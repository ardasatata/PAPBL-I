package com.app.ardasatata.papbl_1;

/**
 * Created by ardasatata on 2/18/18.
 */

public class Produk {
    private int id;
    private String nama;
    private int harga;

    public Produk() {
    }

    public Produk(String nama, int harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public Produk(int id, String nama, int harga) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}


