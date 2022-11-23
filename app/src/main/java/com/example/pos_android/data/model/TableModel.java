package com.example.pos_android.data.model;

public class TableModel {
    private String tablePreference;
    private boolean isSelected;

    public TableModel(String date) {
        this.tablePreference = date;
        this.isSelected = false;
    }

    public String getTable() {
        return tablePreference;
    }

    public void setTablePreference(String tablePreference) {
        this.tablePreference = tablePreference;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
