package com.app.ardasatata.papbl_1;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import android.support.v4.app.DialogFragment;



import com.app.ardasatata.papbl_1.dbHelper.ProdukHelper;

import java.util.ArrayList;

/**
 * Created by ardasatata on 2/19/18.
 */

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.CustomViewHolder>{

    private LayoutInflater mInflater;
    private ArrayList<Produk> produk;
    private Context context;
    private Context mContext;
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
    public void onBindViewHolder(ProdukAdapter.CustomViewHolder holder, final int position) {
        final String nama = produk.get(position).getNama();
        final int harga = produk.get(position).getHarga();
        final int id = produk.get(position).getId();

        holder.namaProduk.setText(nama);
        holder.hargaProduk.setText(Integer.toString(harga));
        holder.idProduk.setText(Integer.toString(id));

        holder.deleteProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                produkHelper.open();
                produkHelper.delete(id);
                produkHelper.close();


            }
        });

        holder.editProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("namaP", nama);
                bundle.putInt("hargaP", harga);
                bundle.putInt("idP", id);
                //set Fragmentclass Arguments

                editProdukFragment a = new editProdukFragment();
                a.setArguments(bundle);

                final Activity activity = (Activity) context;

                a.show(activity.getFragmentManager(),"a");
            }
        });


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
        private Button editProduk;
        private Button deleteProduk;
        private TextView idProduk;

        public CustomViewHolder(View itemView) {
            super(itemView);

            namaProduk = itemView.findViewById(R.id.namaProduk);
            hargaProduk = itemView.findViewById(R.id.hargaProduk);
            idProduk = itemView.findViewById(R.id.nomorProduk);

            editProduk=itemView.findViewById(R.id.editP);
            deleteProduk=itemView.findViewById(R.id.deleteP);

        }
    }
}
