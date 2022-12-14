package com.example.pos_android.data.model.food;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FoodOrderResponseModelData {
    @SerializedName("foodOrder")
    public FoodOrderedItemModel foodOrderedItemModel;
    public ArrayList<String> phone;

    public FoodOrderedItemModel getFoodOrderedItemModel() {
        return foodOrderedItemModel;
    }

    public void setFoodOrderedItemModel(FoodOrderedItemModel foodOrderedItemModel) {
        this.foodOrderedItemModel = foodOrderedItemModel;
    }

    public ArrayList<String> getPhone() {
        return phone;
    }

    public void setPhone(ArrayList<String> phone) {
        this.phone = phone;
    }
}
