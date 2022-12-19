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

public class AllFoodAdapter extends RecyclerView.Adapter<AllFoodAdapter.ViewHolder> {
    // ... ViewHolder class and its constructor as per above
    ArrayList<FoodModel> list;
    private Context context;
    private final OnItemClickListener clickListener;

    // Constructor
    public AllFoodAdapter(ArrayList<FoodModel> list, Context cOntext,  OnItemClickListener clickListener) {
        this.list = list;
        this.context = cOntext;
        this.clickListener = clickListener;
    }

    // Creating a viewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout
        View contactView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_all_food_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    // Assigning respective data for the views based on the position of the current item
    @Override
    public void onBindViewHolder(@NonNull AllFoodAdapter.ViewHolder holder, int position) {
        // Get the Subject based on the current position
        FoodModel currentItem = list.get(position);

        // Setting views with the corresponding data
        ImageView imageView = holder.subjectImageView;

        Glide.with(context)
                .load(currentItem.getImageUrl())
                .transition(DrawableTransitionOptions.withCrossFade())
                .fitCenter()
                .into(imageView);


        TextView subjectTextView = holder.subjectTextView;
        subjectTextView.setText(currentItem.getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        clickListener.onItemClick(position,"",true);
            }
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

        // Constructor - accepts entire row item
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Find each view by id you set up in the list_item.xml
            subjectImageView = itemView.findViewById(R.id.subject_image_view);
            subjectTextView = itemView.findViewById(R.id.subject_text_view);
        }
    }
}
