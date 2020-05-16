package com.example.cryptoranker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Crypto implements Serializable {
    private static final long serialVersionUID = 523L;
    @SerializedName("status")
    @Expose private Status status;
    @SerializedName("data")
    @Expose private List<Data> data;

    public Status getStatus(){
        return status;
    }

    List<Data> getData(){
        return data;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

}
