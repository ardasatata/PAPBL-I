package com.app.ardasatata.papbl_1.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.app.ardasatata.papbl_1.dbHelper.DatabaseContract.TABLE_PRODUK;
import static com.app.ardasatata.papbl_1.dbHelper.DatabaseContract.ProdukColumns.NAMA;
import static com.app.ardasatata.papbl_1.dbHelper.DatabaseContract.ProdukColumns.HARGA;

import static com.app.ardasatata.papbl_1.dbHelper.DatabaseContract.TABLE_PENJUALAN;
import static com.app.ardasatata.papbl_1.dbHelper.DatabaseContract.PenjualanColumns.ID_PRODUK;
import static com.app.ardasatata.papbl_1.dbHelper.DatabaseContract.PenjualanColumns.TOTAL_PRODUK;

/**
 * Created by ardasatata on 2/18/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_PRODUK = "create table " + TABLE_PRODUK +
            " (" + _ID + " integer primary key autoincrement, " +
            NAMA + " text not null, " +
            HARGA + " integer not null);";

    public static String CREATE_TABLE_PENJUALAN = "create table " + TABLE_PENJUALAN +
            " (" + _ID + " integer primary key autoincrement, " +
            ID_PRODUK + " integer not null, " +
            TOTAL_PRODUK + " integer not null);";

    private static String DATABASE_NAME = "dbPenjualan";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PRODUK);
        db.execSQL(CREATE_TABLE_PENJUALAN);

    }

    /*
    Method onUpgrade akan di panggil ketika terjadi perbedaan versi
    Gunakan method onUpgrade untuk melakukan proses migrasi data
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
        Drop table tidak dianjurkan ketika proses migrasi terjadi dikarenakan data user akan hilang,
         */
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PENJUALAN);
        onCreate(db);
    }


}
