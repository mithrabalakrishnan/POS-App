package com.example.pos_android.data.model.request;

public class AddFoodRequestData {
    final String name;
    final String category;
    final String rating;
    final String price;
    final String image;
    final String status;
    final String ingredients;

    public AddFoodRequestData(String name, String categoty, String price, String imageUrl, String ingredients) {
        this.name = name;
        this.category = categoty;
        this.price = price;
        this.rating = "0.0";
        this.status = "popular";
        this.image = imageUrl;
        this.ingredients = ingredients;
    }

    public AddFoodRequestData(String name, String category, String rating, String price, String image, String status, String ingredients) {
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.price = price;
        this.image = image;
        this.status = status;
        this.ingredients = ingredients;
    }
}