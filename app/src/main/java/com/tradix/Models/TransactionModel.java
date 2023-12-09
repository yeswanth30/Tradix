package com.tradix.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class TransactionModel {
    @SerializedName("status")
    private String status;
    @SerializedName("code")
    private String code;
    @SerializedName("data")
    private ArrayList<TransactionData> data;
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

    public ArrayList<TransactionData> getData() {
        return data;
    }

    public void setData(ArrayList<TransactionData> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class TransactionData {

        @SerializedName("User_id")
        private int userId;

        @SerializedName("Stock_id")
        private int stockId;

        @SerializedName("Stock_price")
        private int stockPrice;

        @SerializedName("Bought_count")
        private int boughtCount;

        @SerializedName("Bought_price")
        private int boughtPrice;

        @SerializedName("Bought_time")
        private String boughtTime;

        @SerializedName("Stock_name")
        private String stockName;

        // Getters and setters for the fields

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

        public int getBoughtCount() {
            return boughtCount;
        }

        public void setBoughtCount(int boughtCount) {
            this.boughtCount = boughtCount;
        }

        public int getBoughtPrice() {
            return boughtPrice;
        }

        public void setBoughtPrice(int boughtPrice) {
            this.boughtPrice = boughtPrice;
        }

        public String getBoughtTime() {
            return boughtTime;
        }

        public void setBoughtTime(String boughtTime) {
            this.boughtTime = boughtTime;
        }

        public String getStockName() {
            return stockName;
        }

        public void setStockName(String stockName) {
            this.stockName = stockName;
        }
    }
}