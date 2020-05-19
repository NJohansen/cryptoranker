package com.example.cryptoranker.database;

import android.content.ContentValues;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Coin.TABLE_NAME)
public class Coin {

    public static final String TABLE_NAME = "coins";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_ASSET_NAME = "asset_name";
    public static final String COLUMN_PRICE = "price";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long uid;

    @ColumnInfo(name = COLUMN_ASSET_NAME)
    public String assetName;

    @ColumnInfo(name = COLUMN_PRICE, defaultValue = "0.00")
    public double price;

    public static Coin fromContentValues(@Nullable ContentValues values) {
        final Coin coin = new Coin();
        if (values != null && values.containsKey(COLUMN_ID)) {
            coin.uid = values.getAsLong(COLUMN_ID);
        }
        if (values != null && values.containsKey(COLUMN_ASSET_NAME)) {
            coin.assetName = values.getAsString(COLUMN_ASSET_NAME);
        }
        if (values != null && values.containsKey(COLUMN_PRICE)) {
            coin.price = values.getAsDouble(COLUMN_PRICE);
        }
        return coin;
    }
}
