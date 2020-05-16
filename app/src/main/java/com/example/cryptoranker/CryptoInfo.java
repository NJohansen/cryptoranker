package com.example.cryptoranker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

public class CryptoInfo implements Serializable {
    private static final long serialVersionUID = 55L;
    @SerializedName("status")
    @Expose private Status status;

    @SerializedName("data")
    @Expose private Map<String, CryptoInfoMeta> cryptoMetaMap;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Map<String, CryptoInfoMeta> getCryptoInfoDataMap() {
        return cryptoMetaMap;
    }

    public void setCryptoInfoDataMap(Map<String, CryptoInfoMeta> cryptoInfoDataMap) {
        this.cryptoMetaMap = cryptoInfoDataMap;
    }
}
