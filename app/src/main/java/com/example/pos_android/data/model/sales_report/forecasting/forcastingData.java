package com.example.pos_android.data.model.sales_report.forecasting;

import java.util.ArrayList;

public class forcastingData {
    public ArrayList<dataPrediction> prediction;
    public ArrayList<dataRevenue> revenue;

    public ArrayList<dataPrediction> getPrediction() {
        return prediction;
    }

    public void setPrediction(ArrayList<dataPrediction> prediction) {
        this.prediction = prediction;
    }

    public ArrayList<dataRevenue> getRevenue() {
        return revenue;
    }

    public void setRevenue(ArrayList<dataRevenue> revenue) {
        this.revenue = revenue;
    }
}
