package com.example.posproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    private static Activity[] activities;
    private static Transaksi[] transaksis;

    private static ItemTransaksi[] itemTransaksi;

    private static User currentUser;
    public static String getCurrentUser() {
        return currentUser.getUser();
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }



    public static Transaksi[] getTransaksi(LocalDate date) {
        try {
            Connection connection = DBConnector.getConnection();
            if (connection != null) {
                // Use the connection to perform database operations
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM transaksi WHERE tanggal = ?");
                statement.setString(1, String.valueOf(date)); // Convert LocalDate to string
                ResultSet resultSet = statement.executeQuery();
                List<Transaksi> transaksiList = new ArrayList<>();

                while (resultSet.next()) {
                    int id = resultSet.getInt("idTransaksi");
                    String user = resultSet.getString("user");
                    String tanggal = resultSet.getString("tanggal");
                    String waktu = resultSet.getString("waktu");
                    String description = resultSet.getString("deskripsi");
                    Float totalBelanja = resultSet.getFloat("totalBelanja");

                    Transaksi transaksi = new Transaksi(id, user, tanggal, waktu, description, totalBelanja);
                    transaksiList.add(transaksi);
                }

                transaksis = transaksiList.toArray(new Transaksi[0]); // Convert List<Transaksi> to Transaksi[]

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
        return transaksis;
    }


    public static ItemTransaksi[] getTransaksiItems(Integer idTransaksi) {
        try {
            Connection connection = DBConnector.getConnection();
            if (connection != null) {
                // Use the connection to perform database operations
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM itemtransaksi WHERE idTransaksi = ?");
                statement.setString(1, String.valueOf(idTransaksi)); // Set the username parameter
                ResultSet resultSet = statement.executeQuery();
                List<ItemTransaksi> transaksiList = new ArrayList<>(); // Use a different name to avoid shadowing

                while (resultSet.next()) {
                    int id = resultSet.getInt("idTransaksi");
                    String kode = resultSet.getString("kode");
                    String nama = resultSet.getString("nama");
                    String harga= resultSet.getString("harga");
                    String jumlah = resultSet.getString("jumlah");
                    String total = resultSet.getString("total");
                    ItemTransaksi itemTransaksi = new ItemTransaksi(id, kode, nama, harga, jumlah, total);
                    transaksiList.add(itemTransaksi);
                }

                itemTransaksi = transaksiList.toArray(new ItemTransaksi[0]); // Convert List<Item> to Item[]

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
        return itemTransaksi;
    }

    public static Activity[] getActivity() {
        if (activities == null) {
            try {
                Connection connection = DBConnector.getConnection();
                if (connection != null) {
                    // Use the connection to perform database operations
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM activity");
                    ResultSet resultSet = statement.executeQuery();
                    List<Activity> activityList = new ArrayList<>(); // Use a different name to avoid shadowing

                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String itemName = resultSet.getString("activity");
                        String user = resultSet.getString("user");
                        String date = resultSet.getString("datetime");
                        String description = resultSet.getString("description");

                        Activity activity = new Activity(id, itemName, user, date, description);
                        activityList.add(activity);
                    }

                    activities = activityList.toArray(new Activity[0]); // Convert List<Item> to Item[]

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
        }
        return activities;
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

    @Override
    public void stop() throws Exception {
        if (currentUser != null) {
            updateSessionStatusOnClose("0");
            insertActivity("Logout", getCurrentUser(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "Logged out from Admin App");
        }
    }

    public static void insertActivity(String activity, String username, String datetime, String description) {
        try {
            Connection connection = DBConnector.getConnection();
            if (connection != null) {
                System.out.println("Connection established");
                // Use the connection to insert activity record
                PreparedStatement statement = connection.prepareStatement("INSERT INTO activity (activity, user, datetime, description) VALUES (?, ?, ?, ?)");
                statement.setString(1, activity);
                statement.setString(2, username);
                statement.setString(3, datetime);
                statement.setString(4, description);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Activity record inserted successfully");
                } else {
                    System.out.println("Failed to insert activity record");
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

    public static void main(String[] args) {
        launch();
    }
}