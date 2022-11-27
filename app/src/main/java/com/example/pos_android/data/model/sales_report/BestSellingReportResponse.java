package com.example.pos_android.data.model.sales_report;

public class BestSellingReportResponse {
    public int statuscode;
    public boolean success;
    public String message;
    public BestSellingReportData data;

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

