package com.example.posproject;

public class Transaksi {

    public int idTransaksi;
    public String username;
    public String tanggal;
    public String waktu;
    public String deskripsi;
    public Float totalBelanja;

    public Transaksi(int idTransaksi, String username, String tanggal, String waktu, String deskripsi, float totalBelanja) {
        this.idTransaksi = idTransaksi;
        this.username = username;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.deskripsi = deskripsi;
        this.totalBelanja = totalBelanja;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }


    public String getUser() {
        return username;
    }

    public String getwaktu() {
        return waktu;
    }

    public String getdeskripsi() {
        return deskripsi;
    }
    public Float getTotalBelanja() {
        return totalBelanja;
    }
}

