package com.example.posproject;

public class ItemTransaksi {
    public int id;
    public int idTransaksi;
    public String kode;
    public String nama;
    public String harga;
    public String jumlah;
    public String total;

    public ItemTransaksi(int idTransaksi, String kode, String nama, String harga, String jumlah, String total) {
        this.idTransaksi = idTransaksi;
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.jumlah = jumlah;
        this.total = total;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }
    public String getKode() {
        return kode;
    }
    public String getNama() {
        return nama;
    }
    public String getHarga() {return harga;}
    public String getJumlah() {
        return jumlah;
    }
    public String getTotal() {
        return total;
    }



}
