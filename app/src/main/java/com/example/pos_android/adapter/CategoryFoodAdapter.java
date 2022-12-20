package com.example.pos_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.pos_android.R;
import com.example.pos_android.data.model.food.CategoryDetailData;
import com.example.pos_android.utils.OnItemClickListener;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class CategoryFoodAdapter extends RecyclerView.Adapter<CategoryFoodAdapter.ViewHolder> {
    // ... ViewHolder class and its constructor as per above
    ArrayList<CategoryDetailData> list;
    private Context context;
    private OnItemClickListener clickListener;

    public CategoryFoodAdapter(ArrayList<CategoryDetailData> list, Context context, OnItemClickListener clickListener) {
        this.list = list;
        this.context = context;
        this.clickListener = clickListener;
    }

    // Creating a viewHolder
    @NonNull
    @Override
    public CategoryFoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout
        View contactView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_add_cart_item, parent, false);

        // Return a new holder instance
        CategoryFoodAdapter.ViewHolder viewHolder = new CategoryFoodAdapter.ViewHolder(contactView);

        return viewHolder;
    }

    // Assigning respective data for the views based on the position of the current item
    @Override
    public void onBindViewHolder(@NonNull CategoryFoodAdapter.ViewHolder holder, int position) {
        // Get the Subject based on the current position
        CategoryDetailData currentItem = list.get(position);

        // Setting views with the corresponding data
        ImageView imageView = holder.subjectImageView;
        Glide.with(context)
                .load(currentItem.getImage())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);

        TextView subjectTextView = holder.subjectTextView;
        subjectTextView.setText(currentItem.getFoodName());

        TextView likesTextView = holder.numOfLikesTextView;
        likesTextView.setText("£ "+currentItem.getPrice());

        holder.button.setOnClickListener(v -> {
            clickListener.onItemClick(position, "",false);
        });
    }

    // Indicating how long your data is
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView subjectImageView;
        public TextView subjectTextView;
        public TextView numOfLikesTextView;
        public MaterialButton button;

        // Constructor - accepts entire row item
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Find each view by id you set up in the list_item.xml
            subjectImageView = itemView.findViewById(R.id.subject_image_view);
            subjectTextView = itemView.findViewById(R.id.subject_text_view);
            numOfLikesTextView = itemView.findViewById(R.id.likes_text_view);
            button = itemView.findViewById(R.id.btn_add_cart);
        }
    }
}
