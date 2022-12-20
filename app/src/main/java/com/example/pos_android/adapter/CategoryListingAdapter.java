package com.example.pos_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pos_android.R;
import com.example.pos_android.utils.onCategoryItemClick;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class CategoryListingAdapter extends RecyclerView.Adapter<CategoryListingAdapter.ViewHolder> {
    // ... ViewHolder class and its constructor as per above
    ArrayList<String> list;
    int selectedPosition;
    private Context context;
    private onCategoryItemClick clickListener;

    public CategoryListingAdapter(ArrayList<String> list, Context context, onCategoryItemClick clickListener, int selectedPosition) {
        this.list = list;
        this.context = context;
        this.clickListener = clickListener;
        this.selectedPosition = selectedPosition;
    }

    // Creating a viewHolder
    @NonNull
    @Override
    public CategoryListingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout
        View contactView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_category_item, parent, false);

        // Return a new holder instance
        CategoryListingAdapter.ViewHolder viewHolder = new CategoryListingAdapter.ViewHolder(contactView);

        return viewHolder;
    }

    // Assigning respective data for the views based on the position of the current item
    @Override
    public void onBindViewHolder(@NonNull CategoryListingAdapter.ViewHolder holder, int position) {
        // Get the Subject based on the current position
//        foodCategoryData currentItem = list.get(position);
        if (position == selectedPosition) {
            holder.button.setBackgroundColor(ContextCompat.getColor(context, R.color.orange_500));
            holder.button.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
        else {
            holder.button.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
            holder.button.setTextColor(ContextCompat.getColor(context, R.color.black));
        }
        holder.button.setText(list.get(position));
        holder.button.setOnClickListener(v -> {
            clickListener.onCategoryClick(position, list.get(position));
        });
    }

    // Indicating how long your data is
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updatePosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public MaterialButton button;

        // Constructor - accepts entire row item
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            button = itemView.findViewById(R.id.btn_category);
        }
    }

}

