package com.app.ardasatata.papbl_1;

/**
 * Created by ardasatata on 2/18/18.
 */

public class Penjualan {

    private int id;
    private int id_produk;
    private int total;

    public Penjualan() {
    }

    public Penjualan(int id_produk, int total) {
        this.id_produk = id_produk;
        this.total = total;
    }

    public Penjualan(int id, int id_produk, int total) {
        this.id = id;
        this.id_produk = id_produk;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_produk() {
        return id_produk;
    }

    public void setId_produk(int id_produk) {
        this.id_produk = id_produk;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
