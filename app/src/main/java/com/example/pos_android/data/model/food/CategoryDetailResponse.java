package com.example.pos_android.data.model.food;

import com.example.pos_android.data.model.UserHomeResponse;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetailResponse {
    public boolean status;
    public String message;
    public List<UserHomeResponse.PopularFood> data;

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

    public List<UserHomeResponse.PopularFood> getData() {
        return data;
    }

    public void setData(List<UserHomeResponse.PopularFood> data) {
        this.data = data;
    }
}

