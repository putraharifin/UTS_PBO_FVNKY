/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fvnky.data;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author VIVOBOOK PRO
 */
public class Barang {
    public String kode;
    public Float harga;
    public String nama;
    public int index;
    public int jumlahBelanja;
    public float total;
    
//    public Barang(String kode, Float harga, String nama){
//        this.kode = kode;
//        this.harga = harga;
//        this.nama = nama;
//    }
    
    public String getKode(){
        return kode;
    }
    
    public float getHarga(){
        return harga;
    }
    
    public String getNama(){
        return nama;
    }
    
        // Getter dan setter untuk indeks
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    public int getJumlah() {
        return jumlahBelanja;
    }

    public void setJumlah(int jumlahBelanja) {
        this.jumlahBelanja = jumlahBelanja;
    }
    
    public float getTotal() {
        return total;
    }
    
    public void setTotal(int Total) {
        this.total = Total;
    }
    
    public static ArrayList<Barang> daftarBarang;
    
    public static void loadBarangFromDB(){
        daftarBarang = new ArrayList<Barang>();
        Barang barang;
        try {
            Statement stmt = DBConnector.connection.createStatement();
            String sql = "SELECT * from barang";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                barang = new Barang();
                barang.kode = rs.getString ("kode");
                barang.nama = rs.getString("nama");
                barang.harga = rs.getFloat("harga");
                
                daftarBarang.add(barang);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
}


