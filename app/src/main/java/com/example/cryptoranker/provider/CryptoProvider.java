package com.example.cryptoranker;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Room;

import com.example.cryptoranker.database.AppDatabase;
import com.example.cryptoranker.database.CoinDao;

public class CryptoProvider extends ContentProvider  {

    //Define handle for the app database.
    private AppDatabase appDatabase;

    //Define Data Access Object so we can perform operations to the database.
    private CoinDao coinDao;


    //Define the authority of the content provider
    public static final String AUTHORITY = "com.example.cryptoranker.cryptoprovider,provider";
    //Define database name
    private static final String DBNAME = "db";

    @Override
    public boolean onCreate() {

        //Create new database object
        appDatabase = Room.databaseBuilder(getContext(), AppDatabase.class, DBNAME).build();

        coinDao = appDatabase.coinDao();

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
