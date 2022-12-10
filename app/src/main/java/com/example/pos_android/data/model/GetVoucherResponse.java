package com.example.pos_android.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetVoucherResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<VoucherItem> data = null;

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

    public List<VoucherItem> getData() {
        return data;
    }

    public void setData(List<VoucherItem> data) {
        this.data = data;
    }

    public class VoucherItem {

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
