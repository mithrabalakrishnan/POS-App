package com.example.pos_android.data.model;

import java.io.Serializable;
import java.util.List;

public class OrderInfoModel implements Serializable {
    private List<Integer> foodId;
    private List<Integer> quantity;
    private List<Integer> totalPrice;
    private String time;
    private String date;
    private TableInfoModel tableInfoModel;

    public OrderInfoModel(List<Integer> foodId, List<Integer> quantity, List<Integer> totalPrice, String time,String date, TableInfoModel tableInfoModel) {
        this.foodId = foodId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.time = time;
        this.date = date;
        this.tableInfoModel = tableInfoModel;
    }

    public List<Integer> getFoodId() {
        return foodId;
    }

    public void setFoodId(List<Integer> foodId) {
        this.foodId = foodId;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }

    public List<Integer> getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(List<Integer> totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TableInfoModel getTableInfoModel() {
        return tableInfoModel;
    }

    public void setTableInfoModel(TableInfoModel tableInfoModel) {
        this.tableInfoModel = tableInfoModel;
    }
}