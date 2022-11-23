package com.example.pos_android.data.model.request;

public class TableRequestData {
    final String date;
    final String time;
    final String members;
    final String seatLocation;

    public TableRequestData(String date, String time, String members, String seatLocation) {
        this.date = date;
        this.time = time;
        this.members = members;
        this.seatLocation = seatLocation;
    }
}