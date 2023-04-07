package com.example.update1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegistrationController {

    @FXML
    private TextField usernameField; // Declare username text field

    @FXML
    private TextField emailField; // Declare email text field

    @FXML
    private PasswordField passwordField; // Declare password field

    @FXML
    private Button registerButton; // Declare register button

    @FXML
    private void registerButtonClicked() { // Action when register button is clicked
        String username = usernameField.getText().trim(); // Get username from text field
        String email = emailField.getText().trim(); // Get email from text field
        String password = passwordField.getText().trim(); // Get password from password field

        // Check if any fields are empty
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "All fields are required."); // Show alert message
            return;
        }

        // Validate email address
        if (!email.matches("[^@]+@[^@]+\\.[^@]+")) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid email address."); // Show alert message
            return;
        }

        // Store the user data in a file
        try {
            File file = new File("users.txt"); // Create a file object
            if (!file.exists()) { // If the file doesn't exist, create a new one
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, true); // Create a file writer object
            writer.write(username + "," + email + "," + password + "\n"); // Write the user data to the file
            writer.close(); // Close the file writer object
            showAlert(Alert.AlertType.INFORMATION, "Success", "Registration successful!"); // Show alert message
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while registering. Please try again."); // Show alert message
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) { // Method to show alert message
        Alert alert = new Alert(alertType); // Create an alert object
        alert.setTitle(title); // Set alert title
        alert.setHeaderText(null); // Set alert header text
        alert.setContentText(message); // Set alert content text
        alert.show(); // Show alert message
    }

    @FXML
    public void changeToLogin(ActionEvent event) throws IOException // Action when change to login button is clicked
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml")); // Create FXML loader object
        Parent root = fxmlLoader.load(); // Load the FXML file
        Scene scene = new Scene(root); // Create a new scene object

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Get the current stage object
        stage.setScene(scene); // Set the scene of the stage
        stage.show(); // Show the stage
    }

}
