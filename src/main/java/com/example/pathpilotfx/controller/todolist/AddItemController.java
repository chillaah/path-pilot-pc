package com.example.pathpilotfx.controller.todolist;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddItemController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView addButton;

    @FXML
    private Label noTaskLabel;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    void initialize() {
        // intialises the todo(addItem).fxml

        // when add button is pressed
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("Added Clicked!");
            try {
                //load the todo(addItemForm).fxml
                AnchorPane formPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/pathpilotfx/todo(addItemForm).fxml")));
                // display formpane inside the navigation view
                rootAnchorPane.getChildren().setAll(formPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }



        });
    }
}