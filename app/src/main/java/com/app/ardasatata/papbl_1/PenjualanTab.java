package com.app.ardasatata.papbl_1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.ardasatata.papbl_1.dbHelper.PenjualanHelper;
import com.app.ardasatata.papbl_1.dbHelper.ProdukHelper;

import java.util.ArrayList;

/**
 * Created by ardasatata on 2/18/18.
 */

public class PenjualanTab extends Fragment {

    FloatingActionButton addJual;
    private RecyclerView recyclerJual;
    private PenjualanAdapter penjualanAdapter;
    private PenjualanHelper penjualanHelper;

    private Button refreshJ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.tab_penjualan,container,false);

        recyclerJual = rootview.findViewById(R.id.recyclerJual);
        refreshJ = rootview.findViewById(R.id.refreshJ);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerJual.setLayoutManager(layoutManager);

        refreshJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllPenjualan();
            }
        });


        getAllPenjualan();

        return rootview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        addJual = (FloatingActionButton) view.findViewById(R.id.addJual);

        addJual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShowAddJual();
                getAllPenjualan();

            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        penjualanAdapter = new PenjualanAdapter(getContext());
        penjualanHelper = new PenjualanHelper(getContext());


    }

    void ShowAddJual(){

        addPenjualanFragment penjualanAdd = new addPenjualanFragment();
        penjualanAdd.show(getActivity().getFragmentManager(),"dialog");
    }

    void getAllPenjualan(){
        penjualanHelper.open();
        ArrayList<Penjualan> penjualan =penjualanHelper.getAllData();
        penjualanHelper.close();
        penjualanAdapter.addItem(penjualan);
        recyclerJual.setAdapter(penjualanAdapter);

    }
}
