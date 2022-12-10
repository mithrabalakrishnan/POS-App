package com.example.pos_android.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pos_android.R;
import com.example.pos_android.data.model.HistoryResponse;
import com.example.pos_android.databinding.LayoutHistoryBinding;
import com.example.pos_android.utils.OnItemClickListener;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    public OnItemClickListener clickListener;
    List<HistoryResponse.FoodOrderList> list;
    Context context;

    public HistoryAdapter(List<HistoryResponse.FoodOrderList> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout
        return new HistoryAdapter.ViewHolder(LayoutHistoryBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        HistoryResponse.FoodOrderList currentItem = list.get(position);
        holder.setData(currentItem);
        /*holder.itemView.setOnClickListener(nv -> {
            clickListener.onItemClick(position, "Table");
        });*/
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LayoutHistoryBinding historyBinding;

        public ViewHolder(@NonNull LayoutHistoryBinding itemView) {
            super(itemView.getRoot());
            this.historyBinding = itemView;
        }

        void setData(HistoryResponse.FoodOrderList model) {
            historyBinding.tvTime.setText(model.getTime());
            historyBinding.tvTotal.setText(String.valueOf(model.getTotalPrice()));
            historyBinding.tvDate.setText(String.valueOf(model.getDate()));

            ///create linear layout programmatically
            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setPadding(0, 8, 0, 0);

            ///Adds the itemName
            TextView itemName = new TextView(context);
            itemName.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
            itemName.setText(model.getFoodName());
            itemName.setTextSize(15);
            itemName.setPadding(15, 0, 0, 0);
            itemName.setTextColor(context.getResources().getColor(R.color.black));

            ///Adds the itemCount
            TextView itemCount = new TextView(context);
            itemCount.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
            itemCount.setText(String.valueOf(model.getQuanty()));
            itemCount.setTextSize(15);
            itemCount.setGravity(Gravity.END);
            itemCount.setPadding(0, 0, 15, 0);
            itemCount.setTextColor(context.getResources().getColor(R.color.red_500));

            layout.addView(itemName);
            layout.addView(itemCount);

            historyBinding.layoutFoodDetails.addView(layout);

            historyBinding.tvTableId.setText(String.valueOf(model.getTableId()));
            historyBinding.tvStatus.setText(String.valueOf(model.getStatus()));
        }
    }
}
