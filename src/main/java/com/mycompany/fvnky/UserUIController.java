/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.fvnky;

import static com.mycompany.fvnky.LoginController.getLoggedInUsername;
import com.mycompany.fvnky.data.Barang;
import com.mycompany.fvnky.data.DBConnector;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Dimension2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author VIVOBOOK PRO
 */
public class UserUIController implements Initializable {
    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colNo.setCellValueFactory(new PropertyValueFactory<>("no"));
        colKode.setCellValueFactory(new PropertyValueFactory<>("kode"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        colJumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));
        
        daftarBelanjaTable.setItems(belanjaList);
        
        
        // Tambahkan listener ke setiap sel di kolom jumlah
        colJumlah.setCellFactory(TextFieldTableCell.forTableColumn());
        colJumlah.setOnEditCommit(event -> {
            // Dapatkan nilai yang baru
            String newValue = event.getNewValue();
            // Dapatkan indeks baris yang diubah
            int rowIndex = event.getTablePosition().getRow();
            // Dapatkan barang pada baris tersebut
            Barang barang = belanjaList.get(rowIndex);
            // Perbarui jumlah barang
            barang.setJumlah(Integer.parseInt(newValue));
            // Perbarui total
            barang.setTotal((int) (barang.getJumlah() * barang.getHarga()));
            hitungTotalBelanja();
            // Perbarui TableView
            daftarBelanjaTable.refresh();
        });
        jumlahDibayarTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            hitungKembalian();
        });
        tanggal.setValue(LocalDate.now());
        
        userTextField.setText(LoginController.getLoggedInUsername());
        displayCurrentTime();
        startTimer();
        
    }
    

@FXML
public TextField kodeTextField;
@FXML
public TextField namaTextField;
@FXML
public TextField hargaTextField;


    ArrayList<Barang> daftarBarang;
        public UserUIController() {
            
        DBConnector.initDBConnection();
        
        Barang.loadBarangFromDB();
        daftarBarang = Barang.daftarBarang;
        
        System.out.println(daftarBarang.size());
        
    }
        

@FXML
private void kodeTextFieldActionPerformed() {                                              
        // TODO add your handling code here:
        System.out.println("Kode Action Performed by Pressing Enter");
    
    String kodeInput = kodeTextField.getText();
    
    Barang tempBarang = null;
    boolean barangDitemukan = false;
    // Periksa apakah objek Barang dengan kode yang sama sudah ada di dalam belanjaList
    for (int i = 0; i < belanjaList.size(); i++) {
        if (belanjaList.get(i).getKode().equals(kodeInput)) {
            tempBarang = belanjaList.get(i);
            barangDitemukan = true;
            break;
        }
    }
    
    // Jika barang sudah ada, tidak perlu menambahkannya lagi ke dalam belanjaList
    if (barangDitemukan) {
        System.out.println("Barang Sudah Ditemukan");
        namaTextField.setText(tempBarang.getNama());
        hargaTextField.setText(Float.toString(tempBarang.getHarga()));
    } else {
        // Barang belum ada, tambahkan ke dalam belanjaList
        for (int i = 0; i < daftarBarang.size(); i++){
            tempBarang = daftarBarang.get(i);
            
            if (tempBarang.getKode().equals(kodeInput)){
                System.out.println("Barang Ditemukan");
                tempBarang.setIndex(belanjaList.size() + 1);
                tempBarang.setJumlah(1);
                tempBarang.setTotal((int) tempBarang.getHarga());
                namaTextField.setText(tempBarang.getNama());
                hargaTextField.setText(Float.toString(tempBarang.getHarga()));
                belanjaList.add(tempBarang); // Tambahkan barang yang ditemukan ke dalam model data
                showTable();
                hitungTotalBelanja();
                break; // Keluar dari loop setelah menemukan barang yang sesuai
            }
        }
    }
    }                                             

@FXML
public TableView<Barang> daftarBelanjaTable;
@FXML
public TableColumn<Barang, String> colNo;
@FXML
public TableColumn<Barang, String> colKode;
@FXML
public TableColumn<Barang, String> colNama;
@FXML
public TableColumn<Barang, String> colHarga;
@FXML
public TableColumn<Barang, String> colJumlah;
@FXML
public TableColumn<Barang, String> colTotal;
@FXML
public TableColumn colDelete;


