//package com.example.pathpilotfx.controller.countries;
//
//import com.example.pathpilotfx.MainApplication;
//import com.example.pathpilotfx.controller.authentication.SessionManager;
//import com.example.pathpilotfx.database.CountryDAO;
//import com.example.pathpilotfx.database.ExplorationDAO;
//import com.example.pathpilotfx.database.UserDAO;
//import com.example.pathpilotfx.model.Exploration;
//import com.example.pathpilotfx.model.User;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.ButtonType;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.util.Optional;
///**
// Controller for France's country view after selecting in Map
// **/
//public class FranceController implements ICountry{
//    @FXML
//    private Button backButton;
//    @FXML
//    private Button beginButton;
//    @FXML
//    private Button unlockButton;
//    private ExplorationDAO explorationDAO;
//    private CountryDAO countryDAO;
//    private UserDAO userDAO;
//
//
//    public FranceController(){
//        this.explorationDAO = new ExplorationDAO();
//        this.countryDAO = new CountryDAO();
//        this.userDAO = new UserDAO();
//    }
//
//    public void initialize(){
//        //all buttons are enabled by default
//        User user = userDAO.getByUserId(SessionManager.getLoggedInUserId());
//        System.out.println("currentexp =" + user.getExp());
//
//        //set unlock button as disabled if it is locked and not enough exp
//        if(countryDAO.getLockedCountryNamesByUserId(SessionManager.getLoggedInUserId()).contains("France")
//                && user.getExp() < 20){
//            unlockButton.setDisable(true);
//        }
//        else if(countryDAO.getUnlockedCountryNamesByUserId(SessionManager.getLoggedInUserId()).contains("France")){
//            System.out.println("successfully in if statement");
//            unlockButton.setDisable(true);
//        }
//        else{unlockButton.setDisable(false);}
//
//        //set begin exploration button as disabled if currently exploring
//        if(explorationDAO.getCurrentExploring(SessionManager.getLoggedInUserId()).equals("France")){
//            beginButton.setDisable(true);
//        }
//        //set begin exploration button as disabled if France is locked
//        if(countryDAO.getLockedCountryNamesByUserId(SessionManager.getLoggedInUserId()).contains("France")){
//            beginButton.setDisable(true);
//        }
//    }
//    /**
//     Method to implement back button to map view
//     **/
//    public void onBackButtonClick() throws IOException {
//        Stage stage = (Stage) backButton.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("map-view.fxml"));
//        Parent root = fxmlLoader.load();
//        Scene scene = new Scene(root, 700, 400);
//        stage.setScene(scene);
//    }
//    /**
//     Method to implement begin button, which changes the database currently exploring.
//     **/
//    public void beginMethod() throws IOException{
//        String currExpl = explorationDAO.getCurrentExploring(SessionManager.getLoggedInUserId());
//        Exploration explorationExpl = new Exploration(SessionManager.getLoggedInUserId(),getIDbyCName(currExpl),"Explored", false, false);
//        Exploration toUpdate = explorationDAO.getByUserIdCountryId(SessionManager.getLoggedInUserId(), 3);
//        toUpdate.setStatus("Exploring");
//        explorationDAO.update(explorationExpl);
//        explorationDAO.update(toUpdate);
//        Stage stage = (Stage) beginButton.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("map-view.fxml"));
//        Parent root = fxmlLoader.load();
//        Scene scene = new Scene(root, 700, 400);
//        stage.setScene(scene);
//    }
//    public void onBeginButtonClick() throws IOException {
//        int userID = SessionManager.getLoggedInUserId();
//        if(!explorationDAO.getCurrentExploring(userID).isEmpty()) {
//            if (sendWarningConfirmation()) {
//                beginMethod();
//            } else {
//                System.out.println("do nothing");
//            }
//        }
//        else{beginMethod();}
//    }
//
//    private boolean sendWarningConfirmation() {
//        String currExpl = explorationDAO.getCurrentExploring(SessionManager.getLoggedInUserId());
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setResizable(true);
//        alert.setHeight(70.0);
//        alert.setTitle("Confirmation Warning");
//        alert.setHeaderText("Are you sure you want to begin exploration");
//        alert.setContentText("You are currently exploring " + currExpl
//                + ". Pressing OK may mean losing your progress");
//        Optional<ButtonType> result = alert.showAndWait();
//        return result.isPresent() && result.get() == ButtonType.OK;
//    }
//    /**
//     Method to implement unlock button, setting the country as unlocked.
//     **/
//    public void onUnlockButtonClick() throws IOException {
//        if(countryDAO.getLockedCountryNamesByUserId(SessionManager.getLoggedInUserId()).contains("France")) {
//            Exploration toUpdate = explorationDAO.getByUserIdCountryId(SessionManager.getLoggedInUserId(), 3);
//            toUpdate.setLocked(false);
//            explorationDAO.update(toUpdate);
//            Stage stage = (Stage) unlockButton.getScene().getWindow();
//            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("map-view.fxml"));
//            Parent root = fxmlLoader.load();
//            Scene scene = new Scene(root, 700, 400);
//            stage.setScene(scene);
//        }
//    }
//
//    /**
//     Method to get the countryID from countryName
//     @param CName the countryName
//     **/
//    public int getIDbyCName(String CName){
//        int ID = switch (CName) {
//            case "Australia" -> 1;
//            case "Japan" -> 2;
//            case "France" -> 3;
//            case "Sri Lanka" -> 4;
//            default -> 0;
//        };
//        return ID;
//    }
//}
