package com.example.pathpilotfx.controller.countries;

import com.example.pathpilotfx.MainApplication;
import com.example.pathpilotfx.controller.authentication.SessionManager;
import com.example.pathpilotfx.database.CountryDAO;
import com.example.pathpilotfx.database.ExplorationDAO;
import com.example.pathpilotfx.database.UserDAO;
import com.example.pathpilotfx.model.Country;
import com.example.pathpilotfx.model.Exploration;
import com.example.pathpilotfx.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
/**
 Controller for all countries view after selecting in Map
 **/
public class testmix {
    @FXML
    private Label titleLabel;
    @FXML
    private Label textLabel;
    @FXML
    private Button backButton;
    @FXML
    private Button beginButton;
    @FXML
    private ImageView image;
    @FXML
    private AnchorPane rootAnchorPane;
    private ExplorationDAO explorationDAO;
    private CountryDAO countryDAO;
    private UserDAO userDAO;
    private String currentView;
    private String CountryName = SelectedCountry.getSelectedCountry().getCountryName();
    private String countryDetails = SelectedCountry.getSelectedCountry().getCountryDetails();

    // Method to set the current view
    public void setCurrentView(String viewName) {
        currentView = viewName;
    }



    public testmix(){
        this.explorationDAO = new ExplorationDAO();
        this.countryDAO = new CountryDAO();
        this.userDAO = new UserDAO();
    }
    /**
     Method to disable the begin button and initialise the image
     **/
    public void initialize(){
        //all buttons are enabled by default
        String fileName = SelectedCountry.getSelectedCountry().getStampImage() +".png";
        System.out.println("stamp: "+ fileName);

        String relativeImagePath = "/com/example/pathpilotfx/assets/" + fileName;
        System.out.println("stamp: "+ relativeImagePath);

        URL imageUrl = getClass().getResource(relativeImagePath);
        System.out.println("stamp: "+ String.valueOf(imageUrl));

        Image stamp = new Image(String.valueOf(imageUrl));
        image.setImage(stamp);
//        ImageView image = new ImageView(countryDAO.getStampByCID(getIDbyCName(CountryName))+ ".png");
//        image.setImage(image.getImage());
        titleLabel.setText(CountryName);
        System.out.println("details: "+ countryDetails);
        System.out.println("SelectedCountry: "+ SelectedCountry.getSelectedCountry());
        textLabel.setText(countryDetails);
        User user = userDAO.getByUserId(SessionManager.getLoggedInUserId());
        System.out.println("currentexp =" + user.getExp());
        System.out.println("currently exploring " + explorationDAO.getCurrentExploring(SessionManager.getLoggedInUserId()));
        System.out.println("currently selected " + CountryName);


        //set begin exploration button as disabled if currently exploring
        if(explorationDAO.getCurrentExploring(SessionManager.getLoggedInUserId()).equals(CountryName)){
            beginButton.setDisable(true);
        }
        //set begin exploration button as disabled if the country name is locked
        if(countryDAO.getLockedCountryNamesByUserId(SessionManager.getLoggedInUserId()).contains(CountryName)){
            beginButton.setDisable(true);
        }
    }
    /**
     Method to implement back button to map view
     **/

    public void onBackButtonClick() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/map-view.fxml"));
            AnchorPane mapView = loader.load();
            rootAnchorPane.getChildren().setAll(mapView);

        } catch(IOException e){
            System.out.println("countryView has not been found");
            throw new RuntimeException(e);
        }
    }
    /**
     Method to implement begin button, which changes the database currently exploring.
     **/
    public void beginMethod() throws IOException{
        String currExpl = explorationDAO.getCurrentExploring(SessionManager.getLoggedInUserId());
        Exploration explorationExpl = new Exploration(SessionManager.getLoggedInUserId(),getIDbyCName(currExpl),"Explored", false, false);
        Exploration toUpdate = explorationDAO.getByUserIdCountryId(SessionManager.getLoggedInUserId(), getIDbyCName(CountryName));
        toUpdate.setStatus("Exploring");
        explorationDAO.update(explorationExpl);
        explorationDAO.update(toUpdate);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/map-view.fxml"));
            AnchorPane mapView = loader.load();
            rootAnchorPane.getChildren().setAll(mapView);

        } catch(IOException e){
            System.out.println("countryView has not been found");
            throw new RuntimeException(e);
        }
    }
    /**
     Method to call warning confirmation and beginMethod
     **/
    public void onBeginButtonClick() throws IOException {
        int userID = SessionManager.getLoggedInUserId();
        if(!explorationDAO.getCurrentExploring(userID).isEmpty()) {
            if (sendWarningConfirmation()) {
                beginMethod();
            } else {
                System.out.println("do nothing");
            }
        }
        else{beginMethod();}
    }
    /**
     Method to send warning for continuation on clicking begin button
     **/
    private boolean sendWarningConfirmation() {
        String currExpl = explorationDAO.getCurrentExploring(SessionManager.getLoggedInUserId());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setResizable(true);
        alert.setHeight(70.0);
        alert.setTitle("Confirmation Warning");
        alert.setHeaderText("Are you sure you want to begin exploration");
        alert.setContentText("You are currently exploring " + currExpl
                + ". Pressing OK may mean losing your progress");
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
    /**
     Method to get the countryID from countryName
     @param CName the countryName
     **/
    public int getIDbyCName(String CName){
        int ID = switch (CName) {
            case "Australia" -> 1;
            case "Japan" -> 2;
            case "France" -> 3;
            case "SriLanka" -> 4;
            case "India" -> 5;
            case "Switzerland" -> 6;
            case "USA" -> 7;
            case "Canada" -> 8;
            default -> 0;
        };
        return ID;
    }

}


