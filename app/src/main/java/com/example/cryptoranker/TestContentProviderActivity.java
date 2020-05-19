package com.example.cryptoranker;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptoranker.database.Coin;
import com.example.cryptoranker.provider.CryptoProvider;

import java.util.ArrayList;
import java.util.List;

//Test activity that show content provider works for querying data
public class TestContentProviderActivity extends AppCompatActivity {
    RecyclerView coinRecyclerView;
    CoinRetriever coinRetriever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        coinRecyclerView = findViewById(R.id.rc_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        coinRecyclerView.setLayoutManager(layoutManager);

        loadCoins();
    }

    private void loadCoins() {
        stopCoinRetriever();
        coinRetriever = new CoinRetriever();
        coinRetriever.execute();
    }

    private void stopCoinRetriever(){
        if(coinRetriever != null){
            coinRetriever.cancel(false);
        }
    }

    @SuppressLint("StaticFieldLeak")
    class CoinRetriever extends AsyncTask<Void, Void, List<Coin>> {
        @Override
        protected List<Coin> doInBackground(Void... voids) {
            List<Coin> coinList  = new ArrayList<>();
            Cursor cursor;
            ContentResolver cr = getContentResolver();

            String[] mProjection =
                    {
                            Coin.COLUMN_ID,
                            Coin.COLUMN_ASSET_NAME,
                            Coin.COLUMN_PRICE,
                    };

            Uri uri = CryptoProvider.URI_COIN;

            cursor = cr.query(uri, mProjection, null, null, null);

            assert cursor != null;
            while (cursor.moveToNext()){
                Coin coin = new Coin();
                coin.uid = cursor.getLong(0);
                coin.assetName = cursor.getString(1);
                coin.price = cursor.getInt(2);

                coinList.add(coin);
            }

            return coinList;
        }

        @Override
        protected void onPostExecute(List<Coin> coinList) {
            coinRecyclerView.setAdapter(new CoinAdapter(coinList));
        }

    }
}
