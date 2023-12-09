package com.tradix.Models;

import com.google.gson.annotations.SerializedName;

public class sellstocksmodel {
    @SerializedName("User_id")
    private int User_id;
    @SerializedName("Stock_id")
    private int stock_id;
    @SerializedName("Stock_price")
    private int Stock_price;
    @SerializedName("Count")
    private int count;
    @SerializedName("Total_price")
    private int total_price;

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }

    public int getStock_price() {
        return Stock_price;
    }

    public void setStock_price(int stock_price) {
        Stock_price = stock_price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
}
