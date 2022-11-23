package com.example.pos_android.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HistoryResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Order {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("userId")
        @Expose
        private Integer userId;
        @SerializedName("foodId")
        @Expose
        private Integer foodId;
        @SerializedName("quanty")
        @Expose
        private Integer quanty;
        @SerializedName("tableId")
        @Expose
        private Integer tableId;
        @SerializedName("totalPrice")
        @Expose
        private Integer totalPrice;
        @SerializedName("timeDate")
        @Expose
        private String timeDate;
        public Order(Integer id, Integer userId, Integer foodId, Integer quanty, Integer tableId, Integer totalPrice, String timeDate) {
            this.id = id;
            this.userId = userId;
            this.foodId = foodId;
            this.quanty = quanty;
            this.tableId = tableId;
            this.totalPrice = totalPrice;
            this.timeDate = timeDate;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getFoodId() {
            return foodId;
        }

        public void setFoodId(Integer foodId) {
            this.foodId = foodId;
        }

        public Integer getQuanty() {
            return quanty;
        }

        public void setQuanty(Integer quanty) {
            this.quanty = quanty;
        }

        public Integer getTableId() {
            return tableId;
        }

        public void setTableId(Integer tableId) {
            this.tableId = tableId;
        }

        public Integer getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(Integer totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getTimeDate() {
            return timeDate;
        }

        public void setTimeDate(String timeDate) {
            this.timeDate = timeDate;
        }

    }

    public class Data {

        @SerializedName("orderList")
        @Expose
        private List<Order> orderList = null;
        @SerializedName("tableList")
        @Expose
        private List<Table> tableList = null;

        public List<Order> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<Order> orderList) {
            this.orderList = orderList;
        }

        public List<Table> getTableList() {
            return tableList;
        }

        public void setTableList(List<Table> tableList) {
            this.tableList = tableList;
        }

    }

    public class Table {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("userId")
        @Expose
        private Integer userId;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("time")
        @Expose
        private String time;
        @SerializedName("members")
        @Expose
        private String members;
        @SerializedName("seatLocation")
        @Expose
        private String seatLocation;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getMembers() {
            return members;
        }

        public void setMembers(String members) {
            this.members = members;
        }

        public String getSeatLocation() {
            return seatLocation;
        }

        public void setSeatLocation(String seatLocation) {
            this.seatLocation = seatLocation;
        }

    }
}
