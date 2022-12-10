package com.example.pos_android.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerReportResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<ReportData> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ReportData> getData() {
        return data;
    }

    public void setData(List<ReportData> data) {
        this.data = data;
    }

    public class ReportData {

        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("visitList")
        @Expose
        private List<Integer> visitList = null;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<Integer> getVisitList() {
            return visitList;
        }

        public void setVisitList(List<Integer> visitList) {
            this.visitList = visitList;
        }

    }

}
