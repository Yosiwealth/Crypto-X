package com.yosiwealth.crypto_x;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

/**
 * Created by mo.yosiwealth on 10/31/2017.
 */

public class EthTab extends Fragment {
    FloatingActionButton convertETH;
    TextView ethExchange;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle args) {

        View view = inflater.inflate(R.layout.eth_tab, container, false);

        ethExchange = (TextView) view.findViewById(R.id.exchangeRateEth);

        convertETH = (FloatingActionButton) view.findViewById(R.id.convertETH);
        convertETH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ConvertClassETH.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        BusStation.getBus().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusStation.getBus().unregister(this);
    }

    @Subscribe
    public void recievedData(EthModelClass ethData) {
        ethExchange.setText(ethData.getUSD());
    }

}
