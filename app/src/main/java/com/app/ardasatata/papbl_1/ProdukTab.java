package com.app.ardasatata.papbl_1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.TextView;

import com.app.ardasatata.papbl_1.dbHelper.ProdukHelper;

import java.util.ArrayList;

/**
 * Created by ardasatata on 2/18/18.
 */

public class ProdukTab extends Fragment{

    FloatingActionButton addProduk;
    private RecyclerView recyclerProduk;
    private ProdukAdapter produkAdapter;
    private ProdukHelper produkHelper;

    private TextView search;
    private Button searchBtn;
    private Button refreshP;


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
        recyclerProduk = rootview.findViewById(R.id.recyclerProduk);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerProduk.setLayoutManager(layoutManager);

        refreshP = rootview.findViewById(R.id.refreshP);
        searchBtn = rootview.findViewById(R.id.cariP);
        search = rootview.findViewById(R.id.searchP);

        produkAdapter = new ProdukAdapter(getContext());
        produkHelper = new ProdukHelper(getContext());

        getAllProduk();

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getResult(search.getText().toString());
            }
        });

        refreshP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllProduk();
            }
        });

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


    void getAllProduk(){
        produkHelper.open();
        ArrayList<Produk> produk =produkHelper.getAllData();
        produkHelper.close();
        produkAdapter.addItem(produk);
        recyclerProduk.setAdapter(produkAdapter);

    }

    void getResult(String key){
        produkHelper.open();
        ArrayList<Produk> produk =produkHelper.search(key);
        produkHelper.close();
        produkAdapter.addItem(produk);
        recyclerProduk.setAdapter(produkAdapter);

    }
}
