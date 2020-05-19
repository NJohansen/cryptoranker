package com.example.cryptoranker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptoranker.api.Data;

import java.util.ArrayList;
import java.util.List;

public class CryptoListFragment extends Fragment {
    private RecyclerView list;
    private MyListAdapter adapter;
    private MyListAdapter.OnClickListener listener;
    private List<Data> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.list, container, false);
        list = view.findViewById(R.id.list);
        adapter = new MyListAdapter(getContext(), new ArrayList<>());
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.onClick(listener);
        adapter.set(data);
        return view;
    }

    public void setData(List<Data> data) {
        this.data = data;

        if (adapter != null) {
            adapter.set(data);
        }
    }

    public void onClick(MyListAdapter.OnClickListener listener) {
        this.listener = listener;

        if (adapter != null) {
            adapter.onClick(listener);
        }
    }
}
