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

import de.hdodenhof.circleimageview.CircleImageView;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

    private ArrayList<String> text;
    private ArrayList<String> images = new ArrayList<>();
    private Context context;

    public MyListAdapter(Context context, ArrayList<String> text, ArrayList<String> images) {
        this.text = text;
        this.images = images;
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
        Glide.with(context).asBitmap().load(images.get(position)).into(holder.image);
        holder.label.setText(text.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView image;
        TextView label;
        RelativeLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            label = itemView.findViewById(R.id.label);
            layout = itemView.findViewById(R.id.parent_layout);
        }
    }

}
