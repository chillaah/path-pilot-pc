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

public class SideBarController implements Initializable {

    @FXML
    private BorderPane bp;

    @FXML
    private AnchorPane ap;

    @FXML
    private ImageView menu;

    @FXML
    private ImageView menuClose;

    @FXML
    private AnchorPane slider;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        slider.setTranslateX(-150);
        loadPage("timer-view.fxml"); //Default page when application is loaded
    }

    @FXML
    private void home(MouseEvent event){
        loadPage("timer-view.fxml");
    }

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

    @FXML
    private void map(MouseEvent event){
        loadPage("map-view.fxml");
    }

    @FXML
    private void profile(MouseEvent event){
        loadPage("profile.fxml");
    }

    @FXML
    private void menu(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToX(0);
        slide.play();

        slider.setTranslateX(-150);

        slide.setOnFinished((ActionEvent e)-> {
            menu.setVisible(false);
            menuClose.setVisible(true);
        });
    }

    @FXML
    private void menuClose(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        slide.setToX(-150);
        slide.play();

        slider.setTranslateX(0);

        slide.setOnFinished((ActionEvent e)-> {
            menu.setVisible(true);
            menuClose.setVisible(false);
        });
    }

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

