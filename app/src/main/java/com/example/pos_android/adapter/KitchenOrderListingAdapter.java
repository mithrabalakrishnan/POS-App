package com.example.pos_android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pos_android.R;

public class KitchenOrderListingAdapter extends RecyclerView.Adapter<KitchenOrderListingAdapter.ViewHolder>  {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout
        View contactView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kitchen_order_item, parent, false);

        // Return a new holder instance
        KitchenOrderListingAdapter.ViewHolder viewHolder = new KitchenOrderListingAdapter.ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Constructor - accepts entire row item
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
