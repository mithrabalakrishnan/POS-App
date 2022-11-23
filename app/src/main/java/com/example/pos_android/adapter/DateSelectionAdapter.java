package com.example.pos_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pos_android.R;
import com.example.pos_android.data.model.DateModel;
import com.example.pos_android.databinding.LayoutSelectDateBinding;
import com.example.pos_android.utils.OnItemClickListener;

import java.util.List;

public class DateSelectionAdapter extends RecyclerView.Adapter<DateSelectionAdapter.DateViewHolder> {
    List<DateModel> dateModelList;
    Context context;
    OnItemClickListener onItemClickListener;

    public DateSelectionAdapter(List<DateModel> dateModelList, Context context, OnItemClickListener onItemClickListener) {
        this.dateModelList = dateModelList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DateViewHolder(LayoutSelectDateBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DateViewHolder holder, int position) {
        holder.setData(dateModelList.get(position));
        holder.itemView.setOnClickListener(v -> {
            onItemClickListener.onItemClick(position,"Date");
        });
    }

    @Override
    public int getItemCount() {
        return dateModelList != null ? dateModelList.size() : 0;
    }

    public class DateViewHolder extends RecyclerView.ViewHolder {
        private LayoutSelectDateBinding layoutSelectDateBinding;

        public DateViewHolder(LayoutSelectDateBinding itemView) {
            super(itemView.getRoot());
            this.layoutSelectDateBinding = itemView;
        }

        void setData(DateModel model) {
            if (model.isSelected())
                layoutSelectDateBinding.tvCard.setCardBackgroundColor(context.getResources().getColor(R.color.orange_500));
            else
                layoutSelectDateBinding.tvCard.setCardBackgroundColor(context.getResources().getColor(R.color.white));

            String[] str = model.getDate().split("-", 0);
            layoutSelectDateBinding.tvDate.setText(str[0]);
            layoutSelectDateBinding.tvMonth.setText(str[1]);
        }
    }
}


