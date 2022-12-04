package com.example.pos_android.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FoodModel implements Serializable {
    private String foodId;
    private String name;
    private int imageId;
    private String imageUrl;
    private String price;
    private int quantity;

    public FoodModel(String foodId, String name, String imageUrl, String price) {
        this.foodId = foodId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public FoodModel(String foodId, String name, String imageUrl, String price, int quantity) {
        this.foodId = foodId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public FoodModel() {
    }

    // Constructor
    public FoodModel(String name, int imageId, String price) {
        this.name = name;
        this.imageId = imageId;
        this.price = price;
    }

    public FoodModel(String name, String imageUrl, String price) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public FoodModel(String name, String imageUrl, String price, int quantity) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    //Setting up the getter methods
    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getPrice() {
        return price;
    }
}