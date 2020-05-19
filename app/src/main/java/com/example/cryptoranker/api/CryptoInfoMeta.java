package com.example.cryptoranker.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CryptoInfoMeta implements Serializable {
    private static final long serialVersionUID = 53L;
    @SerializedName("logo")
    @Expose private String logo;

    @SerializedName("id")
    @Expose private int id;

    @SerializedName("description")
    @Expose private String description;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}