private ObservableList<Barang> belanjaList = FXCollections.observableArrayList();

private void showTable(){
        colNo.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getIndex())));
        colKode.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKode()));
        colNama.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNama()));
        colHarga.setCellValueFactory(cellData -> new SimpleStringProperty(Float.toString(cellData.getValue().getHarga())));
        colJumlah.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getJumlah())));
        colTotal.setCellValueFactory(cellData -> new SimpleStringProperty(Float.toString(cellData.getValue().getTotal())));
        colDelete.setCellFactory(col -> {
        TableCell<Barang, Void> cell = new TableCell<Barang, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Barang barang = getTableView().getItems().get(getIndex());
                    // Tampilkan peringatan sebelum menghapus
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Delete Confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to delete this item?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        deleteItem(barang);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        };
        return cell;
        });
        // Tetapkan data ke TableView
        daftarBelanjaTable.setItems(belanjaList);
        hitungTotalBelanja();
}

@FXML
public TextField totalBelanja;
@FXML
public TextField jumlahDibayarTextField;
@FXML
public TextField Kembalian;

private void hitungTotalBelanja() {
    float jumlahBelanja = 0;
    for (Barang barang : belanjaList) {
        jumlahBelanja += barang.getTotal();
    }
    totalBelanja.setText(Float.toString(jumlahBelanja));
    hitungKembalian();
}

private void hitungKembalian(){
    try {
        float jumlahBelanja = Float.parseFloat(totalBelanja.getText());
        float jumlahDibayar = Float.parseFloat(jumlahDibayarTextField.getText());
        float kembalian = jumlahDibayar - jumlahBelanja;
        Kembalian.setText(Float.toString(kembalian));
    } catch (NumberFormatException e) {
        // Tangani jika pengguna memasukkan input yang tidak valid
        Kembalian.setText("Invalid input");
    }
}

@FXML
public DatePicker tanggal;

@FXML
public TextField userTextField;

private void deleteItem(Barang barang) {
    belanjaList.remove(barang);
    updateIndexes();
    showTable();
    hitungTotalBelanja();
}

private void updateIndexes() {
    for (int i = 0; i < belanjaList.size(); i++) {
        belanjaList.get(i).setIndex(i + 1);
    }
}


@FXML
public TextField waktuTextField;

// Method untuk menampilkan jam sekarang pada waktuTextField
private void displayCurrentTime() {
    // Dapatkan waktu saat ini
    LocalTime currentTime = LocalTime.now();
    
    // Format waktu menggunakan DateTimeFormatter
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); // Format waktu: jam:menit:detik
    String formattedTime = currentTime.format(formatter);
    
    // Set nilai formattedTime ke waktuTextField
    waktuTextField.setText(formattedTime);
}

private AnimationTimer timer;

