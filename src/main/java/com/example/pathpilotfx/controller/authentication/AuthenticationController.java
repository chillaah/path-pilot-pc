package com.example.pathpilotfx.controller.authentication;

import com.example.pathpilotfx.MainApplication;
import com.example.pathpilotfx.database.CountryDAO;
import com.example.pathpilotfx.database.ExplorationDAO;
import com.example.pathpilotfx.database.PomodoroDAO;
import com.example.pathpilotfx.database.UserDAO;
import com.example.pathpilotfx.model.Country;
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
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.pathpilotfx.model.PasswordHash.*;


/**
 * Controller for handling user authentication and account creation.
 */
public class AuthenticationController {

    @FXML
    private Button confirmButton;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label statusLabel;

    private final String regexE = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private final String regexP = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

    /**
     * Handles the event when the Login button is clicked.
     *
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    protected void onConfirmButtonClick() throws IOException {

        String email = emailTextField.getText();
        String password = passwordTextField.getText();

        // login logic
        if (email.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Empty email/password");
        } else if (!isValid(email, regexE) || !isValid(password, regexP)) {
            clearFields();
            statusLabel.setText("Invalid email/password format");
        } else if (!MainApplication.getDB().isEmailAvailable(email)) {
            statusLabel.setText("User does not exist");
        } else if (!authenticateUser(email, password)) {
            passwordTextField.clear();
            statusLabel.setText("Incorrect password");
        } else {
            // Link to landing page
            UserDAO userDAO = new UserDAO();
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

        // create account logic
        if (email.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Empty email/password");
        } else if (!isValid(email, regexE)) {
            clearFields();
            statusLabel.setText("Invalid email format");
        } else if (MainApplication.getDB().isEmailAvailable(email)) {
            clearFields();
            statusLabel.setText("Email already in use");
        }
        // At least 8 characters, at least one uppercase letter, one lowercase letter, one digit, and one special character
        else if (!isValid(password, regexP)) {
            passwordTextField.clear();
            statusLabel.setText("Invalid password format");
        } else {
            LocalDateTime ldt = LocalDateTime.now();
            Timestamp date = Timestamp.valueOf(ldt);

            String hashedPassword = hashPassword(password);
            User newUser = new User("username", email, hashedPassword, date, 0);
            MainApplication.getDB().insert(newUser);
            int userId = MainApplication.getDB().getIdByEmail(email);

            // link to landing page
            SessionManager.setLoggedInUserId(userId);

            ExplorationDAO explorationDAO = new ExplorationDAO();
            CountryDAO countryDAO = new CountryDAO();
            List<Country> countryList = countryDAO.getAll();

            for (Country country : countryList) {
                String current_status = "Unexplored";
                boolean is_locked = true;
                if (Objects.equals(country.getCountryName(), "Australia")) {
                    current_status = "Exploring";
                    is_locked = false;
                }
                Exploration exploration = new Exploration(userId, country.getCountryID(), current_status, is_locked, false);
                explorationDAO.insert(exploration);
            }

            // Curate new user timer settings
            PomodoroDAO pomodoroDAO = new PomodoroDAO();
            Pomodoro pomodoro = new Pomodoro();
            pomodoroDAO.insert(pomodoro);

            authSuccess();
        }
    }

    /**
     * Clears the input fields.
     */
    public void clearFields() {
        emailTextField.clear();
        passwordTextField.clear();
    }

    /**
     * Redirects to the landing page upon successful authentication or account creation.
     *
     * @throws IOException if an I/O error occurs.
     */
    protected void authSuccess() throws IOException {

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/navigation-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, MainApplication.getWidth(), MainApplication.getHeight());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/pathpilotfx/styles.css")).toExternalForm());
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