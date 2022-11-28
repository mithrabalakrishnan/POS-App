package com.example.pos_android.data.model.sales_report;

import java.util.ArrayList;
import java.util.List;

public class BestSellingReportData {
    public String type;
    public List<FoodDetail> food_details;
    public List<Double> chart_data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<FoodDetail> getFood_details() {
        return food_details;
    }

    public void setFood_details(ArrayList<FoodDetail> food_details) {
        this.food_details = food_details;
    }

    public List<Double> getChart_data() {
        return chart_data;
    }

    public void setChart_data(ArrayList<Double> chart_data) {
        this.chart_data = chart_data;
    }

    @Override
    public String toString() {
        return "BestSellingReportData{" +
                "type='" + type + '\'' +
                ", food_details=" + food_details +
                ", chart_data=" + chart_data +
                '}';
    }
}

