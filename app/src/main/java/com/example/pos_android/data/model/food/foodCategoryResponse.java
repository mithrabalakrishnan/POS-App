package com.example.pos_android.data.model.food;

import java.util.ArrayList;

public class foodCategoryResponse {
    public boolean status;
    public String message;
    public foodCategoryData data;

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

    public foodCategoryData getData() {
        return data;
    }

    public void setData(foodCategoryData data) {
        this.data = data;
    }
}
