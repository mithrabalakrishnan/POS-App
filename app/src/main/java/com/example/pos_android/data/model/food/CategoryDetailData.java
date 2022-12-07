package com.example.pos_android.data.model.food;

public class CategoryDetailData {
    public int foodid;
    public String food_name;
    public String category;
    public String rating;
    public String price;
    public String image;
    public String status;

    public CategoryDetailData(int foodid, String food_name, String category, String rating, String price, String image, String status) {
        this.foodid = foodid;
        this.food_name = food_name;
        this.category = category;
        this.rating = rating;
        this.price = price;
        this.image = image;
        this.status = status;
    }

    public int getFoodid() {
        return foodid;
    }

    public void setFoodid(int foodid) {
        this.foodid = foodid;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
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
