package com.example.pos_android.data.model.food;

public class CategoryDetailResponse {
    public boolean status;
    public String message;
    public CategoryDetailData data;

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

    public CategoryDetailData getData() {
        return data;
    }

    public void setData(CategoryDetailData data) {
        this.data = data;
    }
}

