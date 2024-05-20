package com.example.pathpilotfx.controller.countries;

import com.example.pathpilotfx.MainApplication;
import com.example.pathpilotfx.controller.authentication.SessionManager;
import com.example.pathpilotfx.database.CountryDAO;
import com.example.pathpilotfx.database.ExplorationDAO;
import com.example.pathpilotfx.database.UserDAO;
import com.example.pathpilotfx.model.Exploration;
import com.example.pathpilotfx.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
/**
 Controller for all countries view after selecting in Map
 **/
public class testmix {
    @FXML
    private Button backButton;
    @FXML
    private Button beginButton;
    @FXML
    private ImageView image;
    private ExplorationDAO explorationDAO;
    private CountryDAO countryDAO;
    private UserDAO userDAO;
    String CountryName = SelectedCountry.getCountryName();
    private String currentView;

    // Method to set the current view
    public void setCurrentView(String viewName) {
        currentView = viewName;
    }

//
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
        //Image image = new Image(countryDao.getStampByCID())
        //image.setImage(image);
        User user = userDAO.getByUserId(SessionManager.getLoggedInUserId());
        System.out.println("currentexp =" + user.getExp());
        System.out.println("currently exploring" + explorationDAO.getCurrentExploring(SessionManager.getLoggedInUserId()));


        //set begin exploration button as disabled if currently exploring
        if(explorationDAO.getCurrentExploring(SessionManager.getLoggedInUserId()).equals(CountryName)){
            beginButton.setDisable(true);
        }
        //set begin exploration button as disabled if the country name is locked
        if(countryDAO.getLockedCountryNamesByUserId(SessionManager.getLoggedInUserId()).contains(CountryName)){
            beginButton.setDisable(true);
        }
        else{beginButton.setDisable(false);}
    }
    /**
     Method to implement back button to map view
     **/

    public void onBackButtonClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("map-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
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
        Stage stage = (Stage) beginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("map-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
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
            case "Sri Lanka" -> 4;
            default -> 0;
        };
        return ID;
    }

}

