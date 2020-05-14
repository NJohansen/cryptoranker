package com.example.cryptoranker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    MyListAdapter adapter;
    RecyclerView view;

    private ArrayList<String> texts = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (RecyclerView) findViewById(R.id.list);
        adapter = new MyListAdapter(this, new ArrayList<>());
        view.setAdapter(adapter);
        view.setLayoutManager(new LinearLayoutManager(this));

        CryptoService.add((data) -> {
            adapter.set(data);
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        CryptoService.listeners = new ArrayList<>();
    }
}
