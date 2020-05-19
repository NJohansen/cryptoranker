package com.example.cryptoranker;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.cryptoranker.api.CryptoService;
import com.example.cryptoranker.api.Data;
import com.example.cryptoranker.database.Coin;
import com.example.cryptoranker.provider.CryptoProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private List<Data> data = new ArrayList<>();
    private CryptoViewFragment view;
    private CryptoListFragment list;
    private AsyncTask<Void, Void, Void> asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crypto_list);

        CryptoService.add(data -> {
            this.data = data;

            populateDatabase();

            if (list != null) {
                list.setData(data);
            }

            if (view != null) {
                view.setData(data);
            }

        });

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                initList(savedInstanceState);
            }
        };

        this.getOnBackPressedDispatcher().addCallback(this, callback);

        initList(savedInstanceState);
    }

    private void initList(Bundle savedInstanceState) {
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            list = new CryptoListFragment();
            list.onClick(position -> onListClick(position));
            list.setData(data);

            list.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, list).commit();
        } else {
            list = (CryptoListFragment) getSupportFragmentManager().findFragmentById(R.id.crypto_list_fragment);
            list.onClick(position -> onListClick(position));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    public void onListClick(int position) {
        view = (CryptoViewFragment) getSupportFragmentManager().findFragmentById(R.id.crypto_view_fragment);

        if (view != null) {
            view.setData(data);
            view.updateCryptoView(position);
        } else {
            view = new CryptoViewFragment();
            view.setData(data);
            Bundle args = new Bundle();
            args.putInt(CryptoViewFragment.ARG_POSITION, position);
            view.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, view);
            transaction.commit();
        }
    }

    /**
     * Insert data from service to database through contentProvider
     */
    private void populateDatabase() {
        stopAsyncTask();
        asyncTask = new MyAsyncTask();
        asyncTask.execute();
    }

    private void stopAsyncTask(){
        if(asyncTask != null){
            asyncTask.cancel(false);
        }
    }

    @SuppressLint("StaticFieldLeak")
    class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
            protected Void doInBackground(Void... voids) {
            Cursor cursor;
            ContentResolver cr = getContentResolver();

            String[] mProjection =
                    {
                            Coin.COLUMN_ID,
                            Coin.COLUMN_ASSET_NAME,
                            Coin.COLUMN_PRICE,
                    };

            Uri uri = CryptoProvider.URI_COIN;

            //Query for data in database
            cursor = cr.query(uri, mProjection, null, null, null);

            //If coins table is empty
            if(cursor.getCount() == 0){
                ContentValues newValues = new ContentValues();

                //For each data in
                for (Data coin : data){

                    //Add content to database through content provier
                    newValues.put(Coin.COLUMN_ASSET_NAME, coin.getName());
                    newValues.put(Coin.COLUMN_PRICE, coin.getQuote().getUsd().getPrice());
                    cr.insert(uri,newValues);
                }
            }

            return null;
        }

    }
}
