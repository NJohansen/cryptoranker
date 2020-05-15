package com.example.cryptoranker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class MainActivity extends BaseActivity {
    MyListAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();

    }

    private void initList(){
        //Find recyclerview
        recyclerView = findViewById(R.id.list);

        // Create MyListAdapter ready to pass data
        adapter = new MyListAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
