package com.loyagram.campaignsdkdemo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by user1 on 11/4/17.
 */

public class LoyagramApplication extends Application {
    @Override public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        // Normal app init code...
    }
}
