package com.example.pos_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pos_android.data.model.HistoryResponse;
import com.example.pos_android.databinding.LayoutHistoryBinding;
import com.example.pos_android.utils.OnItemClickListener;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    public OnItemClickListener clickListener;
    List<HistoryResponse.Order> list;
    Context context;

    public HistoryAdapter(List<HistoryResponse.Order> list, Context context) {
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
        HistoryResponse.Order currentItem = list.get(position);
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

        void setData(HistoryResponse.Order model) {
            historyBinding.tvTime.setText(model.getTimeDate());
            historyBinding.tvTotal.setText(String.valueOf(model.getTotalPrice()));
        }
    }
}
