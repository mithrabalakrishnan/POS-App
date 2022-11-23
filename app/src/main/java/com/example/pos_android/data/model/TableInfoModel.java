package com.example.pos_android.data.model;

import com.google.gson.Gson;

import java.io.Serializable;

public class TableInfoModel implements Serializable {
    private String id;
    private String tableCategory;
    private String guestCount;
    private String time;
    private String date;

    public TableInfoModel(String id, String tableCategory, String guestCount, String time, String date) {
        this.tableCategory = tableCategory;
        this.guestCount = guestCount;
        this.time = time;
        this.date = date;
        this.id = id;
    }
    public String getTableCategory() {
        return tableCategory;
    }

    public void setTableCategory(String tableCategory) {
        this.tableCategory = tableCategory;
    }

    public String getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(String guestCount) {
        this.guestCount = guestCount;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static TableInfoModel convertToTableInfo(String json) {
        return new Gson().fromJson(json, TableInfoModel.class);
    }

    public static String convertToJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}

