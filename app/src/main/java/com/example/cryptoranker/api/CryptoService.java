package com.example.cryptoranker.api;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.annotation.Nullable;

import com.example.cryptoranker.IListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CryptoService extends Service {
    private static final String url = "https://pro-api.coinmarketcap.com/v1/";
    private volatile boolean running;

    private Retrofit retrofit;
    private CryptoWebService cryptoWebService;

    //Thread Stuff
    private Thread workerThread;
    private String idCSV = "";
    private List<Data> cryptolist = new ArrayList<>();

    static List<IListener> listeners = new ArrayList<>();
    static List<String> idList = new ArrayList<>();
    static HashMap<Integer, String> logoList = new HashMap<>();

    public static void add(IListener listener) {
        listeners.add(listener);
    }

    /* The format we want our API to return */
    private static final String start = "1";
    /* The number of results to return we want our API to return */
    private static final String limit = "50";
    /* The fiatType we want our API to return */
    private static final String currency = "USD";

    private final IBinder mBinder = new CryptoServiceBinder();

    public class CryptoServiceBinder extends Binder{
        public CryptoService getService(){
            return CryptoService.this;
        }
    }

    @Override
    public void onCreate(){
        super.onCreate();

        running = true;

        //For logging the response
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        //Instantiate retrofit
        retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        cryptoWebService = retrofit.create(CryptoWebService.class);


        //create thread
        workerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(running){

                    Call<Crypto> cryptoListings = cryptoWebService.getCryptos(start,limit,currency);

                    cryptoListings.enqueue(new Callback<Crypto>() {
                        @Override
                        public void onResponse(Call<Crypto> call, Response<Crypto> response) {

                            cryptolist = response.body().getData();

                            for(Data data : cryptolist){
                                idList.add(String.valueOf(data.getId()));
                            }

                            //Join list with id's to comma separated string
                            idCSV = String.join(",", idList);


                            //Call cryptocurrency/info for metadata on all cryptos
                            Call<CryptoInfo> callCryptoInfo = cryptoWebService.getMetadata(idCSV);

                            callCryptoInfo.enqueue(new Callback<CryptoInfo>() {
                                @Override
                                public void onResponse(Call<CryptoInfo> call, Response<CryptoInfo> response) {
                                    CryptoInfo body = response.body();
                                    // Map objects into Map
                                    Map<String, CryptoInfoMeta> map = body.getCryptoInfoDataMap();
                                    Map<Integer, String> descList = new HashMap<>();

                                    // Add logos with ID's to Hashmap
                                    for(CryptoInfoMeta metadata : map.values()){
                                        logoList.put(metadata.getId(), metadata.getLogo());
                                        descList.put(metadata.getId(), metadata.getDescription());
                                    }

                                    //Set logos to each cryptodata
                                    for(Data data : cryptolist){
                                        data.setLogo(logoList.get(data.getId()));
                                        data.setDescription(descList.get(data.getId()));
                                    }

                                    for (IListener listener : CryptoService.listeners) {
                                        listener.exec(cryptolist);
                                    }
                                }

                                @Override
                                public void onFailure(Call<CryptoInfo> call, Throwable t) {
                                    Log.i("Log", t.getMessage());
                                }
                            });

                        }

                        @Override
                        public void onFailure(Call<Crypto> call, Throwable t) {
                            Log.i("Log", t.getMessage());
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
