package com.example.pos_android.data.model.sales_report;

public class SalesReportResponse {

    public boolean status;
    public String message;
    public SalesReportResponseData data;


    public boolean isSuccess() {
        return status;
    }

    public void setSuccess(boolean success) {
        this.status = success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SalesReportResponseData getData() {
        return data;
    }

    public void setData(SalesReportResponseData data) {
        this.data = data;
    }
}

