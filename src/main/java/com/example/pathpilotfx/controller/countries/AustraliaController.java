package com.example.pathpilotfx.controller.countries;

import com.example.pathpilotfx.MainApplication;
import com.example.pathpilotfx.controller.authentication.SessionManager;
import com.example.pathpilotfx.database.CountryDAO;
import com.example.pathpilotfx.database.ExplorationDAO;
import com.example.pathpilotfx.database.UserDAO;
import com.example.pathpilotfx.model.Exploration;
import com.example.pathpilotfx.model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
/**
Controller for Australia's country view after selecting in Map
 **/
public class AustraliaController implements ICountry {
    @FXML
    private Button backButton;
    @FXML
    private Button beginButton;
    @FXML
    private Button unlockButton;
    private ExplorationDAO explorationDAO;
    private CountryDAO countryDAO;
    private UserDAO userDAO;


    public AustraliaController(){
        this.explorationDAO = new ExplorationDAO();
        this.countryDAO = new CountryDAO();
        this.userDAO = new UserDAO();
    }

    public void initialize(){
        //all buttons are enabled by default

        User user = userDAO.getByUserId(SessionManager.getLoggedInUserId());
        System.out.println("currentexp =" + user.getExp());
        System.out.println("currently exploring" + explorationDAO.getCurrentExploring(SessionManager.getLoggedInUserId()));
        System.out.println(countryDAO.getUnlockedCountryNamesByUserId(SessionManager.getLoggedInUserId()));

        //set unlock button as disabled if it is already unlocked and the user has enough exp
        // (even though Australia is unlocked by default, and it should be impossible to get negative exp)
        if(countryDAO.getUnlockedCountryNamesByUserId(SessionManager.getLoggedInUserId()).contains("Australia")){
            System.out.println("successfully in if statement");
            unlockButton.setDisable(true);
        }

        //set begin exploration button as disabled if currently exploring
        if(explorationDAO.getCurrentExploring(SessionManager.getLoggedInUserId()).equals("Australia")){
            beginButton.setDisable(true);
        }
        else{beginButton.setDisable(false);}
        //set begin exploration button as disabled if Australia is locked which it should not be
        if(countryDAO.getLockedCountryNamesByUserId(SessionManager.getLoggedInUserId()).contains("Australia")){
            beginButton.setDisable(true);
        }
    }

    public void onBackButtonClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("map-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
    }

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

    public void beginMethod() throws IOException{
        int userID = SessionManager.getLoggedInUserId();
        String currExpl = explorationDAO.getCurrentExploring(userID);
        Exploration explorationExpl = new Exploration(userID, getIDbyCName(currExpl), "Explored", false, false);
        Exploration toUpdate = explorationDAO.getByUserIdCountryId(userID, 1);
        toUpdate.setStatus("Exploring");
        explorationDAO.update(explorationExpl);
        explorationDAO.update(toUpdate);
        Stage stage = (Stage) beginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("map-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
    }

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

    public void onUnlockButtonClick() throws IOException {
        Exploration toUpdate = explorationDAO.getByUserIdCountryId(SessionManager.getLoggedInUserId(), 1);
        toUpdate.setLocked(false);
        explorationDAO.update(toUpdate);
        Stage stage = (Stage) unlockButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("map-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);

    }


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
