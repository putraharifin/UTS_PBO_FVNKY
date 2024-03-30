/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fvnky.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author VIVOBOOK PRO
 */
public class User {
    public int id;
    public String username;
    public String password;
    public int session;

    public User(int id,String username, String password, int session) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.session = session;
    }
    public int getId() {
        return id;
    }

    public String getUser() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public static User getUser(String username, String passwordInput) {
        User user = null;

        try {
            Connection connection = DBConnector.getConnection();
            if (connection != null) {
                // Use the connection to perform database operations
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
                statement.setString(1, username); // Set the username parameter
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String password = resultSet.getString("password");
                    // Create the User object
                    if(password.equals(passwordInput)){
                        user = new User(id, username, password, 1);
                    }else{
                        // Password does not match
                        user = new User(id, username, null, 0);
                    }
                }

                // Close resources
                resultSet.close();
                statement.close();
                connection.close();
            } else {
                System.out.println("Failed to establish connection");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

}
