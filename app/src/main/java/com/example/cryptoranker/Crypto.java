package com.example.cryptoranker;

import java.util.List;

public class Crypto {
    private Status status;
    private List<Data> data;

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
