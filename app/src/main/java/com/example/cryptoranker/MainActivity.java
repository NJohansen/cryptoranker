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

public class MainActivity extends BaseActivity {

    RecyclerView view;
    RecyclerView.Adapter adapter;
    List<Data> cryptoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.list);
        adapter = new MyListAdapter(this, cryptoList);
        view.setAdapter(adapter);
        view.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBound) {
                    for (Data data : cryptoService.getCryptos()) {
                        cryptoList.add(data);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

}
