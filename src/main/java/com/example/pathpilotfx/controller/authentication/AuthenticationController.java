package com.example.pathpilotfx.controller.authentication;

//import com.example.pathpilotfx.HomeApplication;
//import com.example.pathpilotfx.HomeController;
import com.example.pathpilotfx.MainApplication;
import com.example.pathpilotfx.database.ExplorationDAO;
import com.example.pathpilotfx.database.PomodoroDAO;
import com.example.pathpilotfx.database.UserDAO;
import com.example.pathpilotfx.model.Exploration;
import com.example.pathpilotfx.model.Pomodoro;
import com.example.pathpilotfx.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.pathpilotfx.MainApplication.db;
//import static com.example.pathpilotfx.HomeController.authSuccess;
import static com.example.pathpilotfx.controller.authentication.AuthSelectController.*;
import static com.example.pathpilotfx.model.PasswordHash.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
//import com.example.pathpilotfx.HomeController.*;

/**
 * Controller for handling user authentication and account creation.
 */
public class AuthenticationController {

    private final int authVal = getAuthType();

    public Button confirmButton;
    public Button createAccountButton;

    public TextField emailTextField;
    public TextField passwordTextField;

    public String regexE = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    public String regexP = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
    public Label statusLabel;
//    public Label headerMsg;

    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() {

//        if (authVal == 0) {
//            headerMsg.setText("Log In, Please Enter Your Credentials");
//        }
//        else {
//            headerMsg.setText("Sign Up, Please Enter Your Credentials");
//        }
    }

    /**
     * Handles the event when the Confirm button is clicked.
     *
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    protected void onConfirmButtonClick() throws IOException {

        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        System.out.println(authVal);
        System.out.println(email + password);


        //authSuccess(); // for instant access


        // login logic
        // traverse emails on db until matching email found
        // check if db pw = provided pw
        // if true auth user
        // else clear pw field and display wrong password message
        if (email.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Empty email/password");
        } else if (!isValid(email, regexE) || !isValid(password, regexP) || !authenticateUser(email, password)) {
            clearFields();
            statusLabel.setText("Incorrect email/password");
        } else if (!db.isEmailAvailable(email)) {
            clearFields();
            statusLabel.setText("Email not found");
        } else {
            UserDAO userDAO = new UserDAO();
            // link to landing page
            SessionManager.setLoggedInUserId(userDAO.getIdByEmail(email));
            authSuccess();
        }
    }

    /**
     * Handles the event when the Create Account button is clicked.
     *
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    protected void onCreateAccountButtonClick() throws IOException {

        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        System.out.println(authVal);
        System.out.println(email + password);
        // create account logic
        // add sanity checks to email and pw
        // if email not right clear both field
        if (email.isEmpty() || password.isEmpty())
        {
            statusLabel.setText("Empty email/password");
        }

        else if (!isValid(email, regexE))
        {
            clearFields();
            statusLabel.setText("Invalid email format");
        }

        else if (db.isEmailAvailable(email))
        {
            clearFields();
            statusLabel.setText("Email already in use");
        }
        // if password not right clear pw field only
        // At least 8 characters, at least one uppercase letter, one lowercase letter, one digit, and one special character
        else if (!isValid(password, regexP))
        {
            passwordTextField.clear();
            statusLabel.setText("Invalid password format");
        }
        // else auth user
        else
        {
            LocalDateTime ldt = LocalDateTime.now();
            Timestamp date = Timestamp.valueOf(ldt);

            String hashedPassword = hashPassword(password);
            User newUser = new User("username", email, hashedPassword, date, 0);
            db.insert(newUser);
            int userId = db.getIdByEmail(email);

            // link to landing page
            SessionManager.setLoggedInUserId(userId);

            ExplorationDAO explorationDAO = new ExplorationDAO();
            Exploration insertAU = new Exploration(userId, 1, "Exploring", false, false);
            Exploration insertJP = new Exploration(userId,2, "Unexplored", true, false);
            Exploration insertFR = new Exploration(userId,3, "Unexplored", true, false);
            Exploration insertSL = new Exploration(userId,4, "Unexplored", true, false);
            explorationDAO.insert(insertAU);
            explorationDAO.insert(insertJP);
            explorationDAO.insert(insertFR);
            explorationDAO.insert(insertSL);

            //Curate new user timer settings
            PomodoroDAO pomodoroDAO = new PomodoroDAO();
            Pomodoro pomodoro = new Pomodoro();
            pomodoroDAO.insert(pomodoro);

            System.out.println(newUser);

            authSuccess();
        }

    }

//    @FXML
//    protected void onClearButtonClick() {
//        clearFields();
//    }

    /**
     * Clears the input fields.
     */
    public void clearFields() {
        emailTextField.clear();
        passwordTextField.clear();
    }

//    @FXML
//    protected void onBackButtonClick() throws IOException {
//        Stage stage = (Stage) backButton.getScene().getWindow();
//        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("auth-select.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
////        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
//        stage.setScene(scene);
//    }

    /**
     * Redirects to the landing page upon successful authentication or account creation.
     *
     * @throws IOException if an I/O error occurs.
     */
    protected void authSuccess() throws IOException {

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/navigation-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, MainApplication.WIDTH, MainApplication.HEIGHT);
        scene.getStylesheets().add(getClass().getResource("/com/example/pathpilotfx/styles.css").toExternalForm());
        stage.setScene(scene);
    }

    /**
     * Checks if the given string matches the specified regular expression pattern.
     *
     * @param EP      The string to be validated.
     * @param regexEP The regular expression pattern.
     * @return true if the string matches the pattern, false otherwise.
     */
    public boolean isValid(String EP, String regexEP) {

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(regexEP);

        // Match the input email against the pattern
        Matcher matcher = pattern.matcher(EP);

        // Return true if the email matches the pattern, otherwise false
        return matcher.matches();
    }
}