package com.omnianobis.rickmorty.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class Utils {

    private Utils() {
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
