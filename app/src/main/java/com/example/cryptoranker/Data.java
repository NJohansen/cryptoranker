package com.example.cryptoranker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Data implements Serializable {
    private static final long serialVersionUID = 42L;
    @SerializedName("id")
    @Expose private int id;
    @SerializedName("name")
    @Expose private String name;
    @SerializedName("symbol")
    @Expose private String symbol;
    @SerializedName("circulating_supply")
    @Expose private double circulating_supply;
    @SerializedName("max_supply")
    @Expose private double max_supply;
    @SerializedName("cmc_rank")
    @Expose private int cmc_rank;
    @SerializedName("quote")
    @Expose private Quote quote;

    private String description;
    private String logo;

    public int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getMax_supply() {
        return max_supply;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public double getCirculating_supply() {
        return circulating_supply;
    }

    int getCmc_rank() {
        return cmc_rank;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setCirculating_supply(int circulating_supply) {
        this.circulating_supply = circulating_supply;
    }

    public void setMax_supply(int max_supply) {
        this.max_supply = max_supply;
    }

    public void setCmc_rank(int cmc_rank) {
        this.cmc_rank = cmc_rank;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}

