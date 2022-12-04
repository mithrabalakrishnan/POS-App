package com.example.pos_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pos_android.R;
import com.example.pos_android.data.model.CouponsData;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.utils.OnItemClickListener;

public class CouponsAdapter extends RecyclerView.Adapter<CouponsAdapter.ViewHolder>{
    private final CouponsData[] couponsData;
    private Context context;
    private final SessionManager sessionManager;
    OnItemClickListener onItemClickListener;

    public CouponsAdapter(CouponsData[] couponsData, Context context, OnItemClickListener onItemClickListener) {
        this.couponsData = couponsData;
        this.context = context;
        sessionManager = new SessionManager(context);
        this.onItemClickListener = onItemClickListener;
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

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.setIsCouponSelected(true);
                sessionManager.setCouponPercent(myListData.getPercentage());
                onItemClickListener.onItemClick(position,"coupon");
            }
        });
    }

    @Override
    public int getItemCount() {
        return couponsData.length;
    }

      public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgLogo;
        public TextView txtName,txtExpDate,txtPrice;
        public FrameLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imgLogo = (ImageView) itemView.findViewById(R.id.imageLogo);
            this.txtName = (TextView) itemView.findViewById(R.id.txtName);
            this.txtExpDate = (TextView) itemView.findViewById(R.id.txtMonth);
            this.txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            this.layout = (FrameLayout) itemView.findViewById(R.id.frameCoupon);

        }
    }
}
