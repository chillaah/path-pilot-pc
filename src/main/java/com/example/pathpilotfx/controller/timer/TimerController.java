package com.example.pathpilotfx.controller.timer;

import com.example.pathpilotfx.MainApplication;
import com.example.pathpilotfx.model.Pomodoro;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;


public class TimerController {
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private Pomodoro sessionTimer;
    @FXML
    private Timeline timerTimeline;

    @FXML
    private Label timerDisplay;
    @FXML
    private Label timerType;
    @FXML
    private Button settingsButton;

    @FXML
    protected void onSettingsButtonClick() throws IOException {
            AnchorPane timerSettings = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/pathpilotfx/timerSettings.fxml")));
            rootAnchorPane.getChildren().setAll(timerSettings);
    }

    @FXML
    protected void onStartButtonClick() {
        timerTimeline.play();
    }

    @FXML
    protected void onStopButtonClick() {
        timerTimeline.stop();
        sessionTimer.resetTimer();
        timerDisplay.setText(sessionTimer.getDisplay());
    }

    @FXML
    protected void onTypeButtonClick(){
        timerTimeline.stop();
        timerType.setText(sessionTimer.toggleType());
        sessionTimer.resetTimer();
        timerDisplay.setText(sessionTimer.getDisplay());
    }


    @FXML
    public void initialize(){
        sessionTimer = new Pomodoro();
        timerDisplay.setText(sessionTimer.getDisplay());
        timerTimeline = sessionTimer.setTimeline();
        timerTimeline.getKeyFrames().addAll(new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
            sessionTimer.decreaseSeconds();
            timerDisplay.setText(sessionTimer.getDisplay());
            if(sessionTimer.getSeconds()==0) {
                timerTimeline.stop();
                timerType.setText(sessionTimer.toggleType());
                sessionTimer.resetTimer();
                timerDisplay.setText(sessionTimer.getDisplay());
            }
        }));
        timerTimeline.setCycleCount(Timeline.INDEFINITE);
    }

}