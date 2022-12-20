package com.example.pos_android.view.user;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.pos_android.data.model.FoodModel;
import com.example.pos_android.databinding.BottomSheetLayoutFoodBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class BottomSheetDialog extends BottomSheetDialogFragment {
    private BottomSheetLayoutFoodBinding binding ;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
         binding = BottomSheetLayoutFoodBinding.inflate(inflater, container, false);
        Bundle mArgs = getArguments();
        assert mArgs != null;
        FoodModel data = (FoodModel) getArguments().getSerializable("data");
        Log.e("daat",data.getName());
        Log.e("daat",data.getIncrediance());
         Glide.with(requireContext())
                .load(data.getImageUrl())
                .transition(DrawableTransitionOptions.withCrossFade())
                .fitCenter()
                .into(binding.subjectImageView);
         binding.subjectTextView.setText(data.getName());
         binding.likesTextView.setText("€ "+data.getPrice());
         binding.txtIngredients.setText("Ingredients: "+data.getIncrediance());

    return binding.getRoot();
    }



}