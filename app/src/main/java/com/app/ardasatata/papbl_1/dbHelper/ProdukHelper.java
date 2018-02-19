package com.app.ardasatata.papbl_1.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.app.ardasatata.papbl_1.Produk;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.app.ardasatata.papbl_1.dbHelper.DatabaseContract.TABLE_PRODUK;
import static com.app.ardasatata.papbl_1.dbHelper.DatabaseContract.ProdukColumns.NAMA;
import static com.app.ardasatata.papbl_1.dbHelper.DatabaseContract.ProdukColumns.HARGA;

/**
 * Created by ardasatata on 2/18/18.
 */

public class ProdukHelper {

    private Context context;
    private DatabaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public ProdukHelper(Context context) {
        this.context = context;
    }

    public ProdukHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }


    public ArrayList<Produk> getAllData() {
        Cursor cursor = database.query(TABLE_PRODUK, null, null, null, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<Produk> arrayList = new ArrayList<>();
        Produk produk;
        if (cursor.getCount() > 0) {
            do {
                produk = new Produk();
                produk.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                produk.setNama(cursor.getString(cursor.getColumnIndexOrThrow(NAMA)));
                produk.setHarga(cursor.getInt(cursor.getColumnIndexOrThrow(HARGA)));
                arrayList.add(produk);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<Produk> search(String key) {
        Cursor cursor = database.query(true, TABLE_PRODUK, null, NAMA + " LIKE ?",
                new String[] {"%"+ key+ "%" }, null, null, null,
                null);
        cursor.moveToFirst();
        ArrayList<Produk> arrayList = new ArrayList<>();
        Produk produk;
        if (cursor.getCount() > 0) {
            do {
                produk = new Produk();
                produk.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                produk.setNama(cursor.getString(cursor.getColumnIndexOrThrow(NAMA)));
                produk.setHarga(cursor.getInt(cursor.getColumnIndexOrThrow(HARGA)));
                arrayList.add(produk);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public String getNamaByID(int id) {
        String key = Integer.toString(id);
        Cursor cursor = database.query( TABLE_PRODUK, null, "_ID =?",new String[]{key}, null, null, null,
                null);
        cursor.moveToFirst();
        Produk  produk = new Produk();
                produk.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                produk.setNama(cursor.getString(cursor.getColumnIndexOrThrow(NAMA)));
                produk.setHarga(cursor.getInt(cursor.getColumnIndexOrThrow(HARGA)));
        cursor.close();
        return produk.getNama();
    }

    public long insert(Produk produk) {

        database.beginTransaction();
        try {
            ContentValues initialValues = new ContentValues();
            initialValues.put(NAMA, produk.getNama());
            initialValues.put(HARGA, produk.getHarga());
            return database.insert(TABLE_PRODUK, null, initialValues);
        }
        finally {
            database.setTransactionSuccessful();
            database.endTransaction();
        }


    }


    public int update(Produk produk) {

        database.beginTransaction();
        try{
            ContentValues args = new ContentValues();
            args.put(NAMA, produk.getNama());
            args.put(HARGA, produk.getHarga());
            return database.update(TABLE_PRODUK, args, _ID + "= '" + produk.getId() + "'", null);
        }
        finally {
            database.setTransactionSuccessful();
            database.endTransaction();
        }

    }


    public int delete(int id) {

        database.beginTransaction();
        try {
            return database.delete(TABLE_PRODUK, _ID + " = '" + id + "'", null);
        }
        finally {
            database.setTransactionSuccessful();
            database.endTransaction();
        }
    }

}
