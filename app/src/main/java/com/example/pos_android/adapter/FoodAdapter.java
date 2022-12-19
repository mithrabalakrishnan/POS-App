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
import com.example.pos_android.data.model.FoodModel;
import com.example.pos_android.utils.OnItemClickListener;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    // ... ViewHolder class and its constructor as per above
    ArrayList<FoodModel> list;
    private boolean isFromNetwork;
    private Context context;
    private String from = null;
    private OnItemClickListener clickListener;

    // Constructor
    public FoodAdapter(ArrayList<FoodModel> list, Context cOntext, OnItemClickListener clickListener,String from ) {
        this.list = list;
        this.isFromNetwork = true;
        this.context = cOntext;
        this.clickListener = clickListener;
        this.from = from;
    }

    public FoodAdapter(ArrayList<FoodModel> list, boolean isFromNetwork, Context cOntext, OnItemClickListener clickListener,String from ) {
        this.list = list;
        this.isFromNetwork = isFromNetwork;
        this.context = cOntext;
        this.clickListener = clickListener;
        this.from = from;
    }

    // Creating a viewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout
        View contactView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_popular_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    // Assigning respective data for the views based on the position of the current item
    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {
        // Get the Subject based on the current position
        FoodModel currentItem = list.get(position);

        // Setting views with the corresponding data
        ImageView imageView = holder.subjectImageView;
        if (isFromNetwork) {
            Glide.with(context)
                    .load(currentItem.getImageUrl())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
        } else
            imageView.setImageResource(currentItem.getImageId());

        TextView subjectTextView = holder.subjectTextView;
        subjectTextView.setText(currentItem.getName());

        TextView likesTextView = holder.numOfLikesTextView;
        likesTextView.setText("£ "+currentItem.getPrice());
        holder.itemView.setOnClickListener(view -> {
            clickListener.onItemClick(position, "",true);
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

        // Constructor - accepts entire row item
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Find each view by id you set up in the list_item.xml
            subjectImageView = itemView.findViewById(R.id.subject_image_view);
            subjectTextView = itemView.findViewById(R.id.subject_text_view);
            numOfLikesTextView = itemView.findViewById(R.id.likes_text_view);
        }
    }
}
