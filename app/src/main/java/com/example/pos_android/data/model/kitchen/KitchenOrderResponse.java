package com.example.pos_android.data.model.kitchen;

import java.util.ArrayList;

public class KitchenOrderResponse {
    public int statuscode;
    public boolean success;
    public String message;
    public KitchenOrderData data;

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public KitchenOrderData getData() {
        return data;
    }

    public void setData(KitchenOrderData data) {
        this.data = data;
    }
}

