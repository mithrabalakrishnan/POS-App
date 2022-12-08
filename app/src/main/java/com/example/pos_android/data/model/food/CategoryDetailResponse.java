package com.example.pos_android.data.model.food;

import java.util.ArrayList;

public class CategoryDetailResponse {
    public boolean status;
    public String message;
    public ArrayList<CategoryDetailData> data;

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

    public ArrayList<CategoryDetailData> getData() {
        return data;
    }

    public void setData(ArrayList<CategoryDetailData> data) {
        this.data = data;
    }
}

