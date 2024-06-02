package com.example.pathpilotfx.model;

import javafx.animation.Timeline;


/**
 * Represents a Pomodoro timer for managing work and break sessions.
 */
public class Pomodoro {
    private int seconds;
    private String display; // text string formatted to represent the timer display.
    private int sessionCount; // sessionCount to be used for long breaks.
    private int rest;

    //private boolean test; // for running test purpose only
    private int work;
    private Boolean isWork; //work = true, rest = false

    /**
     * Constructor for default 25 minutes work and 5 minutes break timer.
     */
    public Pomodoro () {
        this.work = 25;
        this.seconds = 1500; //25 * 60
        this.sessionCount = 0;
        this.rest = 5;
        this.display = String.format("%02d:%02d", 25 , 0);
        this.isWork = true;

    }

    /**
     * Constructor for customised timer duration.
     *
     * @param work The duration of the work session in minutes.
     * @param rest The duration of the break session in minutes.
     */
    public Pomodoro (int work, int rest) {
        if (work <= 0 || rest <= 0) {
            this.work = 0;
            this.rest = 0;
            this.seconds = 0;
            this.display = "00:00";
        } else {
            this.work = work;
            this.rest = rest;
            this.seconds = work * 60;
            this.display = String.format("%02d:%02d", seconds / 60, seconds % 60);
        }
        this.sessionCount = 0;
        this.isWork = true;
    }

    /**
     * Retrieves the remaining seconds on the timer.
     *
     * @return The remaining seconds.
     */
    public int getSeconds(){
        return seconds;
    }

    /**
     * Retrieves the formatted display of the timer.
     *
     * @return The formatted display string.
     */
    public String getDisplay() {
        return display;
    }

    /**
     * Creates a timeline for enabling the timer animation.
     *
     * @return A Timeline object.
     */
    public Timeline setTimeline() {
        return new Timeline();
    }

    /**
     * Decreases the remaining seconds count by 1 and updates the display.
     */
    public void decreaseSeconds() {
        if(seconds > 0){
            seconds--;
        }

        display = String.format("%02d:%02d", seconds/60 , seconds % 60);
    }

    /**
     * Resets the timer to its initial state based on the current session type (work or break).
     */
    public void resetTimer(){
        this.seconds = (isWork) ? work * 60 : rest * 60;
        this.display = String.format("%02d:%02d", seconds/60 , seconds % 60);
    }

    /**
     * Toggles the session type between work and break.
     *
     * @return A string representing the current session type.
     */
    public String toggleType(){
        isWork = !isWork;
        if(isWork) {return "FOCUS";} else return "BREAK";
    }

    /**
     * Retrieves the current timer type.
     *
     * @return A string representing the current timer type.
     */
    public String getTimerType(){
        if(isWork) {return "FOCUS";} else return "BREAK";
    }

    /**
     * Retrieves the current session type.
     *
     * @return A string representing the current session type.
     */
    public String getType(){
        if(isWork) {return "FOCUS";} else return "BREAK";
    }

    /**
     * Sets the remaining seconds on the timer.
     *
     * @param seconds The remaining seconds to be set.
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    /**
     * Sets the display string of the timer.
     *
     * @param display The display string to be set.
     */
    public void setDisplay(String display) {
        this.display = display;
    }

    /**
     * Retrieves the session count of the timer.
     *
     * @return The session count.
     */
    public int getSessionCount() {
        return sessionCount;
    }

    /**
     * Sets the session count of the timer.
     *
     * @param sessionCount The session count to be set.
     */
    public void setSessionCount(int sessionCount) {
        this.sessionCount = sessionCount;
    }

    /**
     * Retrieves the duration of the break session.
     *
     * @return The break session duration.
     */
    public int getRest() {
        return rest;
    }

    /**
     * Retrieves the duration of the work session.
     *
     */
    public void setRest(int rest) {
        this.rest = rest;
    }

    /**
     * Retrieves the duration of the work session.
     *
     * @return The work session duration.
     */
    public int getWork() {
        return work;
    }

    /**
     * Retrieves the duration of the work session.
     *
     */
    public void setWork(Boolean work) {
        isWork = work;
    }

    /**
     * Retrieves the duration of the work session.
     *
     */
    public void setWork(int work) {
        this.work = work;
    }
}
