package com.example.pos_android.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddVoucherResponse {
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

        @SerializedName("voucherId")
        @Expose
        private Integer voucherId;
        @SerializedName("voucherCode")
        @Expose
        private String voucherCode;
        @SerializedName("voucherDiscount")
        @Expose
        private String voucherDiscount;
        @SerializedName("voucherTitle")
        @Expose
        private String voucherTitle;
        @SerializedName("date")
        @Expose
        private String date;

        public Integer getVoucherId() {
            return voucherId;
        }

        public void setVoucherId(Integer voucherId) {
            this.voucherId = voucherId;
        }

        public String getVoucherCode() {
            return voucherCode;
        }

        public void setVoucherCode(String voucherCode) {
            this.voucherCode = voucherCode;
        }

        public String getVoucherDiscount() {
            return voucherDiscount;
        }

        public void setVoucherDiscount(String voucherDiscount) {
            this.voucherDiscount = voucherDiscount;
        }

        public String getVoucherTitle() {
            return voucherTitle;
        }

        public void setVoucherTitle(String voucherTitle) {
            this.voucherTitle = voucherTitle;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
