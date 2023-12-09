package com.tradix.Models;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SoldTransactionModel {
    @SerializedName("status")
    private String status;
    @SerializedName("code")
    private String code;
    @SerializedName("data")
    private ArrayList<SoldTransactionData> data;
    @SerializedName("message")
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<SoldTransactionData> getData() {
        return data;
    }

    public void setData(ArrayList<SoldTransactionData> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class SoldTransactionData {

        @SerializedName("User_id")
        private int userId;

        @SerializedName("Stock_id")
        private int stockId;

        @SerializedName("Stock_price")
        private int stockPrice;

        @SerializedName("Sold_count")
        private int soldCount;

        @SerializedName("Sold_price")
        private int soldPrice;

        @SerializedName("Sold_time")
        private String soldTime;

        @SerializedName("Stock_name")
        private String stockName;


        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getStockId() {
            return stockId;
        }

        public void setStockId(int stockId) {
            this.stockId = stockId;
        }

        public int getStockPrice() {
            return stockPrice;
        }

        public void setStockPrice(int stockPrice) {
            this.stockPrice = stockPrice;
        }

        public int getSoldCount() {
            return soldCount;
        }

        public void setSoldCount(int soldCount) {
            this.soldCount = soldCount;
        }

        public int getSoldPrice() {
            return soldPrice;
        }

        public void setSoldPrice(int soldPrice) {
            this.soldPrice = soldPrice;
        }

        public String getSoldTime() {
            return soldTime;
        }

        public void setSoldTime(String soldTime) {
            this.soldTime = soldTime;
        }

        public String getStockName() {
            return stockName;
        }

        public void setStockName(String stockName) {
            this.stockName = stockName;
        }
    }
}
