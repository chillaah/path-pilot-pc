package com.example.pathpilotfx.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SideBarController implements Initializable {

    @FXML
    private BorderPane bp;

    @FXML
    private AnchorPane ap;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void home(MouseEvent event){
        loadPage("timer-view.fxml");
    }

    @FXML
    private void todo(MouseEvent event){
        loadPage("todo.fxml");
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
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/pathpilotfx/" + page)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bp.setCenter(root);

    }

}

