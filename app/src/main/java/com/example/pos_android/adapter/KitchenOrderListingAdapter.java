package com.example.pos_android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pos_android.R;
import com.example.pos_android.data.model.KitchenResponse;
import com.example.pos_android.utils.OnItemClickListener;

import java.util.List;

public class KitchenOrderListingAdapter extends RecyclerView.Adapter<KitchenOrderListingAdapter.ViewHolder> {
    private List<KitchenResponse.KitchenData> kitchenDataList;
    private OnItemClickListener onItemClickListener;

    public KitchenOrderListingAdapter(List<KitchenResponse.KitchenData> kitchenDataList, OnItemClickListener clickListener) {
        this.kitchenDataList = kitchenDataList;
        this.onItemClickListener = clickListener;
    }

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
        KitchenResponse.KitchenData data = kitchenDataList.get(position);
        holder.orderId.setText("Order Id : #" + String.valueOf(data.getId()));
        holder.foodDetails.setText("User Id : " + String.valueOf(data.getUserId()));
        holder.date.setText(String.valueOf(data.getDate()));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position, "");
            }
        });
    }

    @Override
    public int getItemCount() {
        return kitchenDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Constructor - accepts entire row item
        private TextView orderId, foodDetails, date;
        private LinearLayout button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.tv_order_id);
            foodDetails = itemView.findViewById(R.id.tv_food_details);
            date = itemView.findViewById(R.id.tv_date);
            button = itemView.findViewById(R.id.btn_view_order);
        }
    }
}
