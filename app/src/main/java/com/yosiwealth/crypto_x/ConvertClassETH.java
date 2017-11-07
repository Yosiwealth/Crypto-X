package com.yosiwealth.crypto_x;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.text.DecimalFormat;

/**
 * Created by mo.yosiwealth on 10/31/2017.
 */

public class ConvertClassETH extends AppCompatActivity {

    FloatingActionButton fab;
    TextView aaa;
    TextView bbb;
    EditText cryptocoins;
    EditText countryCurrency;

    int currencies;
    int cryptos;

    Spinner spinner2;
    Spinner spinner1;

    RequestQueue requestQueue;

    String[] CURRENCYLIST = {"USD", "EUR", "NGN", "CAD", "GBP", "CNY", "CHF", "AUD", "JPY",
            "SEK", "MXN", "NZD", "SGD", "HKD", "NOK", "TRY", "RUB", "ZAR", "BRL", "MYR"};

    String[] CRYPTOLIST = {"ETH"};

    private static final String ETH_URL_DATA = "https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=USD,EUR,NGN,CAD," +
            "GBP,CNY,CHF,AUD,JPY,SEK,MXN,NZD,SGD,HKD,NOK,TRY,RUB,ZAR,BRL,MYR";

    Double exchangeRateUSD;
    Double exchangeRateEUR;
    Double exchangeRateNGN;
    Double exchangeRateCAD;
    Double exchangeRateGBP;
    Double exchangeRateCNY;
    Double exchangeRateCHF;
    Double exchangeRateAUD;
    Double exchangeRateJPY;
    Double exchangeRateSEK;
    Double exchangeRateMXN;
    Double exchangeRateNZD;
    Double exchangeRateSGD;
    Double exchangeRateHKD;
    Double exchangeRateNOK;
    Double exchangeRateTRY;
    Double exchangeRateRUB;
    Double exchangeRateZAR;
    Double exchangeRateBRL;
    Double exchangeRateMYR;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convert_activity_eth);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        aaa = (TextView) findViewById(R.id.aaa);
        bbb = (TextView) findViewById(R.id.bbb);
        cryptocoins = (EditText) findViewById(R.id.crypto);
        countryCurrency = (EditText) findViewById(R.id.currency);

        requestQueue = Volley.newRequestQueue(this);

        final DecimalFormat df2 = new DecimalFormat(".##");

        //set App title
        getSupportActionBar().setTitle("Convert ETH");

        loadBTCValue();

        ArrayAdapter<String> cryptoArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, CRYPTOLIST);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setAdapter(cryptoArrayAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String spinner1Value = spinner1.getSelectedItem().toString();
                aaa.setText(spinner1Value);

                Object crypto = adapterView.getItemAtPosition(i);
                switch (i) {
                    case 0:
                        cryptos = 1;
                        cryptocoins.setText("" + cryptos);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> currencyArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, CURRENCYLIST);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setAdapter(currencyArrayAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                String spinner2Value = spinner2.getSelectedItem().toString();
                bbb.setText(spinner2Value);

                Object currency = adapterView.getItemAtPosition(i);
                switch (i) {
                    case 0:
                        double value1 = Double.parseDouble(cryptocoins.getText().toString());
                        double result1 = exchangeRateUSD * value1;
                        countryCurrency.setText("" + df2.format(result1));
                        break;

                    case 1:
                        double value2 = Double.parseDouble(cryptocoins.getText().toString());
                        Double result2 = exchangeRateEUR * value2;
                        countryCurrency.setText("" + df2.format(result2));
                        break;

                    case 2:
                        double value3 = Double.parseDouble(cryptocoins.getText().toString());
                        Double result3 = value3 * exchangeRateNGN;
                        countryCurrency.setText("" + df2.format(result3));
                        break;

                    case 3:
                        double value4 = Double.parseDouble(cryptocoins.getText().toString());
                        Double result4 = value4 * exchangeRateCAD;
                        countryCurrency.setText("" + df2.format(result4));
                        break;

                    case 4:
                        double value5 = Double.parseDouble(cryptocoins.getText().toString());
                        Double result5 = value5 * exchangeRateGBP;
                        countryCurrency.setText("" + df2.format(result5));
                        break;

                    case 5:
                        double value6 = Double.parseDouble(cryptocoins.getText().toString());
                        Double result6 = value6 * exchangeRateCNY;
                        countryCurrency.setText("" + df2.format(result6));
                        break;

                    case 6:
                        double value7 = Double.parseDouble(cryptocoins.getText().toString());
                        Double result7 = value7 * exchangeRateCHF;
                        countryCurrency.setText("" + df2.format(result7));
                        break;

                    case 7:
                        double value8 = Double.parseDouble(cryptocoins.getText().toString());
                        Double result8 = value8 * exchangeRateAUD;
                        countryCurrency.setText("" + df2.format(result8));
                        break;

                    case 8:
                        double value9 = Double.parseDouble(cryptocoins.getText().toString());
                        Double result9 = value9 * exchangeRateJPY;
                        countryCurrency.setText("" + df2.format(result9));
                        break;

                    case 9:
                        double value10 = Double.parseDouble(cryptocoins.getText().toString());
                        Double result10 = value10 * exchangeRateSEK;
                        countryCurrency.setText("" + df2.format(result10));
                        break;

                    case 10:
                        double value11 = Double.parseDouble(cryptocoins.getText().toString());
                        Double result11 = value11 * exchangeRateMXN;
                        countryCurrency.setText("" + df2.format(result11));
                        break;

                    case 11:
                        double value12 = Double.parseDouble(cryptocoins.getText().toString());
                        Double result12 = value12 * exchangeRateNZD;
                        countryCurrency.setText("" + df2.format(result12));
                        break;

                    case 12:
                        double value13 = Double.parseDouble(cryptocoins.getText().toString());
                        Double result13 = value13 * exchangeRateSGD;
                        countryCurrency.setText("" + df2.format(result13));
                        break;

                    case 13:
                        double value14 = Double.parseDouble(cryptocoins.getText().toString());
                        Double result14 = value14 * exchangeRateUSD;
                        countryCurrency.setText("" + df2.format(result14));
                        break;

                    case 14:
                        double value15 = Double.parseDouble(cryptocoins.getText().toString());
                        Double result15 = value15 * exchangeRateNOK;
                        countryCurrency.setText("" + df2.format(result15));
                        break;

                    case 15:
                        double value16 = Double.parseDouble(cryptocoins.getText().toString());
                        Double result16 = value16 * exchangeRateTRY;
                        countryCurrency.setText("" + df2.format(result16));
                        break;

                    case 16:
                        double value17 = Double.parseDouble(cryptocoins.getText().toString());
                        Double result17 = value17 * exchangeRateRUB;
                        countryCurrency.setText("" + df2.format(result17));
                        break;

                    case 17:
                        double value18 = Double.parseDouble(cryptocoins.getText().toString());
                        Double result18 = value18 * exchangeRateZAR;
                        countryCurrency.setText("" + df2.format(result18));
                        break;

                    case 18:
                        double value19 = Double.parseDouble(cryptocoins.getText().toString());
                        Double result19 = value19 * exchangeRateBRL;
                        countryCurrency.setText("" + df2.format(result19));
                        break;

                    case 19:
                        double value20 = Double.parseDouble(cryptocoins.getText().toString());
                        Double result20 = value20 * exchangeRateMYR;
                        countryCurrency.setText("" + df2.format(result20));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void loadBTCValue() {

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, ETH_URL_DATA, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            exchangeRateUSD = response.getDouble("USD");
                            exchangeRateEUR = response.getDouble("EUR");
                            exchangeRateNGN = response.getDouble("NGN");
                            exchangeRateCAD = response.getDouble("CAD");
                            exchangeRateGBP = response.getDouble("GBP");
                            exchangeRateCNY = response.getDouble("CNY");
                            exchangeRateCHF = response.getDouble("CHF");
                            exchangeRateAUD = response.getDouble("AUD");
                            exchangeRateJPY = response.getDouble("JPY");
                            exchangeRateSEK = response.getDouble("SEK");
                            exchangeRateMXN = response.getDouble("MXN");
                            exchangeRateNZD = response.getDouble("NZD");
                            exchangeRateSGD = response.getDouble("SGD");
                            exchangeRateHKD = response.getDouble("HKD");
                            exchangeRateNOK = response.getDouble("NOK");
                            exchangeRateTRY = response.getDouble("TRY");
                            exchangeRateRUB = response.getDouble("RUB");
                            exchangeRateZAR = response.getDouble("ZAR");
                            exchangeRateBRL = response.getDouble("BRL");
                            exchangeRateMYR = response.getDouble("MYR");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error connecting to the internet ", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jor);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
