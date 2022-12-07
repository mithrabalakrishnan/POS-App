package com.example.pos_android.data.model.sales_report;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BestSellingReportWeeklyResponse {

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

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("food_name_list")
        @Expose
        private List<String> foodNameList = null;
        @SerializedName("food_details")
        @Expose
        private List<Object> foodDetails = null;
        @SerializedName("chart_data")
        @Expose
        private List<Integer> chartData = null;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<String> getFoodNameList() {
            return foodNameList;
        }

        public void setFoodNameList(List<String> foodNameList) {
            this.foodNameList = foodNameList;
        }

        public List<Object> getFoodDetails() {
            return foodDetails;
        }

        public void setFoodDetails(List<Object> foodDetails) {
            this.foodDetails = foodDetails;
        }

        public List<Integer> getChartData() {
            return chartData;
        }

        public void setChartData(List<Integer> chartData) {
            this.chartData = chartData;
        }

    }
}
