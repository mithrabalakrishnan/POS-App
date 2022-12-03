package com.example.pos_android.data.model;

public class CouponsData {
    private String txtCouponName;
    private String txtExpDate;
    private String Amount;
    private int image;
    private int percentage;

    public CouponsData(String txtCouponName, String txtExpDate, String Amount, int image, int percentage) {
        this.txtCouponName = txtCouponName;
        this.txtExpDate = txtExpDate;
        this.Amount = Amount;
        this.image = image;
        this.percentage = percentage;
    }

    public String getTxtCouponName() {
        return txtCouponName;
    }

    public void setTxtCouponName(String txtCouponName) {
        this.txtCouponName = txtCouponName;
    }

    public String getTxtExpDate() {
        return txtExpDate;
    }

    public void setTxtExpDate(String txtExpDate) {
        this.txtExpDate = txtExpDate;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
