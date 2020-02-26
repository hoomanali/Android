package com.example.cse118asg02.images;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.URL;

/**
 * Helper class to modularize utilities for dealing with images.
 */
public final class Utils {

    /**
     * Block instantiation with private constructor
     */
    private Utils() {
        throw new UnsupportedOperationException();
    }

    /**
     * Ensure a valid URL string is entered for image download.
     * @param url The string representing the URL to be downloaded.
     * @return True if URL is valid.
     */
    public static Boolean UrlValid(String url) {
        if(url == null || url.equals("")) {
            return false;
        }
        try {
            URL u = new URL(url);
        } catch(Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Check if network connection is available.
     * @param context Current activity context.
     * @return True if network is connected.
     */
    public static Boolean NetworkConnected(Context context) {
        ConnectivityManager cm;
        cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if(activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }

        return false;
    }

}
