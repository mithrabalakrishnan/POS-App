package com.example.pos_android.data.model.request;

public class KitchenRequestData {
    private int foodId;
    private int userId;
    private String status;

    public KitchenRequestData(int foodId, int userId, String status) {
        this.foodId = foodId;
        this.userId = userId;
        this.status = status;
    }
}
