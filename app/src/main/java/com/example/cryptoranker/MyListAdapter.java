package com.example.cryptoranker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private List<Data> dataList = new ArrayList<>();
    private Context context;

    public MyListAdapter(Context context, List<Data> data) {
        this.dataList = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Data data = dataList.get(position);

        Glide.with(context).asBitmap().load(data.getLogo()).into(holder.image);
        holder.label.setText(data.getName());
        holder.value.setText("1 " + data.getSymbol()+ " = " + String.format("$%.2f", data.getQuote().getUsd().getPrice()) );
        holder.price_change.setText(String.format("%.2f", data.getQuote().getUsd().getPercentChange24h()) + "%");
        holder.cmc_rank.setText(String.valueOf(data.getCmc_rank()));

    }

    public void set(List<Data> data) {
        this.dataList = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(dataList == null){
            return 0;
        }
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView image;
        TextView label;
        TextView value;
        TextView price_change;
        RelativeLayout layout;
        TextView cmc_rank;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            label = itemView.findViewById(R.id.label);
            value = itemView.findViewById(R.id.value);
            price_change = itemView.findViewById(R.id.price_change);
            cmc_rank = itemView.findViewById(R.id.cmc_rank);
            layout = itemView.findViewById(R.id.parent_layout);
        }
    }

}
