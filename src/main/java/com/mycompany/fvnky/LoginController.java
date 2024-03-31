/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.fvnky;

import com.mycompany.fvnky.data.DBConnector;
import com.mycompany.fvnky.data.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.geometry.Dimension2D;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;

public class LoginController {
    @FXML
    private Label alert;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    @FXML
    public void loginClicked(ActionEvent event) throws IOException {
        String usernameText = username.getText();
        String passwordText = password.getText();
        User user = User.getUser(usernameText, hashPassword(passwordText));

        if (user == null) {
            // User found, proceed with login
            alert.setText("User not found");
            // Load the activity.fxml file or perform any other actions required for login
        } else {
            if(user.getPassword() == null){
                alert.setText("Wrong password");
            }else{
                LoginController.setLoggedInUsername(usernameText);
                
                alert.setText("Logged");

                updateUserLoginSessionStatus(user.getId(), 1);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("userUI.fxml"));
                Parent root = loader.load();
                Dimension2D sceneSize = new Dimension2D(1080, 720);
                Scene scene = new Scene(root, sceneSize.getWidth(), sceneSize.getHeight());

                // Get the stage from the action event
                Stage stage = (Stage) username.getScene().getWindow();

                // Set the new scene to the stage
                stage.setScene(scene);
                stage.show();
            }

        }
        int userId = user.getId(); // Ambil ID pengguna yang berhasil login
        UserUIController.saveLoginActivity(userId); // Panggil metode untuk menyimpan aktivitas login
    }

    public static String hashPassword(String password) {
        try {
            // Create MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Add password bytes to digest
            md.update(password.getBytes());

            // Get the hashed bytes
            byte[] hashedBytes = md.digest();

            // Convert byte array to base64 representation
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateUserLoginSessionStatus(int userId, int status) {
        try {
            Connection connection = DBConnector.getConnection();
            if (connection != null) {
                System.out.println("Connection established");
                // Use the connection to perform the update operation
                PreparedStatement statement = connection.prepareStatement("UPDATE user SET session = ? WHERE id = ?");
                statement.setInt(1, status); // Set the session status
                statement.setInt(2, userId); // Set the user ID
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Session status updated successfully");
                } else {
                    System.out.println("Failed to update session status");
                }
                // Close resources
                statement.close();
                connection.close();
            } else {
                System.out.println("Failed to establish connection");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static String loggedInUsername;
    
    public static void setLoggedInUsername(String username){
        loggedInUsername = username;
    }
    public static String getLoggedInUsername() {
        return loggedInUsername;
    }
    
    public static void updateSessionStatusOnClose(String sessionStatus) {
        try {
            Connection connection = DBConnector.getConnection();
            if (connection != null) {
                System.out.println("Connection established");
                // Use the connection to perform the update operation
                PreparedStatement statement = connection.prepareStatement("UPDATE user SET session = ?");
                statement.setString(1, sessionStatus); // Set the session status
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Session status updated successfully to false");
                } else {
                    System.out.println("Failed to update session status");
                }
                // Close resources
                statement.close();
                connection.close();
            } else {
                System.out.println("Failed to establish connection");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
