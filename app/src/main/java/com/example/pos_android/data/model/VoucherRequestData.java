package com.example.pos_android.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VoucherRequestData {

    @SerializedName("name")
    @Expose
    private String voucher_name;
    @SerializedName("Category")
    @Expose
    private String Category_name;
    @SerializedName("Price")
    @Expose
    private String Price;

    public VoucherRequestData(String voucher_name, String category_name, String price) {
        this.voucher_name = voucher_name;
        Category_name = category_name;
        Price = price;
    }

    public String getVoucher_name() {
        return voucher_name;
    }

    public void setVoucher_name(String voucher_name) {
        this.voucher_name = voucher_name;
    }

    public String getCategory_name() {
        return Category_name;
    }

    public void setCategory_name(String category_name) {
        Category_name = category_name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
