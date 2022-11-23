package com.example.pos_android.data.model;

public class TimeModel {
    private String time;
    private boolean isSelected;

    public TimeModel(String time) {
        this.time = time;
        this.isSelected = false;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
