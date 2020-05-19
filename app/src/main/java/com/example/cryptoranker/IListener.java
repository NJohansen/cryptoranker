package com.example.cryptoranker;

import com.example.cryptoranker.api.Data;

import java.util.List;

public interface IListener {
    public void exec(List<Data> list);
}