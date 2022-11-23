package com.example.pos_android.data.model;

public class DateModel {
    private String date;
    private boolean isSelected;

    public DateModel(String date) {
        this.date = date;
        this.isSelected = false;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
