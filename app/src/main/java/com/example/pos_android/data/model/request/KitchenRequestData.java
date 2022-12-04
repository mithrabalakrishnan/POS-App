package com.example.pos_android.data.model.request;

public class KitchenRequestData {
    private int orderId;
    private int userId;
    private String status;

    public KitchenRequestData(int orderId, int userId, String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.status = status;
    }
}
