package com.example.posproject;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class transaksiController {
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField userTextField;
    @FXML
    private TextField timeTextField;
    @FXML
    private TextField totalBelanja;

    @FXML
    private Button logoutButton;

    @FXML
    private TableView<Transaksi> tableView;
    @FXML
    private TableColumn<Transaksi, Integer> idTransaksi;
    @FXML
    private TableColumn<Transaksi, String> deskripsi;

    @FXML
    private TableView<ItemTransaksi> tableViewItem;
    @FXML
    private TableColumn<ItemTransaksi, String> kode;
    @FXML
    private TableColumn<ItemTransaksi, String> username;
    @FXML
    private TableColumn<ItemTransaksi, String> harga;
    @FXML
    private TableColumn<ItemTransaksi, String> jumlah;
    @FXML
    private TableColumn<ItemTransaksi, String> total;

    @FXML
    private Button goToItemEntry;
    @FXML
    private Button goToActivity;

    @FXML
    private void moveToActivity(ActionEvent event) throws IOException {
        // Load the login page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("activity.fxml"));
        Parent root = loader.load();
        Dimension2D sceneSize = new Dimension2D(1080, 720);
        Scene scene = new Scene(root, sceneSize.getWidth(), sceneSize.getHeight());

        // Get the stage from the action event
        Stage stage = (Stage) goToActivity.getScene().getWindow();

        // Set the new scene to the stage
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void moveToItemEntry(ActionEvent event) throws IOException {
        // Load the login page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("itemEntry.fxml"));
        Parent root = loader.load();
        Dimension2D sceneSize = new Dimension2D(1080, 720);
        Scene scene = new Scene(root, sceneSize.getWidth(), sceneSize.getHeight());

        // Get the stage from the action event
        Stage stage = (Stage) goToItemEntry.getScene().getWindow();

        // Set the new scene to the stage
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        updateSessionStatusOnClose("0");

        // Load the login page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        Dimension2D sceneSize = new Dimension2D(1080, 720);
        Scene scene = new Scene(root, sceneSize.getWidth(), sceneSize.getHeight());

        // Get the stage from the action event
        Stage stage = (Stage) logoutButton.getScene().getWindow();

        insertActivity("Logout", App.getCurrentUser(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "Logged out from Admin App");

        // Set the new scene to the stage
        stage.setScene(scene);
        stage.show();
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

    @FXML
    private void inputDateTime(ActionEvent event) throws IOException {
         LocalDate selectedDate = datePicker.getValue();

        // Get all the transactions from the App instance
        Transaksi[] itemsDb = getTransaksiFromDb(selectedDate);

        // Populate the TableView with the fetched data
        tableView.setItems(FXCollections.observableArrayList(itemsDb));

        // Bind columns with cell values
        idTransaksi.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdTransaksi()).asObject());
        deskripsi.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getdeskripsi()));
        tableView.setOnMouseClicked(this::viewDetail);
    }

    private Transaksi[] getTransaksiFromDb(LocalDate date) {
        // Get all the transactions from the App instance
        Transaksi[] itemsDb = App.getTransaksi(date);

        // Return the fetched items
        return itemsDb;
    }

    private ItemTransaksi[] getTransaksiItemFromDb(Integer idTransaksi) {
        // Get all the transactions from the App instance
        ItemTransaksi[] itemsDb = App.getTransaksiItems(idTransaksi);

        // Return the fetched items
        return itemsDb;
    }

    private void viewDetail(MouseEvent event) {
        Transaksi selectedTransaksi = tableView.getSelectionModel().getSelectedItem();
        if (selectedTransaksi != null) {
            userTextField.setText(selectedTransaksi.getUser());
            timeTextField.setText(selectedTransaksi.getwaktu());
            totalBelanja.setText(String.valueOf(selectedTransaksi.getTotalBelanja()));

            ItemTransaksi[] itemsTransaksiDb = getTransaksiItemFromDb(selectedTransaksi.getIdTransaksi());

            // Populate the TableView with the fetched data
            tableViewItem.setItems(FXCollections.observableArrayList(itemsTransaksiDb));

            // Bind columns with cell values
            kode.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKode()));
            username.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNama()));
            harga.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHarga()));
            jumlah.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getJumlah()));
            total.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTotal()));

        }
    }


}
