package com.tradix.Models;

import com.google.gson.annotations.SerializedName;

public class AmountModel {
    @SerializedName("User_id")
    private int id;
    @SerializedName("Stock_id")
    private int id1;
    @SerializedName("Stock_price")
    private int price;
    @SerializedName("Count")
    private int count;
    @SerializedName("Total_price")
    private int pricee;

    public int getPricee() {
        return pricee;
    }

    public void setPricee(int pricee) {
        this.pricee = pricee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
