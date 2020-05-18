package com.example.cryptoranker;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptoranker.database.Coin;

import java.util.List;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.ViewHolder> {
    List<Coin> coinList;
    private Cursor mCursor;

    public CoinAdapter(List<Coin> coinList) {
        this.coinList = coinList;
    }

    @Override
    public CoinAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.coin_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Coin coin = coinList.get(position);
        holder.column1.setText(String.valueOf(coin.uid));
        holder.column2.setText(coin.assetName);
        holder.column3.setText(String.valueOf(coin.price));
    }

    @Override
    public int getItemCount() {
        return coinList == null ? 0 : coinList.size();
    }

    void setCoins(Cursor cursor) {
        mCursor = cursor;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView column1;
        TextView column2;
        TextView column3;


        ViewHolder(View container) {
            super(container);
            column1 = container.findViewById(R.id.tvColumn1);
            column2 = container.findViewById(R.id.tvColumn2);
            column3 = container.findViewById(R.id.tvColumn3);
        }
    }
}
