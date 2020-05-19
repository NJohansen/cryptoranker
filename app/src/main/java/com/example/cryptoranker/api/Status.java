package com.example.cryptoranker.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Status implements Serializable {
    private static final long serialVersionUID = 46L;
    @SerializedName("timestamp")
    @Expose private String timestamp;
    @SerializedName("error_code")
    @Expose private int errorCode;
    @SerializedName("error_message")
    @Expose private String errorMessage;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
