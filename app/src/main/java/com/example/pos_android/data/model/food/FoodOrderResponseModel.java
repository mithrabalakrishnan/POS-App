package com.example.pos_android.data.model.food;

import java.util.ArrayList;

public class FoodOrderResponseModel {
    public boolean status;
    public String message;
    public FoodOrderResponseModelData data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FoodOrderResponseModelData getData() {
        return data;
    }

    public void setData(FoodOrderResponseModelData data) {
        this.data = data;
    }
}

