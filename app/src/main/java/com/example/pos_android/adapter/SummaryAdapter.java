package com.example.pos_android.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.pos_android.R;
import com.example.pos_android.data.model.FoodModel;
import com.example.pos_android.databinding.ItemCartProductBinding;

import java.util.List;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.CartViewHolder> {
    private Context context;
    private onCartItemClickListener listener;
    private List<FoodModel> list;
    private float itemPrice;
    private int totalPrice;
    private int quantity;


    public SummaryAdapter(Context context, List<FoodModel> list, onCartItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.list = list;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCartProductBinding binding = ItemCartProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CartViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FoodModel model = list.get(position);
        itemPrice = Float.parseFloat(model.getPrice());
        if (model.getQuantity() >= 1) {
            holder.binding.foodItem.subjectTextView.setText(model.getName() + " x " + model.getQuantity());
        } else {
            holder.binding.foodItem.subjectTextView.setText(model.getName());
        }
        for (int i = 1; i < model.getQuantity(); i++) {
            itemPrice = itemPrice + Float.parseFloat(model.getPrice());
        }
        Glide.with(context)
                .load(model.getImageUrl())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.binding.foodItem.subjectImageView);
        holder.binding.textFoodPrice.setText("£" + itemPrice);
        if (model.getQuantity() == 0) {
            //holder.binding.layoutQuantityControl.imageSub.setVisibility(View.GONE);
            holder.binding.layoutQuantityControl.textQuantity.setText("Add");
            holder.binding.textFoodPrice.setText("");
            holder.binding.textFoodPrice.setText("");
            holder.binding.layoutQuantityControl.imageSub.setImageResource(R.drawable.ic_minus);
            holder.binding.layoutQuantityControl.imageSub.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            holder.binding.layoutQuantityControl.textQuantity.setText(Integer.toString(model.getQuantity()));
        } else if (model.getQuantity() <= 0) {
            holder.binding.layoutQuantityControl.imageSub.setVisibility(View.GONE);
            holder.binding.textFoodPrice.setText("");
        } else {
            holder.binding.layoutQuantityControl.imageSub.setImageResource(R.drawable.ic_minus);
            holder.binding.layoutQuantityControl.imageSub.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            holder.binding.layoutQuantityControl.imageSub.setVisibility(View.VISIBLE);
            holder.binding.layoutQuantityControl.textQuantity.setText(Integer.toString(model.getQuantity()));
        }
        holder.binding.layoutQuantityControl.imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onQuantityAdd(position);
            }
        });
        holder.binding.layoutQuantityControl.imageSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (model.getQuantity() >= 0) {
//                    if (model.getQuantity() == -1) {
//                        listener.onDeleteItem(position);
//                    } else {
                    listener.onQuantitySub(position);
                    //  }
                }
            }
        });
        holder.binding.layoutQuantityControl.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    public interface onCartItemClickListener {
        void onItemClicked(int position);

        void onQuantityAdd(int position);

        void onQuantitySub(int position);

        void onDeleteItem(int position);
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        private ItemCartProductBinding binding;

        public CartViewHolder(@NonNull ItemCartProductBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
