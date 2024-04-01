package com.example.posproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ItemEntryController {


    @FXML
    private TableView<Item> tableView;

    @FXML
    private TableColumn<Item, String> kodeColumn;

    @FXML
    private TableColumn<Item, String> namaColumn;

    @FXML
    private TableColumn<Item, Float> hargaColumn;

    @FXML
    private TextField kodeField;

    @FXML
    private TextField namaField;

    @FXML
    private TextField hargaField;

    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button logoutButton;

    @FXML
    private Label errorLabel;

    @FXML
    private Button goToTransaksi;
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

    private ObservableList<Item> itemList = FXCollections.observableArrayList();

    private void updateItemInDatabase(Item item) {
        String url = "jdbc:mysql://localhost:3306/posproject";
        String username = "root";
        String password = "";

        String sql = "UPDATE barang SET nama = ?, harga = ? WHERE kode = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, App.getCurrentUser());
            preparedStatement.setFloat(2, item.getHarga());
            preparedStatement.setString(3, item.getKode());

            preparedStatement.executeUpdate();
            System.out.println("Data berhasil diperbarui di dalam database.");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    @FXML
    public void initialize() {
        kodeColumn.setCellValueFactory(cellData -> cellData.getValue().kodeProperty());
        namaColumn.setCellValueFactory(cellData -> cellData.getValue().namaProperty());
        hargaColumn.setCellValueFactory(cellData -> cellData.getValue().hargaProperty().asObject());

        // Ambil data dari database dan tambahkan ke dalam itemList
        fetchDataFromDatabase();

        tableView.setItems(itemList);

        // Panggil method populateFormFromSelectedItem() saat aplikasi dimuat
        populateFormFromSelectedItem();

    }

    @FXML
    public void onAddButtonClick() {
        String kode = kodeField.getText();
        String nama = namaField.getText();
        String hargaStr = hargaField.getText();

        if (kode.isEmpty() || nama.isEmpty() || hargaStr.isEmpty()) {
            errorLabel.setText("Semua field harus diisi!");
            return;
        }

        try {
            float harga = Float.parseFloat(hargaStr);
            if (harga <= 0) {
                errorLabel.setText("Harga harus lebih besar dari 0!");
                return;
            }
            saveItemToDatabase(kode, nama, harga);
            resetFields();
            fetchDataFromDatabase();
        } catch (NumberFormatException e) {
            errorLabel.setText("Harga harus berupa angka!");
        }
        addActivity("Tambah", App.getCurrentUser(), LocalDateTime.now(), "Menambahkan item baru");
    }

    @FXML
    public void onEditButtonClick() {
        // Mendapatkan item yang dipilih dari tabel
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Mendapatkan nilai baru dari field input
            String newKode = kodeField.getText();
            String newNama = namaField.getText();
            String newHargaStr = hargaField.getText();

            // Validasi input
            if (newKode.isEmpty() || newNama.isEmpty() || newHargaStr.isEmpty()) {
                errorLabel.setText("Semua field harus diisi!");
                return;
            }

            try {
                float newHarga = Float.parseFloat(newHargaStr);
                if (newHarga <= 0) {
                    errorLabel.setText("Harga harus lebih besar dari 0!");
                    return;
                }

                // Memperbarui nilai item yang dipilih dengan nilai baru
                selectedItem.setKode(newKode);
                selectedItem.setNama(newNama);
                selectedItem.setHarga(newHarga);

                // Memperbarui data di database
                updateItemInDatabase(selectedItem);

                // Mengambil ulang data dari database
                fetchDataFromDatabase();
            } catch (NumberFormatException e) {
                errorLabel.setText("Harga harus berupa angka!");
            }
            // Membersihkan field input setelah melakukan edit
            resetFields();
        } else {
            errorLabel.setText("Pilih item yang ingin diedit!");
        }
        addActivity("Edit", App.getCurrentUser(), LocalDateTime.now(), "Mengedit item");
    }


    @FXML
    public void onDeleteButtonClick() {
        // Implementasi operasi hapus item
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            deleteItemFromDatabase(selectedItem);
            itemList.remove(selectedItem);
        } else {
            errorLabel.setText("Pilih item yang ingin dihapus!");
        }
        addActivity("Hapus", App.getCurrentUser(), LocalDateTime.now(), "Menghapus item");
    }

    // Method untuk mendapatkan nama pengguna yang sedang aktif


    private void saveItemToDatabase(String kode, String nama, float harga) {
        String url = "jdbc:mysql://localhost:3306/posproject";
        String username = "root";
        String password = "";

        String sql = "INSERT INTO barang (kode, nama, harga) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, kode);
            preparedStatement.setString(2, nama);
            preparedStatement.setFloat(3, harga);

            preparedStatement.executeUpdate();
            System.out.println("Data berhasil disimpan ke dalam database.");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteItemFromDatabase(Item item) {
        String url = "jdbc:mysql://localhost:3306/posproject";
        String username = "root";
        String password = "";

        String sql = "DELETE FROM barang WHERE kode = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, item.getKode());

            preparedStatement.executeUpdate();
            System.out.println("Data berhasil dihapus dari database.");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void resetFields() {
        kodeField.clear();
        namaField.clear();
        hargaField.clear();
        errorLabel.setText("");
    }

    private void fetchDataFromDatabase() {
        itemList.clear();
        String url = "jdbc:mysql://localhost:3306/posproject";
        String username = "root";
        String password = "";

        String sql = "SELECT * FROM barang";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String kode = resultSet.getString("kode");
                String nama = resultSet.getString("nama");
                float harga = resultSet.getFloat("harga");
                itemList.add(new Item(kode, nama, harga));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method untuk mengisi nilai dari item yang dipilih ke dalam formulir
    private void populateFormFromSelectedItem() {
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            kodeField.setText(selectedItem.getKode());
            namaField.setText(selectedItem.getNama());
            hargaField.setText(String.valueOf(selectedItem.getHarga()));
        } else {
            // Clear the form fields if no item is selected
            kodeField.clear();
            namaField.clear();
            hargaField.clear();
        }
    }

    // Method untuk menambahkan catatan aktivitas ke dalam database
    private void addActivity(String activity, String user, LocalDateTime dateTime, String description) {

        String url = "jdbc:mysql://localhost:3306/posproject"; // Ganti dengan URL database Anda
        String username = "root"; // Ganti dengan username database Anda
        String password = ""; // Ganti dengan password database Anda

        String sql = "INSERT INTO activity (activity, user, datetime, description) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Set nilai parameter query
            preparedStatement.setString(1, activity);
            preparedStatement.setString(2, user);
            preparedStatement.setObject(3, dateTime);
            preparedStatement.setString(4, description);

            // Eksekusi query
            preparedStatement.executeUpdate();

            // Tampilkan pesan sukses jika data berhasil dimasukkan
            System.out.println("Data aktivitas berhasil dimasukkan ke dalam database.");
        } catch (SQLException e) {
            // Tangani jika terjadi error saat memasukkan data
            System.out.println("Error: " + e.getMessage());
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
