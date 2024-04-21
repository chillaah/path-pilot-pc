package com.example.pathpilotfx.controller;

import javafx.animation.Timeline;

public class Pomodoro {
    private int seconds;
    private String display;
    private int sessionCount;
    private int rest;
    private int work;
    private Boolean isWork; //work = true, rest = false


    public Pomodoro () {
        this.work = 25;
        this.seconds = 1500; //25 * 60
        this.sessionCount = 0;
        this.rest = 5;
        this.display = String.format("%02d:%02d", 25 , 0);
        this.isWork = true;

    }
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

    public Timeline setTimeline() {
        return new Timeline();
    }

    public void decreaseSeconds() {
        seconds--;
        display = String.format("%02d:%02d", seconds/60 , seconds % 60);
    }

    public void resetTimer(){
        this.seconds = (isWork) ? work * 60 : rest * 60;
        this.display = String.format("%02d:%02d", seconds/60 , seconds % 60);
    }

    public String toggleType(){
        isWork = !isWork;
        if(isWork) {return "FOCUS";} else return "BREAK";
    }

    public String getType(){
        if(isWork) {return "FOCUS";} else return "BREAK";
    }
}
