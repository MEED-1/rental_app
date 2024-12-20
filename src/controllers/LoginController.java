package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utils.DatabaseConnection;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorLabel;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Please fill in all fields.");
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT role FROM users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String role = resultSet.getString("role");
                errorLabel.setTextFill(Color.GREEN);
                errorLabel.setText("Login successful! Role: " + role);
                // TODO: Redirect to the appropriate dashboard based on the user's role
            } else {
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Invalid username or password.");
            }
        } catch (Exception e) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("An error occurred during login.");
            e.printStackTrace();
        }
    }
}
