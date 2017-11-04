package com.yosiwealth.crypto_x;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String BTC_URL_DATA = "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=USD,EUR,NGN,CAD," +
            "GBP,CNY,CHF,AUD,JPY,SEK,MXN,NZD,SGD,HKD,NOK,TRY,RUB,ZAR,BRL,MYR";
    private static final String ETH_URL_DATA = "https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=USD,EUR,NGN,CAD," +
            "GBP,CNY,CHF,AUD,JPY,SEK,MXN,NZD,SGD,HKD,NOK,TRY,RUB,ZAR,BRL,MYR";

    RequestQueue requestQueue;
    TextView btcExchange;
    SwipeRefreshLayout mSwipeRefreshLayout;


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        //set App title
//        getSupportActionBar().setTitle("");

        //remove app title
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        requestQueue = Volley.newRequestQueue(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

//        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
////                loadBTCValue();
////                loadETHValue();
//                Toast.makeText(getApplicationContext(), "I'm just tired", Toast.LENGTH_LONG).show();
//
//            }
//        });

        loadBTCValue();
        loadETHValue();

    }

    private void loadBTCValue() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Retrieving rates...");
        progressDialog.show();


        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, BTC_URL_DATA, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();

                        try {

                            Double exchangeRateUSD = response.getDouble("USD");
                            Double exchangeRateEUR = response.getDouble("EUR");
                            Double exchangeRateNGN = response.getDouble("NGN");
                            Double exchangeRateCAD = response.getDouble("CAD");
                            Double exchangeRateGBP = response.getDouble("GBP");
                            Double exchangeRateCNY = response.getDouble("CNY");
                            Double exchangeRateCHF = response.getDouble("CHF");
                            Double exchangeRateAUD = response.getDouble("AUD");
                            Double exchangeRateJPY = response.getDouble("JPY");
                            Double exchangeRateSEK = response.getDouble("SEK");
                            Double exchangeRateMXN = response.getDouble("MXN");
                            Double exchangeRateNZD = response.getDouble("NZD");
                            Double exchangeRateSGD = response.getDouble("SGD");
                            Double exchangeRateHKD = response.getDouble("HKD");
                            Double exchangeRateNOK = response.getDouble("NOK");
                            Double exchangeRateTRY = response.getDouble("TRY");
                            Double exchangeRateRUB = response.getDouble("RUB");
                            Double exchangeRateZAR = response.getDouble("ZAR");
                            Double exchangeRateBRL = response.getDouble("BRL");
                            Double exchangeRateMYR = response.getDouble("MYR");


                            BusStation.getBus().post(new BtcModelClass(
                                    exchangeRateUSD.toString(), exchangeRateEUR.toString(), exchangeRateNGN.toString(),
                                    exchangeRateCAD.toString(), exchangeRateGBP.toString(), exchangeRateCNY.toString(),
                                    exchangeRateCHF.toString(), exchangeRateAUD.toString(), exchangeRateJPY.toString(),
                                    exchangeRateSEK.toString(), exchangeRateMXN.toString(), exchangeRateNZD.toString(),
                                    exchangeRateSGD.toString(), exchangeRateHKD.toString(), exchangeRateNOK.toString(),
                                    exchangeRateTRY.toString(), exchangeRateRUB.toString(), exchangeRateZAR.toString(),
                                    exchangeRateBRL.toString(), exchangeRateMYR.toString()
                            ));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error connecting to the internet ", Toast.LENGTH_LONG).show();
//                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        requestQueue.add(jor);

    }

    private void loadETHValue() {

        final ProgressDialog progressDialog = new ProgressDialog(this);

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, ETH_URL_DATA, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Double exchangeRateUSD = response.getDouble("USD");
                            Double exchangeRateEUR = response.getDouble("EUR");
                            Double exchangeRateNGN = response.getDouble("NGN");
                            Double exchangeRateCAD = response.getDouble("CAD");
                            Double exchangeRateGBP = response.getDouble("GBP");
                            Double exchangeRateCNY = response.getDouble("CNY");
                            Double exchangeRateCHF = response.getDouble("CHF");
                            Double exchangeRateAUD = response.getDouble("AUD");
                            Double exchangeRateJPY = response.getDouble("JPY");
                            Double exchangeRateSEK = response.getDouble("SEK");
                            Double exchangeRateMXN = response.getDouble("MXN");
                            Double exchangeRateNZD = response.getDouble("NZD");
                            Double exchangeRateSGD = response.getDouble("SGD");
                            Double exchangeRateHKD = response.getDouble("HKD");
                            Double exchangeRateNOK = response.getDouble("NOK");
                            Double exchangeRateTRY = response.getDouble("TRY");
                            Double exchangeRateRUB = response.getDouble("RUB");
                            Double exchangeRateZAR = response.getDouble("ZAR");
                            Double exchangeRateBRL = response.getDouble("BRL");
                            Double exchangeRateMYR = response.getDouble("MYR");


                            BusStation.getBus().post(new EthModelClass(
                                    exchangeRateUSD.toString(), exchangeRateEUR.toString(), exchangeRateNGN.toString(),
                                    exchangeRateCAD.toString(), exchangeRateGBP.toString(), exchangeRateCNY.toString(),
                                    exchangeRateCHF.toString(), exchangeRateAUD.toString(), exchangeRateJPY.toString(),
                                    exchangeRateSEK.toString(), exchangeRateMXN.toString(), exchangeRateNZD.toString(),
                                    exchangeRateSGD.toString(), exchangeRateHKD.toString(), exchangeRateNOK.toString(),
                                    exchangeRateTRY.toString(), exchangeRateRUB.toString(), exchangeRateZAR.toString(),
                                    exchangeRateBRL.toString(), exchangeRateMYR.toString()
                            ));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
//                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        requestQueue.add(jor);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    BtcTab tab1 = new BtcTab();
                    return tab1;
                case 1:
                    EthTab tab2 = new EthTab();
                    return tab2;
//                case 2:
//                    tbcTab tab3 = new tbcTab();
//                    return tab3;
                default:
                    return null;
            }


        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {


            switch (position) {
                case 0:
                    return " BTC";
                case 1:
                    return " ETH";
//                case 2:
//                    return "TBC";
            }
            return null;
        }
    }
}

