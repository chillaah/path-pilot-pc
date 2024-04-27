package com.example.pathpilotfx.controller.timer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TimerController {
    @FXML
    private Pomodoro sessionTimer;
    @FXML
    private Timeline timerTimeline;

    @FXML
    private Label timerDisplay;
    @FXML
    private Label timerType;

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