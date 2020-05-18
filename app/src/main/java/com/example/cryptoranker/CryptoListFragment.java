package com.example.cryptoranker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.sip.SipSession;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
