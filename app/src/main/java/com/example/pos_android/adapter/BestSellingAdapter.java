package com.example.pos_android.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pos_android.R;
import com.example.pos_android.databinding.BestSellingLayoutBinding;

public class BestSellingAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] title;
    private final Double[] sale;


    public BestSellingAdapter(@NonNull Context context, Activity context1, String[] title, Double[] sale) {
        super(context, com.example.pos_android.R.layout.best_selling_layout, title);
        this.context = context1;
        this.title = title;
        this.sale = sale;
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


        String myTitle = title[position];
        String mySale = String.valueOf(sale[position]);

        binding.tvFood.setText(myTitle);
        binding.tvAmount.setText("£ " + mySale);


        return convertView;

    }
}
