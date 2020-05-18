package com.example.cryptoranker;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private List<Data> data = new ArrayList<>();
    private CryptoViewFragment view;
    private CryptoListFragment list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crypto_list);

        CryptoService.add(data -> {
            this.data = data;

            if (list != null) {
                list.setData(data);
            }

            if (view != null) {
                view.setData(data);
            }
        });

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                initList(savedInstanceState);
            }
        };

        this.getOnBackPressedDispatcher().addCallback(this, callback);

        initList(savedInstanceState);
    }

    private void initList(Bundle savedInstanceState) {
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            list = new CryptoListFragment();
            list.onClick(position -> onListClick(position));
            list.setData(data);

            list.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, list).commit();
        } else {
            list = (CryptoListFragment) getSupportFragmentManager().findFragmentById(R.id.crypto_list_fragment);
            list.onClick(position -> onListClick(position));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    public void onListClick(int position) {
        view = (CryptoViewFragment) getSupportFragmentManager().findFragmentById(R.id.crypto_view_fragment);

        if (view != null) {
            view.setData(data);
            view.updateCryptoView(position);
        } else {
            view = new CryptoViewFragment();
            view.setData(data);
            Bundle args = new Bundle();
            args.putInt(CryptoViewFragment.ARG_POSITION, position);
            view.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, view);
            transaction.commit();
        }
    }
}
