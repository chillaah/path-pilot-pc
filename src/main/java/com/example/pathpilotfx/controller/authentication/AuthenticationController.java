package com.example.pathpilotfx.controller.authentication;

import com.example.pathpilotfx.AuthSelectApplication;
import com.example.pathpilotfx.HomeApplication;
import com.example.pathpilotfx.HomeController;
import com.example.pathpilotfx.controller.navigation.SideBarController;
import com.example.pathpilotfx.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.pathpilotfx.AuthSelectApplication.db;
import static com.example.pathpilotfx.HomeController.authSuccess;
import static com.example.pathpilotfx.controller.authentication.AuthSelectController.*;
import static com.example.pathpilotfx.model.PasswordHash.*;
import com.example.pathpilotfx.HomeController.*;


public class AuthenticationController {

    private final int authVal = getAuthType();

    public Button confirmButton;
    public Button clearButton;
    public Button backButton;

    public TextField emailTextField;
    public TextField passwordTextField;

    public String regexE = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    public String regexP = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

    @FXML
    protected void onConfirmButtonClick() throws IOException {

        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        System.out.println(authVal);
        System.out.println(email + password);

        if (authVal == 0)
        {
            // login logic
            // traverse emails on db until matching email found
            // check if db pw = provided pw
            // if true auth user
            // else clear pw field and display wrong password message
            if (!isValid(email, regexE))
            {
                clearFields();
                System.out.println("invalid email format");
            }

            else if (!db.isEmailAvailable(email))
            {
                clearFields();
                System.out.println("email not found");
            }

            else if (!isValid(password, regexP))
            {
                passwordTextField.clear();
                System.out.println("invalid password format");
            }

            else if (!authenticateUser(email, password))
            {
                passwordTextField.clear();
                System.out.println("incorrect password");
            }

            else
            {
                // link to landing page
                System.out.println("You're In");
                authSuccess();
            }
        }
        else // authVal = 1
        {
            // create account logic
            // add sanity checks to email and pw
            // if email not right clear both field
            if (!isValid(email, regexE))
            {
                clearFields();
                System.out.println("invalid email format");
            }

            else if (db.isEmailAvailable(email))
            {
                clearFields();
                System.out.println("email already in use");
            }
            // if password not right clear pw field only
            // At least 8 characters, at least one uppercase letter, one lowercase letter, one digit, and one special character
            else if (!isValid(password, regexP))
            {
                passwordTextField.clear();
                System.out.println("invalid password format");
            }
            // else auth user
            else
            {
                LocalDateTime ldt = LocalDateTime.now();
                Timestamp date = Timestamp.valueOf(ldt);
                int lastId = db.getLatestUser(); lastId++;

                String hashedPassword = hashPassword(password);
                User newUser = new User(lastId, "username", email, hashedPassword, date, 1);
                db.insert(newUser);
                System.out.println(newUser.toString());
                // link to landing page
                System.out.println("You're In");
                authSuccess();
            }
        }
    }

    @FXML
    protected void onClearButtonClick() {
        clearFields();
    }

    public void clearFields() {
        emailTextField.clear();
        passwordTextField.clear();
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(AuthSelectApplication.class.getResource("auth-select.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), AuthSelectApplication.WIDTH, AuthSelectApplication.HEIGHT);
//        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
    }

    protected void authSuccess() throws IOException {
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getResource("navigation-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setScene(scene);

//// Get the scene of the current window
//        Scene currentScene = confirmButton.getScene();
//// Load the navigation view FXML
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("navigation-view.fxml"));
//        Parent navigationView = fxmlLoader.load();
//// Set the content of the current scene to the navigation view
//        currentScene.setRoot(navigationView);
    }

    public boolean isValid(String EP, String regexEP) {

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(regexEP);

        // Match the input email against the pattern
        Matcher matcher = pattern.matcher(EP);

        // Return true if the email matches the pattern, otherwise false
        return matcher.matches();
    }
}