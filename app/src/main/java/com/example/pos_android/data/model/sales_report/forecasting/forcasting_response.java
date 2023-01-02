package com.example.pos_android.data.model.sales_report.forecasting;

public class forcasting_response {
    public boolean status;
    public String message;
    public forcastingData data;

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

    public forcastingData getData() {
        return data;
    }

    public void setData(forcastingData data) {
        this.data = data;
    }
}

