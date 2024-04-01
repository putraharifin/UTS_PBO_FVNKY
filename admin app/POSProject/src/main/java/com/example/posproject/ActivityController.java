package com.example.posproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ActivityController {
    public void setAppInstance(App appInstance) {
    }

    @FXML
    private TextField userTextField;
    @FXML
    private TextField descTextField;
    @FXML
    private TextField timeTextField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button logoutButton;

    @FXML
    private TableView<Activity> tableView;

    @FXML
    private TableColumn<Activity, Integer> idColumn;

    @FXML
    private TableColumn<Activity, String> activityColumn;

    @FXML
    private Button goToItemEntry;
    @FXML
    private Button goToTransaksi;

    @FXML
    private void moveToTransaksi(ActionEvent event) throws IOException {
        // Load the login page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("transaksi.fxml"));
        Parent root = loader.load();
        Dimension2D sceneSize = new Dimension2D(1080, 720);
        Scene scene = new Scene(root, sceneSize.getWidth(), sceneSize.getHeight());

        // Get the stage from the action event
        Stage stage = (Stage) goToTransaksi.getScene().getWindow();

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

    private ObservableList<Activity> getItemsForDate(LocalDate date) {
        // Get the items from the App instance
        Activity[] itemsDb = App.getActivity();

        // Create an ObservableList to store filtered items
        ObservableList<Activity> filteredActivities = FXCollections.observableArrayList();

        for (Activity activity : itemsDb) {
            LocalDate itemDate = getLocalDateFromDateTime(activity.getDatetime());
            if (date.isEqual(itemDate)) {
                filteredActivities.add(activity);
            }
        }

        // Return the filtered items as an ObservableList
        return filteredActivities;
    }

    private LocalDate getLocalDateFromDateTime(String dateTimeString) {
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return dateTime.toLocalDate();
    }

    @FXML
    private void inputDateTime(ActionEvent event) throws IOException {
        LocalDate selectedDate = datePicker.getValue();
        ObservableList<Activity> filteredActivities = getItemsForDate(selectedDate);

        tableView.setItems(filteredActivities);
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        activityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getActivity()));
        tableView.setOnMouseClicked(this::viewDetail);
    }

    private void viewDetail(MouseEvent event) {
        Activity selectedActivity = tableView.getSelectionModel().getSelectedItem();
        if (selectedActivity != null) {
            userTextField.setText(selectedActivity.getUser());
            timeTextField.setText(selectedActivity.getDatetime());
            descTextField.setText(selectedActivity.getDescription());
        }
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
}