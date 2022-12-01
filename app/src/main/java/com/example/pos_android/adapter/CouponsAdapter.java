package com.example.pos_android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pos_android.R;
import com.example.pos_android.data.model.CouponsData;

public class CouponsAdapter extends RecyclerView.Adapter<CouponsAdapter.ViewHolder>{
    private CouponsData[] couponsData;

     public CouponsAdapter(CouponsData[] couponsData) {
        this.couponsData = couponsData;
    }

    @NonNull
    @Override
    public CouponsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.coupen_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CouponsAdapter.ViewHolder holder, int position) {
        final CouponsData myListData = couponsData[position];
        holder.txtName.setText(myListData.getTxtCouponName());
        holder.txtPrice.setText(myListData.getAmount());
        holder.txtExpDate.setText(myListData.getTxtExpDate());
        holder.imgLogo.setImageResource(myListData.getImage());
    }

    @Override
    public int getItemCount() {
        return couponsData.length;
    }

      public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgLogo;
        public TextView txtName,txtExpDate,txtPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imgLogo = (ImageView) itemView.findViewById(R.id.imageLogo);
            this.txtName = (TextView) itemView.findViewById(R.id.txtName);
            this.txtExpDate = (TextView) itemView.findViewById(R.id.txtMonth);
            this.txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);

        }
    }
}
