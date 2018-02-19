package com.app.ardasatata.papbl_1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.ardasatata.papbl_1.dbHelper.ProdukHelper;

import java.util.ArrayList;

/**
 * Created by ardasatata on 2/19/18.
 */

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.CustomViewHolder>{

    private LayoutInflater mInflater;
    private ArrayList<Produk> produk;
    private Context context;
    private ProdukHelper produkHelper;

    public ProdukAdapter(Context context){
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        produkHelper = new ProdukHelper(context);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                viewGroup.getContext());
        View v =
                inflater.inflate(R.layout.row_produk, viewGroup, false);
        // set the view's size, margins, paddings and layout parameters
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ProdukAdapter.CustomViewHolder holder, int position) {
        final String nama = produk.get(position).getNama();
        final int harga = produk.get(position).getHarga();

        holder.namaProduk.setText(nama);
        holder.hargaProduk.setText(Integer.toString(harga));

    }

    @Override
    public int getItemCount() {
        return produk.size();
    }

    public void addItem(ArrayList<Produk> mData) {
        this.produk = mData;
        notifyDataSetChanged();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView namaProduk;
        private TextView hargaProduk;

        public CustomViewHolder(View itemView) {
            super(itemView);

            namaProduk = itemView.findViewById(R.id.namaProduk);
            hargaProduk = itemView.findViewById(R.id.hargaProduk);

        }
    }
}
