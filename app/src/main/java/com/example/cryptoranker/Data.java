package com.example.cryptoranker;

public class Data {
    private int id;
    private String name;
    private String symbol;
    private double circulating_supply;
    private double max_supply;
    private int cmc_rank;

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




}
