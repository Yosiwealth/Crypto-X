package com.yosiwealth.crypto_x;

import com.squareup.otto.Bus;

/**
 * Created by mo.yosiwealth on 10/31/2017.
 */

public class BusStation {

    private static Bus bus = new Bus();

    public static Bus getBus() {
        return bus;
    }
}