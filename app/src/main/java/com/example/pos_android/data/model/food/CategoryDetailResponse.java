package com.example.pos_android.data.model.food;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoryDetailResponse {
    @SerializedName("status")
    @Expose
    public boolean status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
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

