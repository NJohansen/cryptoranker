package com.example.cryptoranker.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cryptoranker.database.AppDatabase;
import com.example.cryptoranker.database.Coin;
import com.example.cryptoranker.database.CoinDao;

public class CryptoProvider extends ContentProvider  {

    //Define the authority of the content provider
    public static final String AUTHORITY = "com.example.cryptoranker.provider";
    //Define URI for the coin table
    public static final Uri URI_COIN = Uri.parse("content://" + AUTHORITY + "/" + Coin.TABLE_NAME);

    private static final int CODE_COIN_DIR = 1;
    private static final int CODE_COIN_ITEM = 2;

    // URI matcher
    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, Coin.TABLE_NAME, CODE_COIN_DIR);
        MATCHER.addURI(AUTHORITY, Coin.TABLE_NAME + "/*", CODE_COIN_ITEM);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final int code = MATCHER.match(URI_COIN);

        if(code == CODE_COIN_DIR || code == CODE_COIN_ITEM){
            final Context context = getContext();
            if(context == null){
                return null;
            }
            CoinDao coin = AppDatabase.getInstance(context).coinDao();

            final Cursor cursor;

            switch (code){
                case 1:
                    cursor = coin.getAll();
                    return cursor;
                case 2:
                    cursor = coin.getById(ContentUris.parseId(uri));
                    return cursor;
                default:
                    throw new IllegalArgumentException("Unknown URI: " + uri);
            }

        }
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
        switch (MATCHER.match(uri)){
            case CODE_COIN_DIR:
                final Context context = getContext();
                if(context == null){
                    return null;
                }
                final long id = AppDatabase.getInstance(context).coinDao().insert(Coin.fromContentValues(values));
                return ContentUris.withAppendedId(uri,id);
            case CODE_COIN_ITEM:
                throw new IllegalArgumentException("Invalid URI, cannot insert with ID; " + uri);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
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
