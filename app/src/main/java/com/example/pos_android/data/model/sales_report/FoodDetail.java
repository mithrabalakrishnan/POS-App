package com.example.pos_android.data.model.sales_report;

import java.util.Objects;
public class FoodDetail implements Comparable<FoodDetail> {
    public String food;
    public Double totalPrice;

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public Double getSale_amount() {
        return totalPrice;
    }

    public void setSale_amount(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "FoodDetail{" +
                "food='" + food + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return !super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(food, totalPrice);
    }


    @Override
    public int compareTo(FoodDetail o) {
        if(Objects.equals(this.food, o.food))
            return 0;
        else if(Objects.equals(this.totalPrice, o.totalPrice))
            return 1;
        else
            return -1;
    }
}
