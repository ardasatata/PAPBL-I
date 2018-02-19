package com.app.ardasatata.papbl_1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.app.ardasatata.papbl_1.dbHelper.PenjualanHelper;
import com.app.ardasatata.papbl_1.dbHelper.ProdukHelper;

/**
 * Created by ardasatata on 2/19/18.
 */

public class addProdukFragment extends DialogFragment {

    EditText nama;
    EditText harga;
    ProdukHelper produkHelper;

    String aNama;
    int aHarga;

    public addProdukFragment() {

    }

    public static addProdukFragment newInstance(String title) {
        addProdukFragment frag = new addProdukFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        frag.setArguments(args);
        return frag;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_add_produk,container,false);
        getDialog().setTitle("Add Produk");
        nama = (EditText) rootView.findViewById(R.id.tambahNamaProduk);
        harga = (EditText) rootView.findViewById(R.id.tambahHargaProduk);

        Button btOK = (Button) rootView.findViewById(R.id.buttonTambah);

        produkHelper = new ProdukHelper(getActivity());

        btOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertProduk();
                getDialog().hide();
            }
        });

        return rootView;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//
//        // Edited: Overriding onCreateView is not necessary in your case
//        LayoutInflater inflater = LayoutInflater.from(getActivity());
//        View newFileView = inflater.inflate(R.layout.dialog_add_produk, null);
//
//        produkHelper = new ProdukHelper(getActivity());
//
//
//        return new AlertDialog.Builder(getActivity())
//                .setTitle("Add Produk")
//                .setPositiveButton("Add",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//
//
//
//                            insertProduk();
//
//                            }
//                        }
//                )
//                .setNegativeButton("Cancel",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//
//                            }
//                        }
//                ).setView(newFileView)
//                .create();
//    }


    private void insertProduk(){
        produkHelper.open();
        Produk produk = new Produk(nama.getText().toString(),Integer.parseInt(harga.getText().toString()));
       // Produk produk = new Produk(aNama,aHarga);
        produkHelper.insert(produk);
        produkHelper.close();

    }


}
