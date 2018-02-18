package com.app.ardasatata.papbl_1.dbHelper;

import android.provider.BaseColumns;

/**
 * Created by ardasatata on 2/18/18.
 */

public class DatabaseContract {

    static String TABLE_PRODUK = "table_produk";

    static final class ProdukColumns implements BaseColumns {

        // Model nama
        static String NAMA = "nama";
        // model harga
        static String HARGA = "harga";

    }

    static String TABLE_PENJUALAN = "table_penjualan";

    static final class PenjualanColumns implements BaseColumns {

        // Model id_produk[
        static String ID_PRODUK = "id_produk";
        // model total
        static String TOTAL_PRODUK = "total";

    }
}
