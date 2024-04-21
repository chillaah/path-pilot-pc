package com.example.pathpilotfx.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.Objects;

public class TimerController {
    @FXML
    private Pomodoro focusTimer;
    @FXML
    private Timeline pomodoroTimeline;

    @FXML
    private Label timer;
    @FXML
    private Label type;

    @FXML
    protected void onStartButtonClick() {
        pomodoroTimeline.play();
    }

    @FXML
    protected void onStopButtonClick() {
        pomodoroTimeline.stop();
        focusTimer.resetTimer();
        timer.setText(focusTimer.getDisplay());
    }

    @FXML
    protected void onTypeButtonClick(){
        type.setText(focusTimer.toggleType());
        focusTimer.resetTimer();
        timer.setText(focusTimer.getDisplay());
    }


    @FXML
    public void initialize(){
        focusTimer = new Pomodoro();
        timer.setText(focusTimer.getDisplay());
        pomodoroTimeline = focusTimer.setTimeline();
        pomodoroTimeline.getKeyFrames().addAll(new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
            focusTimer.decreaseSeconds();
            timer.setText(focusTimer.getDisplay());
            if(focusTimer.getSeconds()==0) {
                pomodoroTimeline.stop();
                type.setText(focusTimer.toggleType());
                focusTimer.resetTimer();
                timer.setText(focusTimer.getDisplay());
            }
        }));
        pomodoroTimeline.setCycleCount(Timeline.INDEFINITE);
    }
}
