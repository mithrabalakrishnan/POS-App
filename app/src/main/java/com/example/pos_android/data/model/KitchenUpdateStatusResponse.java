package com.example.pos_android.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KitchenUpdateStatusResponse {

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

    public class Data {

        @SerializedName("orderid")
        @Expose
        private Integer orderid;
        @SerializedName("userid")
        @Expose
        private Integer userid;
        @SerializedName("foodid")
        @Expose
        private Integer foodid;
        @SerializedName("quanty")
        @Expose
        private Integer quanty;
        @SerializedName("tableid")
        @Expose
        private Integer tableid;
        @SerializedName("totalPrice")
        @Expose
        private Integer totalPrice;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("time")
        @Expose
        private String time;
        @SerializedName("day")
        @Expose
        private Integer day;
        @SerializedName("month")
        @Expose
        private String month;
        @SerializedName("year")
        @Expose
        private Integer year;
        @SerializedName("status")
        @Expose
        private String status;

        public Integer getOrderid() {
            return orderid;
        }

        public void setOrderid(Integer orderid) {
            this.orderid = orderid;
        }

        public Integer getUserid() {
            return userid;
        }

        public void setUserid(Integer userid) {
            this.userid = userid;
        }

        public Integer getFoodid() {
            return foodid;
        }

        public void setFoodid(Integer foodid) {
            this.foodid = foodid;
        }

        public Integer getQuanty() {
            return quanty;
        }

        public void setQuanty(Integer quanty) {
            this.quanty = quanty;
        }

        public Integer getTableid() {
            return tableid;
        }

        public void setTableid(Integer tableid) {
            this.tableid = tableid;
        }

        public Integer getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(Integer totalPrice) {
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

        public Integer getDay() {
            return day;
        }

        public void setDay(Integer day) {
            this.day = day;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }
}
