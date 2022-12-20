package com.example.pos_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pos_android.R;
import com.example.pos_android.data.model.TimeModel;
import com.example.pos_android.databinding.LayoutSelectTimeBinding;
import com.example.pos_android.utils.OnItemClickListener;

import java.util.List;

public class TimeSelectionAdapter extends RecyclerView.Adapter<TimeSelectionAdapter.TimeViewHolder> {
    List<TimeModel> stringList;
    Context context;
    OnItemClickListener onItemClickListener;

    public TimeSelectionAdapter(List<TimeModel> stringList, Context context, OnItemClickListener onItemClickListener) {
        this.stringList = stringList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public TimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TimeViewHolder(LayoutSelectTimeBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TimeViewHolder holder, int position) {
        holder.setData(stringList.get(position));
        holder.itemView.setOnClickListener(v -> {
            onItemClickListener.onItemClick(position,"Time",false);
        });
    }

    @Override
    public int getItemCount() {
        return stringList != null ? stringList.size() : 0;
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder {
        private LayoutSelectTimeBinding layoutSelectDateBinding;

        public TimeViewHolder(LayoutSelectTimeBinding itemView) {
            super(itemView.getRoot());
            this.layoutSelectDateBinding = itemView;
        }

        void setData(TimeModel timeModel) {
            if (timeModel.isSelected())
                layoutSelectDateBinding.card.setCardBackgroundColor(context.getResources().getColor(R.color.orange_500));
            else
                layoutSelectDateBinding.card.setCardBackgroundColor(context.getResources().getColor(R.color.white));
            layoutSelectDateBinding.tvTime.setText(timeModel.getTime());
        }
    }
}


