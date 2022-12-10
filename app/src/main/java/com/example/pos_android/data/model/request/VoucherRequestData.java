package com.example.pos_android.data.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VoucherRequestData {

    @SerializedName("voucherTitle")
    @Expose
    private String voucherTitle;
    @SerializedName("voucherCode")
    @Expose
    private String voucherCode;
    @SerializedName("voucherDiscount")
    @Expose
    private String voucherDiscount;

    @SerializedName("date")
    @Expose
    private String expiryDate;

    public VoucherRequestData(String voucherTitle, String voucherCode, String voucherDiscount, String expiryDate) {
        this.voucherTitle = voucherTitle;
        this.voucherCode = voucherCode;
        this.voucherDiscount = voucherDiscount;
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getVoucherTitle() {
        return voucherTitle;
    }

    public void setVoucherTitle(String voucherTitle) {
        this.voucherTitle = voucherTitle;
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
}
