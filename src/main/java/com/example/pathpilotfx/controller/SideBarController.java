package com.example.pathpilotfx.controller;


import com.example.pathpilotfx.Database.ToDoDOA;
import com.example.pathpilotfx.model.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class SideBarController implements Initializable {

    @FXML
    private BorderPane bp;

    @FXML
    private AnchorPane ap;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPage("home.fxml"); //Default page when application is loaded
    }

    @FXML
    private void home(MouseEvent event){
        loadPage("home.fxml");
    }

    @FXML
    private void todo(MouseEvent event){
        //establish a connection with the database
        ToDoDOA toDoDOA = new ToDoDOA();
        List<Task> taskList = toDoDOA.getAll();

        if(taskList.isEmpty()){
            loadPage("todo(addItem).fxml");   // empty page asking users to add tasks
        }
        else{
            loadPage("todo(taskPage).fxml"); // to show existing tasks
        }
    }

    @FXML
    private void map(MouseEvent event){
        loadPage("map.fxml");
    }

    @FXML
    private void profile(MouseEvent event){
        loadPage("profile.fxml");
    }

    private void loadPage(String page){
        Parent root = null;

        try {
            // load the required page
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/pathpilotfx/" + page)));

        } catch (IOException e) {
            System.out.println("root has not been found");
            throw new RuntimeException(e);
        }
        //set the loaded page in the 'center' location in the border pane in navigation-view.fxml
        bp.setCenter(root);
        BorderPane.setAlignment(root, Pos.CENTER);

    }

}

