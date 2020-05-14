package com.example.cryptoranker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CryptoWebService {
    @Headers({
            "Accept: application/json",
            "X-CMC_PRO_API_KEY: a22f2194-44e2-480c-8add-16dd9778fcb1"
    })
    @GET("cryptocurrency/listings/latest")
    Call<Crypto> getCryptos(
            @Query("start") String start,
            @Query("limit") String limit,
            @Query("convert") String currency
    );
}
