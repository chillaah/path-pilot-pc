package com.example.pathpilotfx.controller.navigation;


import com.example.pathpilotfx.MainApplication;
import com.example.pathpilotfx.database.ToDoDAO;
import com.example.pathpilotfx.model.Task;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller class for the side bar navigation.
 */
public class SideBarController implements Initializable {

    @FXML
    private BorderPane bp;

    @FXML
    private AnchorPane ap;

    /**
     * Initializes the side bar navigation.
     *
     * @param url            The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPage("timer-view.fxml"); //Default page when application is loaded
    }

    /**
     * Loads the home page when the home icon is clicked.
     *
     * @param event The mouse event triggering the home action.
     */
    @FXML
    private void home(MouseEvent event){
        loadPage("timer-view.fxml");
    }

    /**
     * Loads the to-do list page when the to-do icon is clicked.
     * If there are no tasks, loads the page for adding tasks; otherwise, loads the page for viewing existing tasks.
     *
     * @param event The mouse event triggering the to-do action.
     */
    @FXML
    private void todo(MouseEvent event){
        //establish a connection with the database
        ToDoDAO toDoDAO = new ToDoDAO();
        List<Task> taskList = toDoDAO.getAll();

        if(taskList.isEmpty()){
            loadPage("todo(addItem).fxml");   // empty page asking users to add tasks
        }
        else{
            loadPage("todo(taskPage).fxml"); // to show existing tasks
        }
    }

    /**
     * Loads the map page when the map icon is clicked.
     *
     * @param event The mouse event triggering the map action.
     */
    @FXML
    private void map(MouseEvent event){
        loadPage("map-view.fxml");
    }

    /**
     * Loads the profile page when the profile icon is clicked.
     *
     * @param event The mouse event triggering the profile action.
     */
    @FXML
    private void profile(MouseEvent event){
        loadPage("profile.fxml");
    }



    /**
     * Loads the specified FXML page into the center of the border pane.
     *
     * @param page The name of the FXML page to load.
     */
    public void loadPage(String page){
        Parent root = null;


        try {
//            System.out.println("The page is " + page);
            // load the required page
          //  FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/example/pathpilotfx/" + page)));
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/pathpilotfx/" + page)));


        } catch (IOException e) {
            System.out.println("root has not been found");
            throw new RuntimeException(e);
        }
        //set the loaded page in the 'center' location in the border pane in navigation-view.fxml

        bp.setCenter(root);
        BorderPane.setAlignment(root, Pos.CENTER);
        //System.out.println(root.idProperty());
    }
}

