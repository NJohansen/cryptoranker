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
    private List<Data> data = new ArrayList<>();
    private Context context;

    public MyListAdapter(Context context, List<Data> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Glide.with(context).asBitmap().load(images.get(position)).into(holder.image);
        holder.label.setText(data.get(position).getName());
        holder.value.setText(data.get(position).getSymbol()+ " - " + String.valueOf(data.get(position).getMax_supply()));
    }

    public void set(List<Data> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(data == null){
            return 0;
        }
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView image;
        TextView label;
        TextView value;
        RelativeLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            label = itemView.findViewById(R.id.label);
            value = itemView.findViewById(R.id.value);
            layout = itemView.findViewById(R.id.parent_layout);
        }
    }

}