// Method untuk memulai timer
    private void startTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Panggil method untuk memperbarui waktu
                updateCurrentTime();
            }
        };
        // Mulai timer
        timer.start();
    }
    
    // Method untuk memperbarui waktu saat ini dan menampilkannya di waktuTextField
    private void updateCurrentTime() {
        // Dapatkan waktu saat ini
        LocalTime currentTime = LocalTime.now();

        // Format waktu menggunakan DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); // Format waktu: jam:menit:detik
        String formattedTime = currentTime.format(formatter);

        // Set nilai formattedTime ke waktuTextField
        waktuTextField.setText(formattedTime);
    }
    
    @FXML
    public Button saveButton;
    
    @FXML
    private void saveButtonAction(ActionEvent event) {
        // Validasi apakah jumlah yang dibayarkan mencukupi untuk total belanja
        try {
            float totalBelanjaValue = Float.parseFloat(totalBelanja.getText());
            float jumlahDibayarValue = Float.parseFloat(jumlahDibayarTextField.getText());

            if (jumlahDibayarValue < totalBelanjaValue) {
                // Tampilkan alert jika jumlah yang dibayarkan tidak mencukupi
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Insufficient Payment");
                alert.setHeaderText(null);
                alert.setContentText("The payment amount is insufficient. Please enter a sufficient amount.");

                alert.showAndWait();
            } else {
                // Tampilkan alert konfirmasi sebelum menyimpan data
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to save the transaction?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // Jika pengguna menekan tombol OK, simpan data
                    addActivity();
                    addTransaction();
                    // Setelah menyimpan data, bersihkan TableView
                    belanjaList.clear();
                    showTable();
                    // Bersihkan juga total belanja, jumlah dibayar, dan kembalian
                    totalBelanja.clear();
                    jumlahDibayarTextField.clear();
                    Kembalian.clear();
                    // Bersihkan juga total kode, jumlah nama, dan harga
                    kodeTextField.clear();
                    namaTextField.clear();
                    hargaTextField.clear();
                } else {
                    // Jika pengguna menekan tombol Cancel atau menutup alert, tidak ada tindakan yang diambil
                    System.out.println("Transaction save cancelled.");
                }
            }
        } catch (NumberFormatException e) {
            // Tangani jika input jumlah belanja atau jumlah dibayar tidak valid
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid numeric values for Total Belanja and Jumlah Dibayar.");

            alert.showAndWait();
        }
    }
    
    private void addActivity (){
        // Ambil data dari UI
    String activity = "Transaksi " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // Isi dengan activity dari UI
    String user = userTextField.getText(); // Ambil user dari UI
    LocalDateTime dateTime = LocalDateTime.now(); // Ambil waktu saat ini
    
    // Hitung total barang
    int totalBarang = 0;
    for (Barang barang : belanjaList) {
        totalBarang += barang.getJumlah();
    }
    
    // Ambil jumlah dibayar
    float jumlahDibayar = Float.parseFloat(jumlahDibayarTextField.getText());
    
    // Ambil kembalian
    float kembalian = Float.parseFloat(Kembalian.getText());
    
    // Bangun deskripsi
    String description = "Total Barang: " + totalBarang
                       + ", Total Belanja: " + totalBelanja.getText()
                       + ", Jumlah Dibayar: " + jumlahDibayar
                       + ", Kembalian: " + kembalian;
    
    // Masukkan data ke dalam tabel activity di database
    insertActivity(activity, user, dateTime, description);
    }
    
     // Method untuk memasukkan data ke dalam tabel activity di database
    private void insertActivity(String activity, String user, LocalDateTime dateTime, String description) {
        // SQL query untuk memasukkan data ke dalam tabel activity
        String query = "INSERT INTO activity (activity, user, datetime, description) VALUES ( ?, ?, ?, ?)";

        try (Connection connection = DBConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            // Set nilai parameter query
            statement.setString(1, activity);
            statement.setString(2, user);
            statement.setObject(3, dateTime);
            statement.setString(4, description);

            // Eksekusi query
            statement.executeUpdate();

            // Tampilkan pesan sukses jika data berhasil dimasukkan
            System.out.println("Data berhasil dimasukkan ke dalam tabel activity.");
        } catch (SQLException e) {
            // Tangani jika terjadi error saat memasukkan data
            e.printStackTrace();
        }
        
    }
    
private void addTransaction() {
    // Ambil data dari UI
    String user = userTextField.getText();
    LocalDateTime dateTime = LocalDateTime.now();
    Float seluruhBelanja = Float.parseFloat(totalBelanja.getText());

    // Bangun deskripsi
    StringBuilder descriptionBuilder = new StringBuilder();
    descriptionBuilder.append("Transaksi pada tanggal ").append(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    descriptionBuilder.append(", Total Barang: ").append(belanjaList.size());
    descriptionBuilder.append(", Total Belanja: ").append(totalBelanja);
    descriptionBuilder.append(", Jumlah Dibayar: ").append(jumlahDibayarTextField.getText());
    descriptionBuilder.append(", Kembalian: ").append(Kembalian.getText());
    String description = descriptionBuilder.toString();

    // Masukkan data ke dalam tabel transaksi
    int idTransaksi = insertTransaction(user, dateTime, description, seluruhBelanja);

    // Masukkan data barang ke dalam tabel item_transaksi
    insertItemTransactions(idTransaksi);
}

private int insertTransaction(String user, LocalDateTime dateTime, String description, float totalBelanja) {
    // SQL query untuk memasukkan data ke dalam tabel transaksi
    String query = "INSERT INTO transaksi (user, tanggal, waktu, deskripsi, totalBelanja) VALUES (?, ?, ?, ?, ?)";

    try (Connection connection = DBConnector.getConnection();
         PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
        // Set nilai parameter query
        statement.setString(1, user);
        statement.setObject(2, dateTime.toLocalDate()); // Set tanggal
        statement.setObject(3, dateTime.toLocalTime()); // Set waktu
        statement.setString(4, description);
        statement.setFloat(5, totalBelanja);

        // Eksekusi query
        statement.executeUpdate();

        // Ambil ID transaksi yang baru saja dimasukkan
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        } else {
            throw new SQLException("Failed to get generated ID for transaction.");
        }
    } catch (SQLException e) {
        // Tangani jika terjadi error saat memasukkan data
        e.printStackTrace();
        return -1; // Return -1 jika terjadi kesalahan
    }
}

