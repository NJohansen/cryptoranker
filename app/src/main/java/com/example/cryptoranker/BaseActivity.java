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
    protected CryptoService cryptoService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, CryptoService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("service_exercise",
                    "ServiceConnection onServiceConnected - Current Thread ID-"
                            + Thread.currentThread().getId()
                            + " for thread"
                            + Thread.currentThread().getName());

            // Bind CryptoService and cast the IBinder and get CryptoService instance
            CryptoService.CryptoServiceBinder binder = (CryptoService.CryptoServiceBinder) service;
            cryptoService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("Problem", "Service disconnected");
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
