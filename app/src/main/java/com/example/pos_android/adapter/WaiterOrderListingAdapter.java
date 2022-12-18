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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class WaiterOrderListingAdapter extends RecyclerView.Adapter<WaiterOrderListingAdapter.ViewHolder> {
    private List<KitchenResponse.KitchenData> kitchenDataList;
    private OnItemClickListener onItemClickListener;

    public WaiterOrderListingAdapter(List<KitchenResponse.KitchenData> kitchenDataList, OnItemClickListener clickListener) {
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
        WaiterOrderListingAdapter.ViewHolder viewHolder = new WaiterOrderListingAdapter.ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KitchenResponse.KitchenData data = kitchenDataList.get(position);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String currentDateToString = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            Date currentDate = sdf.parse(currentDateToString);
            Date offerDate = sdf.parse(data.getDate());
            if (Objects.requireNonNull(currentDate).compareTo(offerDate)<0 || data.getStatus() =="To Do" || data.getStatus() =="In-Progress") {
                holder.mainLayout.setVisibility(View.GONE);
            }else {
                holder.mainLayout.setVisibility(View.VISIBLE);
            }
        } catch (ParseException e1) {
            e1.printStackTrace();
        }


        holder.orderId.setText("Order Id : #" + String.valueOf(data.getId()));
        holder.foodDetails.setText(String.format("%s  X %d", data.getFoodName(), data.getQuanty()));
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
        private LinearLayout button,mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.tv_order_id);
            foodDetails = itemView.findViewById(R.id.tv_food_details);
            date = itemView.findViewById(R.id.tv_date);
            button = itemView.findViewById(R.id.btn_view_order);
            mainLayout = itemView.findViewById(R.id.item_layout);
        }
    }
}