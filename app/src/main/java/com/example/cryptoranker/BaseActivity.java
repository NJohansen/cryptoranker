package com.example.cryptoranker;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class BaseActivity extends AppCompatActivity {
    List<Data> cryptoList = new ArrayList<>();

    protected CryptoService cryptoService;
    protected boolean mBound;

    RecyclerView view;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to CryptoService
        Intent intent = new Intent(this, CryptoService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("service_exercise",
                    "CryptoService onDestroy - Current Thread ID-"
                            + Thread.currentThread().getId()
                            + " for thread"
                            + Thread.currentThread().getName());

            // Bind CryptoService and cast the IBinder and get CryptoService instance
            CryptoService.CryptoServiceBinder binder = (CryptoService.CryptoServiceBinder) service;
            cryptoService = binder.getService();
            mBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("Problem", "Service disconnected");
            mBound = false;
        }
    };



    public void updateList(){

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBound) {
                    for (Data data : cryptoService.getCryptos()) {
                        cryptoList.add(data);
                    }
                }
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
