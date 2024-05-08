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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class SriLankaController {
    @FXML
    private Button backButton;
    @FXML
    private Button beginButton;
    @FXML
    private Button unlockButton;
    @FXML
    private Button cancelButton;
    private ExplorationDAO explorationDAO;
    private CountryDAO countryDAO;
    private UserDAO userDAO;


    public SriLankaController(){
        this.explorationDAO = new ExplorationDAO();
        this.countryDAO = new CountryDAO();
        this.userDAO = new UserDAO();
    }

    public void initialize(){
        //all buttons are enabled by default
        User user = userDAO.getByUserId(SessionManager.getLoggedInUserId());
        System.out.println("currentexp =" + user.getExp());

        //set cancel exploration button as disabled if there are no currently exploring
        if (explorationDAO.getCurrentExploring(SessionManager.getLoggedInUserId()) == null){
            cancelButton.setDisable(true);
        }
        //set cancel exploration button as disabled if current exploration is not Sri Lanka
        else if (explorationDAO.getCurrentExploring(SessionManager.getLoggedInUserId()) != "Sri Lanka"){
            cancelButton.setDisable(true);
        }
        else {cancelButton.setDisable(false);}

        //set unlock button as disabled if it is already unlocked
        if(countryDAO.getLockedCountryNamesByUserId(SessionManager.getLoggedInUserId()).contains("Sri Lanka")
                && user.getExp() < 40){
            unlockButton.setDisable(true);
        }
        else{unlockButton.setDisable(false);}

        //set begin exploration button as disabled if currently exploring
        if(explorationDAO.getCurrentExploring(SessionManager.getLoggedInUserId()).equals("Sri Lanka")){
            beginButton.setDisable(true);
        }
        //set begin exploration button as disabled if Sri Lanka locked
        if(countryDAO.getLockedCountryNamesByUserId(SessionManager.getLoggedInUserId()).contains("Sri Lanka")){
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
        if(sendWarningConfirmation()) {
            String currExpl = explorationDAO.getCurrentExploring(SessionManager.getLoggedInUserId());
            Exploration explorationExpl = new Exploration(SessionManager.getLoggedInUserId(),getIDbyCName(currExpl),"Explored", false, false);
            Exploration explorationFR = new Exploration(SessionManager.getLoggedInUserId(), 4, "Exploring", false, false);
            explorationDAO.update(explorationExpl);
            explorationDAO.update(explorationFR);
            Stage stage = (Stage) beginButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("map-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 700, 400);
            stage.setScene(scene);
        }
        else{System.out.println("do nothing");}
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
        Stage stage = (Stage) unlockButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("map-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
    }
    public void onCancelButtonClick() throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("srilanka-view.fxml"));
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