private void insertItemTransactions(int idTransaksi) {
    // SQL query untuk memasukkan data barang ke dalam tabel item_transaksi
    String query = "INSERT INTO itemTransaksi (idTransaksi, kode, nama, harga, jumlah, total) VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection connection = DBConnector.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
        // Masukkan setiap barang dalam daftar belanja ke dalam tabel item_transaksi
        for (Barang barang : belanjaList) {
            // Set nilai parameter query
            statement.setInt(1, idTransaksi);
            statement.setString(2, barang.getKode());
            statement.setString(3, barang.getNama());
            statement.setFloat(4, barang.getHarga());
            statement.setInt(5, barang.getJumlah());
            statement.setFloat(6, barang.getTotal());

            // Eksekusi query
            statement.executeUpdate();
        }

        // Tampilkan pesan sukses jika data berhasil dimasukkan
        System.out.println("Data barang berhasil dimasukkan ke dalam tabel item_transaksi.");
    } catch (SQLException e) {
        // Tangani jika terjadi error saat memasukkan data
        e.printStackTrace();
    }
}

@FXML
public Button LogoutButton;

@FXML
private void logout(ActionEvent event) throws IOException {
    // Tampilkan alert konfirmasi sebelum logout
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Logout Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to logout?");
    
    // Tambahkan opsi OK dan Cancel ke dalam alert
    ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
    ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
    alert.getButtonTypes().setAll(okButton, cancelButton);
    
    // Tampilkan alert dan tunggu respons pengguna
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == okButton) {
        // Jika pengguna menekan OK, lakukan logout
        int userId = getUserIdFromUsername(getLoggedInUsername()); // Ambil ID pengguna dari nama pengguna yang sedang login
        saveLogoutActivity(userId); // Panggil metode untuk menyimpan aktivitas logout
        updateSessionStatusOnClose("0"); // Perbarui status sesi pengguna menjadi 0 saat logout

        // Lakukan proses logout seperti biasa
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        Dimension2D sceneSize = new Dimension2D(1080, 720);
        Scene scene = new Scene(root, sceneSize.getWidth(), sceneSize.getHeight());

        Stage stage = (Stage) LogoutButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } else {
        // Jika pengguna menekan Cancel atau menutup alert, tidak melakukan logout
        System.out.println("Logout cancelled.");
    }
}

// Method untuk menyimpan aktivitas logout ke dalam tabel activity
private void saveLogoutActivity(int userId) {
    String activity = "Logout"; // Aktivitas adalah Logout
    String user = getLoggedInUsername(); // Ambil nama pengguna yang sedang login
    LocalDateTime dateTime = LocalDateTime.now(); // Ambil waktu saat ini
    
    // Bangun deskripsi aktivitas logout
    String description = "logged out from User App ";
    
    // Panggil metode untuk menyimpan aktivitas logout ke dalam tabel activity
    insertActivity(activity, user, dateTime, description);
}

// Method untuk mendapatkan ID pengguna berdasarkan nama pengguna
private int getUserIdFromUsername(String username) {
    int userId = -1; // Inisialisasi nilai default jika pengguna tidak ditemukan
    String query = "SELECT id FROM user WHERE username = ?";

    try (Connection connection = DBConnector.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
        // Set nilai parameter query
        statement.setString(1, username);

        // Eksekusi query
        ResultSet resultSet = statement.executeQuery();

        // Ambil ID pengguna jika ada hasil dari query
        if (resultSet.next()) {
            userId = resultSet.getInt("id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return userId;
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