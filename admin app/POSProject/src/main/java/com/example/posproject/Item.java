package com.example.posproject;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Item {
    private final StringProperty kode;
    private final StringProperty nama;
    private final FloatProperty harga;

    public Item(String kode, String nama, float harga) {
        this.kode = new SimpleStringProperty(kode);
        this.nama = new SimpleStringProperty(nama);
        this.harga = new SimpleFloatProperty(harga);
    }

    public StringProperty kodeProperty() {
        return kode;
    }

    public StringProperty namaProperty() {
        return nama;
    }

    public FloatProperty hargaProperty() {
        return harga;
    }

    public String getKode() {
        return kode.get();
    }

    public void setKode(String kode) {
        this.kode.set(kode);
    }

    public String getNama() {
        return nama.get();
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }

    public float getHarga() {
        return harga.get();
    }

    public void setHarga(float harga) {
        this.harga.set(harga);
    }
}
