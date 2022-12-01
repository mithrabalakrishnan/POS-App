package com.example.pos_android.data.model.sales_report;

public class BestSellingReportResponse {
    public boolean status;
    public String message;
    public BestSellingReportData data;


    public boolean isSuccess() {
        return status;
    }

    public void setSuccess(boolean success) {
        this.status = success;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BestSellingReportData getData() {
        return data;
    }

    public void setData(BestSellingReportData data) {
        this.data = data;
    }
}

