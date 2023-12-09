package com.tradix.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PriceModel {
    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private String code;

    @SerializedName("data")
    private List<DataItem> data;

    @SerializedName("message")
    private String message;

    // Getters and setters for the fields

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

    public List<DataItem> getData() {
        return data;
    }

    public void setData(List<DataItem> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Inner class representing the "data" array
    public static class DataItem {
        @SerializedName("Id")
        private int id;

        @SerializedName("Name")
        private String name;

        @SerializedName("Price")
        private int price;

        @SerializedName("Time")
        private String time;

        @SerializedName("Preprice")
        private int preprice;

        @SerializedName("Updated_time")
        private String updatedTime;

        // Getters and setters for the fields

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getPreprice() {
            return preprice;
        }

        public void setPreprice(int preprice) {
            this.preprice = preprice;
        }

        public String getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(String updatedTime) {
            this.updatedTime = updatedTime;
        }
    }
}
