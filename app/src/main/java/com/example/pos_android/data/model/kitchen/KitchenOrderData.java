package com.example.pos_android.data.model.kitchen;

import java.util.ArrayList;

public class KitchenOrderData {
    public ArrayList<String> foods;
    public String date;
    public String time;
    public OrdereDetails ordere_details;

    public ArrayList<String> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<String> foods) {
        this.foods = foods;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public OrdereDetails getOrdere_details() {
        return ordere_details;
    }

    public void setOrdere_details(OrdereDetails ordere_details) {
        this.ordere_details = ordere_details;
    }
}
