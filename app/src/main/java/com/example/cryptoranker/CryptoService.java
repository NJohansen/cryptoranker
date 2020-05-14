package com.example.cryptoranker;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.text.Html;
import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CryptoService extends Service {
    private static final String uri = "https://pro-api.coinmarketcap.com/v1/";
    private volatile boolean running;

    private Retrofit retrofit;
    private CryptoWebService cryptoWebService;

    //Thread Stuff
    private Thread workerThread;

    static List<IListener> listeners = new ArrayList<>();

    static void add(IListener listener) {
        listeners.add(listener);
    }

    /* The format we want our API to return */
    private static final String start = "1";
    /* The number of results to return we want our API to return */
    private static final String limit = "50";
    /* The fiatType we want our API to return */
    private static final String currency = "USD";

    private final IBinder mBinder = new CryptoServiceBinder();

    class CryptoServiceBinder extends Binder{
        CryptoService getService(){
            return CryptoService.this;
        }
    }

    @Override
    public void onCreate(){
        super.onCreate();

        running = true;

        //Instantiate retrofit
        retrofit = new Retrofit.Builder().baseUrl(uri)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cryptoWebService = retrofit.create(CryptoWebService.class);


        //create thread
        workerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(running){
                    Call<Crypto> call = cryptoWebService.getCryptos(start,limit,currency);

                        call.enqueue(new Callback<Crypto>() {
                            @Override
                            public void onResponse(Call<Crypto> call, Response<Crypto> response) {

                                for (IListener listener : CryptoService.listeners) {
                                    listener.exec(response.body().getData());
                                }

                                Log.i("test", "Cryptos = response.body()");


                            }

                            @Override
                            public void onFailure(Call<Crypto> call, Throwable t) {
                                Log.i("autolog", t.getMessage());
                            }
                        });
                    //Have thread sleep for 10 seconds (10.000 ms)
                    try {
                        Thread.sleep(100000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        workerThread.start();
    }


    @Override
    public void onDestroy() {
        // Stop running the thread
        running = false;
        super.onDestroy();
        Log.i("service_exercise",
                "CryptoService onDestroy - Current Thread ID-"
                        + Thread.currentThread().getId()
                        + " for thread"
                        + Thread.currentThread().getName());

        workerThread = null;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
