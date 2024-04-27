package com.example.pathpilotfx.controller.timer;

import javafx.animation.Timeline;

public class Pomodoro {
    private int seconds;
    private String display; // text string formatted to represent the timer display.
    private int sessionCount; // sessionCount to be used for long breaks.
    private int rest;
    private int work;
    private Boolean isWork; //work = true, rest = false

    //constructor for default 25min - 5min timer
    public Pomodoro () {
        this.work = 25;
        this.seconds = 1500; //25 * 60
        this.sessionCount = 0;
        this.rest = 5;
        this.display = String.format("%02d:%02d", 25 , 0);
        this.isWork = true;

    }

    //constructor for customised timer duration
    public Pomodoro (int work, int rest) {
        this.work = work;
        this.seconds = work * 60;
        this.rest = rest;
        this.sessionCount = 0;
        this.display = String.format("%02d:%02d", seconds/60 , seconds % 60);
        this.isWork = true;
    }

    public int getSeconds(){
        return seconds;
    }

    public String getDisplay() {
        return display;
    }

    // creates a timeline for enabling the timer animation.
    public Timeline setTimeline() {
        return new Timeline();
    }

    //decrements the seconds count by 1, and updates displays.
    //this method will be used for the keyframe animation to update the timer every 1 second.
    public void decreaseSeconds() {
        seconds--;
        display = String.format("%02d:%02d", seconds/60 , seconds % 60);
    }

    public void resetTimer(){
        this.seconds = (isWork) ? work * 60 : rest * 60;
        this.display = String.format("%02d:%02d", seconds/60 , seconds % 60);
    }

    //changes the session type and returns a string representing the current type
    public String toggleType(){
        isWork = !isWork;
        if(isWork) {return "FOCUS";} else return "BREAK";
    }

    public String getType(){
        if(isWork) {return "FOCUS";} else return "BREAK";
    }
}