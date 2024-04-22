package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.example.demo.AuthSelectController.*;
import static com.example.demo.PasswordHash.*;


public class AuthenticationController {

    private final int authVal = getAuthType();

    public Button confimButton;
    public Button clearButton;

    public TextField emailTextField;
    public TextField passwordTextField;

    public String regexE = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    public String regexP = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

    static UserDAO db = new UserDAO();

    @FXML
    protected void onConfirmButtonClick() {
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        System.out.println(authVal);
        System.out.println(email + password);

        if (authVal == 0)
        {
            // login logic
            // traverse emails on db until matching email found
            if (isValid(email, regexE) && db.isEmailAvailable(email))
            {
                if (isValid(password, regexP) && authenticateUser(email, password))
                {
                    // link to landing page
                    System.out.println("You're In");
                }
                else
                {
                    passwordTextField.clear();
                    System.out.println("incorrect password");
                }
            }
            else
            {
                clearFields();
                System.out.println("incorrect email");
            }

            // check if db pw = provided pw
            // if true auth user
            // else clear pw field and display wrong password message
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

//                String hashedPassword = hashPassword(password);
                User newUser = new User(lastId, "username", email, password, date, 1);
                db.insert(newUser);
                System.out.println(newUser.toString());
                // link to landing page
                System.out.println("You're In");
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

    public boolean isValid(String email, String regexEP) {

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(regexEP);

        // Match the input email against the pattern
        Matcher matcher = pattern.matcher(email);

        // Return true if the email matches the pattern, otherwise false
        return matcher.matches();
    }
}