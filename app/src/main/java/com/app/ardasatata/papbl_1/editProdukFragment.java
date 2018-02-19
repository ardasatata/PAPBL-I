package com.app.ardasatata.papbl_1;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.app.ardasatata.papbl_1.dbHelper.ProdukHelper;

/**
 * Created by ardasatata on 2/19/18.
 */

public class editProdukFragment extends DialogFragment {

    EditText nama;
    EditText harga;
    int id;
    ProdukHelper produkHelper;

    String aNama;
    int aHarga;

    public editProdukFragment() {

    }

    public static editProdukFragment newInstance(String title) {
        editProdukFragment frag = new editProdukFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        frag.setArguments(args);
        return frag;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_edit_produk,container,false);
        getDialog().setTitle("Add Produk");
        nama = (EditText) rootView.findViewById(R.id.editNamaP);
        harga = (EditText) rootView.findViewById(R.id.editHargaP);

        Button btOK = (Button) rootView.findViewById(R.id.editP);
        Button btCancel = rootView.findViewById(R.id.cancelPedit);

        produkHelper = new ProdukHelper(getActivity());

        String namaP=getArguments().getString("namaP");
        int hargaP=getArguments().getInt("hargaP");
        int idP=getArguments().getInt("idP");

        nama.setText(namaP);
        harga.setText(Integer.toString(hargaP));
        id=idP;

        btOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProduk();
                getDialog().hide();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    private void editProduk(){
        produkHelper.open();
        Produk produk = new Produk(id,nama.getText().toString(),Integer.parseInt(harga.getText().toString()));
        // Produk produk = new Produk(aNama,aHarga);
        produkHelper.update(produk);
        produkHelper.close();
    }


}
