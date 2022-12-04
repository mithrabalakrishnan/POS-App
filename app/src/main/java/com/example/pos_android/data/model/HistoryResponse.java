package com.example.pos_android.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
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

//        @SerializedName("orderList")
//        @Expose
//        private List<Order> orderList = null;

        @SerializedName("foodOrderList")
        @Expose
        public ArrayList<FoodOrderList> foodOrderList = null;

        @SerializedName("tableList")
        @Expose
        private List<Table> tableList = null;

        public ArrayList<FoodOrderList> getFoodOrderList() {
            return foodOrderList;
        }

        public void setFoodOrderList(ArrayList<FoodOrderList> foodOrderList) {
            this.foodOrderList = foodOrderList;
        }

//        public List<Order> getOrderList() {
//            return orderList;
//        }
//
//        public void setOrderList(List<Order> orderList) {
//            this.orderList = orderList;
//        }

        public List<Table> getTableList() {
            return tableList;
        }

        public void setTableList(List<Table> tableList) {
            this.tableList = tableList;
        }

    }

    public class Table {

        @SerializedName("tableid")
        @Expose
        private Integer id;
        @SerializedName("userid")
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

    public class FoodOrderList{
        public int id;
        public int userId;
        public int foodId;
        public int quanty;
        public int tableId;
        public Object totalPrice;
        public String date;
        public String time;
        public Object day;
        public Object month;
        public Object year;
        public String foodName;
        public String username;
        public String status;
        public Object userPhoneNumber;

        public FoodOrderList(int id, int userId, int foodId, int quanty, int tableId, Object totalPrice, String date, String time, Object day, Object month, Object year, String foodName, String username, String status, Object userPhoneNumber) {
            this.id = id;
            this.userId = userId;
            this.foodId = foodId;
            this.quanty = quanty;
            this.tableId = tableId;
            this.totalPrice = totalPrice;
            this.date = date;
            this.time = time;
            this.day = day;
            this.month = month;
            this.year = year;
            this.foodName = foodName;
            this.username = username;
            this.status = status;
            this.userPhoneNumber = userPhoneNumber;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getFoodId() {
            return foodId;
        }

        public void setFoodId(int foodId) {
            this.foodId = foodId;
        }

        public int getQuanty() {
            return quanty;
        }

        public void setQuanty(int quanty) {
            this.quanty = quanty;
        }

        public int getTableId() {
            return tableId;
        }

        public void setTableId(int tableId) {
            this.tableId = tableId;
        }

        public Object getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(Object totalPrice) {
            this.totalPrice = totalPrice;
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

        public Object getDay() {
            return day;
        }

        public void setDay(Object day) {
            this.day = day;
        }

        public Object getMonth() {
            return month;
        }

        public void setMonth(Object month) {
            this.month = month;
        }

        public Object getYear() {
            return year;
        }

        public void setYear(Object year) {
            this.year = year;
        }

        public String getFoodName() {
            return foodName;
        }

        public void setFoodName(String foodName) {
            this.foodName = foodName;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getUserPhoneNumber() {
            return userPhoneNumber;
        }

        public void setUserPhoneNumber(Object userPhoneNumber) {
            this.userPhoneNumber = userPhoneNumber;
        }
    }

}
