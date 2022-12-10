package com.example.pos_android.data.model.food;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryDetailData {
    @SerializedName("foodid")
    @Expose
    public int foodId;
    @SerializedName("food_name")
    @Expose
    public String foodName;
    @SerializedName("category")
    @Expose
    public String category;
    @SerializedName("rating")
    @Expose
    public String rating;
    @SerializedName("price")
    @Expose
    public String price;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("status")
    @Expose
    public String status;

    public CategoryDetailData(int foodId, String foodName, String category, String rating, String price, String image, String status) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.category = category;
        this.rating = rating;
        this.price = price;
        this.image = image;
        this.status = status;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
