package com.app.ardasatata.papbl_1;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.ardasatata.papbl_1.dbHelper.PenjualanHelper;
import com.app.ardasatata.papbl_1.dbHelper.ProdukHelper;

import java.util.ArrayList;

/**
 * Created by ardasatata on 2/19/18.
 */

public class PenjualanAdapter extends RecyclerView.Adapter<PenjualanAdapter.CustomViewHolder>{

    private LayoutInflater mInflater;
    private ArrayList<Penjualan> penjualan;
    private ArrayList<Produk> produk;
    private Context context;
    private Context mContext;
    private PenjualanHelper penjualanHelper;
    private ProdukHelper produkHelper;

    public PenjualanAdapter(Context context){
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        penjualanHelper = new PenjualanHelper(context);
        produkHelper = new ProdukHelper(context);
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                viewGroup.getContext());
        View v =
                inflater.inflate(R.layout.row_jual, viewGroup, false);
        // set the view's size, margins, paddings and layout parameters
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PenjualanAdapter.CustomViewHolder holder, final int position) {

        final int idProduk = penjualan.get(position).getId_produk();
        final int totalProduk = penjualan.get(position).getTotal();

        final int id = penjualan.get(position).getId();

        getData();

        produkHelper.open();
        final String namaProduk = produkHelper.getNamaByID(idProduk);
        produkHelper.close();

        holder.namaProduk.setText(namaProduk);
        holder.totalProduk.setText(Integer.toString(totalProduk));
        //holder.idProduk.setText(Integer.toString(idProduk));

        if (totalProduk==0){

            penjualanHelper.open();
            penjualanHelper.delete(id);
            penjualanHelper.close();

        }


        holder.kurangJual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                penjualanHelper.open();
                Penjualan minus = new Penjualan(id,idProduk,(totalProduk-1));
                penjualanHelper.update(minus);
                penjualanHelper.close();
                Toast.makeText(context, "terjual", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

        holder.plusJual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                penjualanHelper.open();
                Penjualan plus = new Penjualan(id,idProduk,(totalProduk+1));
                penjualanHelper.update(plus);
                penjualanHelper.close();
                Toast.makeText(context, "tidak jadi terjual", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

        holder.deleteJual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                penjualanHelper.open();
                penjualanHelper.delete(id);
                penjualanHelper.close();
                Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return penjualan.size();
    }

    public void addItem(ArrayList<Penjualan> mData) {
        this.penjualan = mData;
        notifyDataSetChanged();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView namaProduk;
        private TextView totalProduk;
        private Button kurangJual;
        private Button  plusJual;
        private Button deleteJual;
        //private TextView idProduk;

        public CustomViewHolder(View itemView) {
            super(itemView);

            namaProduk = itemView.findViewById(R.id.namaProdukJual);
            totalProduk = itemView.findViewById(R.id.totalJual);
            kurangJual = itemView.findViewById(R.id.minusJ);
            plusJual = itemView.findViewById(R.id.plusJ);
            deleteJual = itemView.findViewById(R.id.deleteJ);

            //idProduk = itemView.findViewById(R.id.nomorProduk);

        }
    }

    private void getData(){
        produkHelper.open();
        produk = produkHelper.getAllData();
        produkHelper.close();
    }


}
