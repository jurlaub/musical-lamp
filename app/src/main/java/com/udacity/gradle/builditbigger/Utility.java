package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by dev on 3/25/16.
 */
public class Utility {

    private final String LOG_TAG = Utility.class.getSimpleName();


    // determines if network connection is available
    // based on feedback from code review and Android Dev page: "Check the Network Connection"
    public static boolean networkIsAvailable(Context context) {

        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }

        return false;

    }


}
