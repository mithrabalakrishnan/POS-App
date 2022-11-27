package com.example.pos_android.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pos_android.R;
import com.example.pos_android.data.model.sales_report.FoodDetail;
import com.example.pos_android.databinding.BestSellingLayoutBinding;

import java.util.ArrayList;

public class BestSellingAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final ArrayList<FoodDetail> foodDetails;


    public BestSellingAdapter(@NonNull Context context, Activity context1, ArrayList<FoodDetail> foodDetails) {
        super(context, com.example.pos_android.R.layout.best_selling_layout);
        this.context = context1;
        this.foodDetails = foodDetails;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BestSellingLayoutBinding binding;
        if (convertView == null) {
            binding = BestSellingLayoutBinding.inflate(context.getLayoutInflater(), parent, false);
            convertView = binding.getRoot();
            convertView.setTag(R.id.viewBinding, binding);
        } else
            binding = ((BestSellingLayoutBinding) convertView.getTag(R.id.viewBinding));


        String myTitle = foodDetails.get(position).getFood();
        String mySale = foodDetails.get(position).getSale_amount();

        binding.tvFood.setText(myTitle);
        binding.tvAmount.setText( mySale);


        return convertView;

    }
}
