package com.example.cryptoranker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView view;

    private ArrayList<String> texts = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();
        view = (RecyclerView) findViewById(R.id.list);
        RecyclerView.Adapter adapter = new MyListAdapter(this, texts, images);
        view.setAdapter(adapter);
        view.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setup() {
        images.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        texts.add("Havasu Falls");

        images.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        texts.add("Trondheim");

        images.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        texts.add("Portugal");

        images.add("https://i.redd.it/j6myfqglup501.jpg");
        texts.add("Rocky Mountain National Park");


        images.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        texts.add("Mahahual");

        images.add("https://i.redd.it/k98uzl68eh501.jpg");
        texts.add("Frozen Lake");


        images.add("https://i.redd.it/glin0nwndo501.jpg");
        texts.add("White Sands Desert");

        images.add("https://i.redd.it/obx4zydshg601.jpg");
        texts.add("Austrailia");

        images.add("https://i.imgur.com/ZcLLrkY.jpg");
        texts.add("Washington");

    }


}
