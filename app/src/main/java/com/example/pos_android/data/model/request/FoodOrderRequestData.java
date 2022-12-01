package com.example.pos_android.data.model.request;

import java.util.List;

public class FoodOrderRequestData {
    private List<Integer> foodId;
    private List<Integer> quanty;
    private String tableId;
    private List<Integer> totalPrice;
    private String time;
    private String date;

    public FoodOrderRequestData(List<Integer> foodId, List<Integer> quanty, String tableId,  List<Integer> totalPrice, String time,String date) {
        this.foodId = foodId;
        this.quanty = quanty;
        this.tableId = tableId;
        this.totalPrice = totalPrice;
        this.time = time;
        this.date = date;
    }
}
