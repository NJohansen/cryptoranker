package com.example.cryptoranker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CryptoViewFragment extends Fragment {
    final static String ARG_POSITION = "position";
    int currentPosition = -1;
    private View view;
    private List<Data> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        // Inflate the layout for this fragment
        view = (View) inflater.inflate(R.layout.crypto_view, container, false);
        return view;
    }

    public void setData(List<Data> data) {
        this.data = data;

        if (currentPosition >= 0) {
            this.updateCryptoView(currentPosition);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateCryptoView(args.getInt(ARG_POSITION));
        } else if (currentPosition != -1) {
            // Set article based on saved instance state defined during onCreateView
            updateCryptoView(currentPosition);
        }
    }

    public void updateCryptoView(int position) {
        TextView label = (TextView) view.findViewById(R.id.label);
        TextView price = (TextView) view.findViewById(R.id.price_change);
        TextView description = (TextView) view.findViewById(R.id.description);
        CircleImageView image = (CircleImageView) view.findViewById(R.id.image);

        Glide.with(getContext()).asBitmap().load(data.get(position).getLogo()).into(image);

        label.setText(data.get(position).getName());
        price.setText("1 " + data.get(position).getSymbol()+ " = " + String.format("$%.2f", data.get(position).getQuote().getUsd().getPrice()));
        System.out.println(data.get(position).getDescription());
        description.setText(data.get(position).getDescription());
        currentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, currentPosition);
    }
}
