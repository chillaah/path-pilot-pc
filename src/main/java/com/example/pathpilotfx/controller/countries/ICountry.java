package com.example.pathpilotfx.controller.countries;

import java.io.IOException;

/**
 Interface for country view implementation
 **/
public abstract interface ICountry {

    /**
     Method to apply all disable and enable logic for buttons
     **/
    void initialize();

    /**
     * Method to implement back button to map view
     **/
    default void onBackButtonClick() throws IOException {

    }
    /**
     Method to implement begin button, which changes the database currently exploring.
     **/

    void beginMethod() throws IOException;
    /**
     Method to send warning for continuation on clicking begin button
     **/

    private void onBeginButtonClick() {

    }
    /**
     Method to apply all disable and enable logic for buttons
     @return boolean for if the OK button was pressed.
     **/

    private boolean sendWarningConfirmation() {
        return false;
    }
    /**
     Method to implement unlock button, setting the country as unlocked.
     **/
    private void onUnlockButtonClick() {

    }
    /**
     Method to implement cancel button, setting the country as Unexplored.
     **/
    private void onCancelButtonClick() {

    }
    /**
     Method to get the countryID from countryName
     @param CName the countryName
     **/

    int getIDbyCName(String CName);

}
