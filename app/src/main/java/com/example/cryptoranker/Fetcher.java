package com.example.cryptoranker;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

public class Fetcher extends AsyncTask<Void, Void, Void> {
    String data;
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://sandbox-api.coinmarketcap.com");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream input = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line = "";
            while (line != null) {
                line = reader.readLine();
                data += line;
            }
        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        System.out.println(data);
    }

}
