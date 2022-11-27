package com.example.pos_android.data.model.sales_report;

import java.util.ArrayList;

public class SalesReportResponseData {
    public String type;
    public String total_sale;
    public int total_customers;
    public ArrayList<Double> chart_data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTotal_sale() {
        return total_sale;
    }

    public void setTotal_sale(String total_sale) {
        this.total_sale = total_sale;
    }

    public int getTotal_customers() {
        return total_customers;
    }

    public void setTotal_customers(int total_customers) {
        this.total_customers = total_customers;
    }

    public ArrayList<Double> getChart_data() {
        return chart_data;
    }

    public void setChart_data(ArrayList<Double> chart_data) {
        this.chart_data = chart_data;
    }
}
