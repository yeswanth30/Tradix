package com.tradix.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsModel {
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private ArrayList<Data> data;
    @SerializedName("message")
    private String message;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
