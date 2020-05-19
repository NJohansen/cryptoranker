package com.example.cryptoranker.database;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CoinDao {

    @Query("SELECT * FROM coins")
    Cursor getAll();

    @Query("SELECT * FROM " + Coin.TABLE_NAME + " WHERE " + Coin.COLUMN_ID + " = :id")
    Cursor getById(long id);

    @Insert
    long insert(Coin coin);

    @Delete
    void delete(Coin coin);

    @Query("DELETE FROM " + Coin.TABLE_NAME)
    void deleteAll();

    @Query("SELECT COUNT(*) FROM " + Coin.TABLE_NAME)
    int count();
}
