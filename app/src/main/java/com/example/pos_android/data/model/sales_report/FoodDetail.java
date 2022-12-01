package com.example.pos_android.data.model.sales_report;

public class FoodDetail {
    public String food;
    public Double totalPrice;

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public Double getSale_amount() {
        return totalPrice;
    }

    public void setSale_amount(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
