package com.example.pos_android.data.model.sales_report;

public class IncomePerItemMonthlyResponse {
    public int statuscode;
    public boolean success;
    public String message;
    public IncomePerItemMonthlyData data;

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

    public IncomePerItemMonthlyData getData() {
        return data;
    }

    public void setData(IncomePerItemMonthlyData data) {
        this.data = data;
    }
}

