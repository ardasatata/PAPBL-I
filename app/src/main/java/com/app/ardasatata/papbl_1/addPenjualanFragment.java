package com.app.ardasatata.papbl_1;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.ardasatata.papbl_1.dbHelper.PenjualanHelper;
import com.app.ardasatata.papbl_1.dbHelper.ProdukHelper;

/**
 * Created by ardasatata on 2/19/18.
 */

public class addPenjualanFragment extends DialogFragment {

    EditText totalProduk;
    EditText idProduk;
    ProdukHelper produkHelper;
    PenjualanHelper penjualanHelper;


    public addPenjualanFragment() {

    }

    public static addPenjualanFragment newInstance(String title) {
        addPenjualanFragment frag = new addPenjualanFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        frag.setArguments(args);
        return frag;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_add_penjualan,container,false);
        getDialog().setTitle("Tambah Penjualan");

        totalProduk = rootView.findViewById(R.id.tambahTotalJual);
        idProduk = rootView.findViewById(R.id.tambahJualID);


        Button btOK = (Button) rootView.findViewById(R.id.buttonTambahJual);
        Button btCancel = rootView.findViewById(R.id.buttonCancelJual);

        penjualanHelper = new PenjualanHelper(getActivity());

        btOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertJual();
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


    private void insertJual(){
        penjualanHelper.open();
        Penjualan penjualan = new Penjualan(Integer.parseInt(idProduk.getText().toString()),Integer.parseInt(totalProduk.getText().toString()));
        penjualanHelper.insert(penjualan);
        penjualanHelper.close();

    }


}
