package com.example.cryptoranker.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cryptoranker.CryptoService;

@Database(entities = {Coin.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CoinDao coinDao();

    //Define database name
    private static final String DBNAME = "db";

    //Define singleton
    private static AppDatabase singleton;

    public static synchronized AppDatabase getInstance(Context context){

        if(singleton == null){

            //Create new database object
            singleton = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DBNAME).build();
            singleton.populateInitialData();

        }
        //Log.d("Coins", String.valueOf(CryptoService.getCryptoList().size()));
        return singleton;
    }

    /**
     * Inserts the dummy data into the database if it is currently empty.
     */
    private void populateInitialData() {
        if (coinDao().count() == 0) {
            runInTransaction(new Runnable() {
                @Override
                public void run() {
                    Coin coin = new Coin();
                    for (int i = 0; i < Coin.COINS.length; i++) {
                        coin.assetName = Coin.COINS[i];
                        coinDao().insert(coin);
                    }
                }
            });
        }
    }
}
