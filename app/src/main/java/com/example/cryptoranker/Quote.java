package com.example.cryptoranker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Quote implements Serializable {
    private static final long serialVersionUID = 43L;
    @SerializedName("USD")
    @Expose
    private USD usd;

    public USD getUsd(){
        return usd;
    }

    public void setUsd(USD usd) {
        this.usd = usd;
    }

}
