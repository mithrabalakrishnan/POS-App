package com.example.pos_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pos_android.R;
import com.example.pos_android.data.model.TableModel;
import com.example.pos_android.databinding.TableLayoutBinding;
import com.example.pos_android.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {

    List<TableModel> list;
    public OnItemClickListener clickListener;
    Context context;

    public TableAdapter(List<TableModel> list,OnItemClickListener clickListener, Context context) {
        this.list = list;
        this.clickListener = clickListener;
        this.context = context;

    }

    @NonNull
    @Override
    public TableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout
        return new TableAdapter.ViewHolder(TableLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull TableAdapter.ViewHolder holder, int position) {
        TableModel currentItem = list.get(position);
        holder.setData(currentItem);
        holder.itemView.setOnClickListener(nv -> {
            clickListener.onItemClick(position,"Table",false);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TableLayoutBinding tableLayoutBinding;

        public ViewHolder(@NonNull TableLayoutBinding itemView) {
            super(itemView.getRoot());
            this.tableLayoutBinding = itemView;
        }

        void setData(TableModel model) {
            if (model.isSelected())
                tableLayoutBinding.card.setCardBackgroundColor(context.getResources().getColor(R.color.orange_500));
            else
                tableLayoutBinding.card.setCardBackgroundColor(context.getResources().getColor(R.color.white));

            tableLayoutBinding.tvTablePreference.setText(model.getTable());
        }
    }
}
