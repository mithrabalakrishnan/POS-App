package com.example.pos_android.data.model;

import java.io.Serializable;
import java.util.List;

public class OrderInfoModel implements Serializable {
    private List<Integer> foodId;
    private List<Integer> quantity;
    private List<Integer> totalPrice;
    private String timeAndDate;
    private TableInfoModel tableInfoModel;

    public OrderInfoModel(List<Integer> foodId, List<Integer> quantity, List<Integer> totalPrice, String timeAndDate, TableInfoModel tableInfoModel) {
        this.foodId = foodId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.timeAndDate = timeAndDate;
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

    public String getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(String timeAndDate) {
        this.timeAndDate = timeAndDate;
    }

    public TableInfoModel getTableInfoModel() {
        return tableInfoModel;
    }

    public void setTableInfoModel(TableInfoModel tableInfoModel) {
        this.tableInfoModel = tableInfoModel;
    }
}