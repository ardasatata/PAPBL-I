package com.app.ardasatata.papbl_1.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.app.ardasatata.papbl_1.Penjualan;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.app.ardasatata.papbl_1.dbHelper.DatabaseContract.TABLE_PENJUALAN;
import static com.app.ardasatata.papbl_1.dbHelper.DatabaseContract.PenjualanColumns.ID_PRODUK;
import static com.app.ardasatata.papbl_1.dbHelper.DatabaseContract.PenjualanColumns.TOTAL_PRODUK;

/**
 * Created by ardasatata on 2/18/18.
 */

public class PenjualanHelper {

    private Context context;
    private DatabaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public PenjualanHelper(Context context) {
        this.context = context;
    }

    public PenjualanHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }


    public ArrayList<Penjualan> getAllData() {
        Cursor cursor = database.query(TABLE_PENJUALAN, null, null, null, null, null, _ID + " DESC", null);
        cursor.moveToFirst();
        ArrayList<Penjualan> arrayList = new ArrayList<>();
        Penjualan penjualan;
        if (cursor.getCount() > 0) {
            do {
                penjualan = new Penjualan();
                penjualan.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                penjualan.setId_produk(cursor.getInt(cursor.getColumnIndexOrThrow(ID_PRODUK)));
                penjualan.setTotal(cursor.getInt(cursor.getColumnIndexOrThrow(TOTAL_PRODUK)));
                arrayList.add(penjualan);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(Penjualan penjualan) {

        database.beginTransaction();
        try {
            ContentValues initialValues = new ContentValues();
            initialValues.put(ID_PRODUK, penjualan.getId_produk());
            initialValues.put(TOTAL_PRODUK, penjualan.getTotal());
            return database.insert(TABLE_PENJUALAN, null, initialValues);
        }
        finally {
            database.setTransactionSuccessful();
            database.endTransaction();
        }
    }


    public int update(Penjualan penjualan) {

        database.beginTransaction();
        try {
            ContentValues args = new ContentValues();
            args.put(ID_PRODUK, penjualan.getId_produk());
            args.put(TOTAL_PRODUK, penjualan.getTotal());
            return database.update(TABLE_PENJUALAN, args, _ID + "= '" + penjualan.getId() + "'", null);
        }
        finally {
            database.setTransactionSuccessful();
            database.endTransaction();
        }

    }


    public int delete(int id) {

        database.beginTransaction();
        try {
            return database.delete(TABLE_PENJUALAN, _ID + " = '" + id + "'", null);
        }
        finally {
            database.setTransactionSuccessful();
            database.endTransaction();
        }
    }

}
