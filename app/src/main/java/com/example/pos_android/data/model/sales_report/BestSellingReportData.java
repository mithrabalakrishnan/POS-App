package com.example.pos_android.data.model.sales_report;

import java.util.ArrayList;

public class BestSellingReportData {
    public String type;
    public ArrayList<FoodDetail> food_details;
    public ArrayList<Double> chart_data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<FoodDetail> getFood_details() {
        return food_details;
    }

    public void setFood_details(ArrayList<FoodDetail> food_details) {
        this.food_details = food_details;
    }

    public ArrayList<Double> getChart_data() {
        return chart_data;
    }

    public void setChart_data(ArrayList<Double> chart_data) {
        this.chart_data = chart_data;
    }
}

