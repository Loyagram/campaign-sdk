package com.loyagram.campaignsdkdemo;

import android.app.Application;

import com.loyagram.android.campaignsdk.globals.LoyagramCampaignSdk;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by user1 on 11/4/17.
 */

public class LoyagramApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String apiKey = "faa733e7-a9b0-453d-8a77-aa881909bbb0";
        String accessSecret = "3dc8b3f2-141e-44de-babb-f0eadb9b6359";
        LoyagramCampaignSdk.init(apiKey, accessSecret);
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        // Normal app init code...
    }
}
