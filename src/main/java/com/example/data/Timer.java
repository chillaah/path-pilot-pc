package main.java.com.example.data;

public class Timer {
    private int userID;
    private int timerID;
    private String dayName;
    private int duration;

    public Timer(int userID, int timerID, String dayName, int duration) {
        this.userID = userID;
        this.timerID = timerID;
        this.dayName = dayName;
        this.duration = duration;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTimerID() {
        return timerID;
    }

    public void setTimerID(int timerID) {
        this.timerID = timerID;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Timer{" +
                "userID=" + userID +
                ", timerID=" + timerID +
                ", dayName='" + dayName + '\'' +
                ", duration=" + duration +
                '}';
    }
}
