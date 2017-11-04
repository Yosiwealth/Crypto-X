package com.yosiwealth.crypto_x;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

/**
 * Created by mo.yosiwealth on 10/31/2017.
 */

public class BtcTab extends Fragment {

    Button convertBTC;
    TextView btcExchange;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle args) {

        View view = inflater.inflate(R.layout.btc_tab, container, false);

        btcExchange = (TextView) view.findViewById(R.id.exchangeRateBtc);

        convertBTC = (Button) view.findViewById(R.id.convertBTC);
        convertBTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ConvertClassBTC.class);
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
    public void recievedData(BtcModelClass btcData) {
        btcExchange.setText(btcData.getUSD());
    }

}
