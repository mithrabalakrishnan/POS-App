package com.example.pos_android.data.model;

import java.io.Serializable;

public class FoodModel implements Serializable {
    private String foodId;
    private String name;
    private int imageId;
    private String imageUrl;
    private String price;
    private int quantity;
    private String incrediance;

    public FoodModel(String foodId, String name, String imageUrl, String price, String incrediance) {
        this.foodId = foodId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.incrediance = incrediance;
    }

    public FoodModel(String foodId, String name, String imageUrl, String price, int quantity, String incrediance) {
        this.foodId = foodId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
        this.incrediance = incrediance;
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
    public FoodModel(String name, int imageId, String price, String incrediance1) {
        this.name = name;
        this.imageId = imageId;
        this.price = price;
        this.incrediance = incrediance1;
    }

    public FoodModel(String name, String imageUrl, String price, String incrediance) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.incrediance = incrediance;
    }

    public FoodModel(String name, String imageUrl, String price, int quantity, String incrediance) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
        this.incrediance = incrediance;
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

    public String getIncrediance() {
        return incrediance;
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