package com.loyagram.android.campaignsdk.restapi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.loyagram.android.campaignsdk.globals.LoyagramCampaignSdk;
import com.loyagram.android.campaignsdk.models.ErrorModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for api request. Contains methods for checking internet connectivity and method for base url.
 */
public class ApiBase {
    //    public static final String HOST = "http://192.168.1.144:5000";
    public static final String HOST = "https://loyagram.com";
    //    public static final String HOST = "http://geocastapp.com:3000";
    public static final String AUTHENTICATED_PATH = "/api/v0.0.1beta";

    /**
     * Perform and internet conncectivity check.
     *
     * @param context Application contect
     * @return retuns true if there is an active network otherwise false.
     */
    public static boolean checkInternet(Context context) {
        final ConnectivityManager conMgr = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnected();
        } catch (Exception ex) {
            return false;
        }


    }


//    public static void noInternet(Context context) {
//        ErrorModel em = new ErrorModel();
//        em.setMsg("Seems like you are not connected to internet");
//        em.setField("nw");
//        em.setType("NETWORK");
//        List<ErrorModel> list = new ArrayList();
//        list.add(em);
//        Toast.makeText(context, "No internet.",
//                Toast.LENGTH_SHORT).show();
//    }

    /**
     * Returns the base url
     *
     * @return base url
     */
    public static String getApiPath() {

        String domainType = LoyagramCampaignSdk.getInstance().getDomainType();

        if (domainType != null && domainType.equals("ex")) {
            return "https://ex.loyagram.com";
        }
        return HOST;
    }

//    public static String getBasePath() {
//        return HOST;
//    }

    /**
     * Checks network status
     *
     * @param context Applciation context
     * @return returns true if network seems good otherwise false
     */
    public static boolean isEverythingOK(Context context) {
        return checkInternet(context);
    }

}
