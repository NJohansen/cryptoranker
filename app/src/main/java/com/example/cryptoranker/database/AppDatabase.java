package com.example.cryptoranker.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Coin.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CoinDao coinDao();

    //Define database name
    private static final String DBNAME = "db";

    //Define singleton
    private static AppDatabase singleton;

    public static synchronized AppDatabase getInstance(Context context){
        if(singleton == null){

            //Create new database object and whipe database
            singleton = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DBNAME).fallbackToDestructiveMigration().build();
        }
        return singleton;
    }
}
