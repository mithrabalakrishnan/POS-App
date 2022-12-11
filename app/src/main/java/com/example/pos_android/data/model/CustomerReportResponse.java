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
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("userReport")
        @Expose
        private List<UserReport> userReport = null;
        @SerializedName("totalUser")
        @Expose
        private Integer totalUser;

        public List<UserReport> getUserReport() {
            return userReport;
        }

        public void setUserReport(List<UserReport> userReport) {
            this.userReport = userReport;
        }

        public Integer getTotalUser() {
            return totalUser;
        }

        public void setTotalUser(Integer totalUser) {
            this.totalUser = totalUser;
        }

    }

    public class UserReport {

        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("visitList")
        @Expose
        private Integer visitList;
        @SerializedName("totalUser")
        @Expose
        private Object totalUser;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Integer getVisitList() {
            return visitList;
        }

        public void setVisitList(Integer visitList) {
            this.visitList = visitList;
        }

        public Object getTotalUser() {
            return totalUser;
        }

        public void setTotalUser(Object totalUser) {
            this.totalUser = totalUser;
        }

    }
}
