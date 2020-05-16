package com.example.cryptoranker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public  class USD implements Serializable {
    private static final long serialVersionUID = 44L;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("volume_24")
    @Expose private double volume24h;
    @SerializedName("market_cap")
    @Expose private double marketCap;
    @SerializedName("percent_change_24h")
    @Expose private double percentChange24h;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVolume24h() {
        return volume24h;
    }

    public void setVolume24h(double volume24h) {
        this.volume24h = volume24h;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public double getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(double percentChange24h) {
        this.percentChange24h = percentChange24h;
    }
}
