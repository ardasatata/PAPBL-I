package com.app.ardasatata.papbl_1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

/**
 * Created by ardasatata on 2/18/18.
 */

public class ProdukTab extends Fragment{

    FloatingActionButton addProduk;


    void ShowAddProduk(){

        addProdukFragment produkAdd = new addProdukFragment();
        produkAdd.show(getActivity().getFragmentManager(),"dialog");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.tab_produk,container,false);

        return rootview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        addProduk = (FloatingActionButton) view.findViewById(R.id.addProduk);

        addProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShowAddProduk();

            }
        });
    }
}
