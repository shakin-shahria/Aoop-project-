package com.example.update1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class LoginController {

    @FXML
    private TextField usernameField; // Declare a private TextField variable named usernameField and annotated with FXML

    @FXML
    private PasswordField passwordField; // Declare a private PasswordField variable named passwordField and annotated with FXML

    @FXML
    private void loginButtonClicked(ActionEvent event) { // Declare a private method named loginButtonClicked that takes an ActionEvent parameter and annotated with FXML
        String username = usernameField.getText(); // Get the text value of the usernameField and store it in a String variable named username
        String password = passwordField.getText(); // Get the text value of the passwordField and store it in a String variable named password

        boolean isAuthenticated = authenticate(username, password); // Call the authenticate method with the username and password parameters, and store the result in a boolean variable named isAuthenticated

        if (isAuthenticated) { // If isAuthenticated is true
            showAlert("Login Successful", "Welcome, " + username + "!"); // Call the showAlert method with "Login Successful" and "Welcome, [username]!" parameters
        } else { // If isAuthenticated is false
            showAlert("Login Failed", "Invalid username or password."); // Call the showAlert method with "Login Failed" and "Invalid username or password." parameters
        }
    }

    private boolean authenticate(String username, String password) { // Declare a private method named authenticate that takes a String username and password parameters and returns a boolean
        Map<String, String> users = readUsersFromFile(); // Call the readUsersFromFile method and store the result in a Map variable named users

        if (users.containsKey(username)) { // If the users map contains the username
            String storedPassword = users.get(username); // Get the password associated with the username and store it in a String variable named storedPassword
            if (password.equals(storedPassword)) { // If the password entered by the user matches the stored password
                return true; // Return true
            }
        }

        return false; // Return false
    }

    // This method reads the list of users from the users.txt file into a HashMap
    private Map<String, String> readUsersFromFile() {
        Map<String, String> users = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;

            // Read each line of the users.txt file and add the username and password to the HashMap
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String password = parts[1];
                users.put(username, password);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return the HashMap containing the list of users
        return users;
    }

    // This method shows an alert dialog box with the specified title and message
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // This method is called when the user clicks the "Register" button to go to the registration screen
    @FXML
    public void changeScene(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registration.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
