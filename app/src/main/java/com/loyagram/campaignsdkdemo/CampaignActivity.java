package com.loyagram.campaignsdkdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.loyagram.android.campaignsdk.campaigncallback.CampaignCallback;
import com.loyagram.android.campaignsdk.ui.LoyagramCampaignView;


/**
 * Created by user1 on 5/4/17.
 */

public class CampaignActivity extends Activity {

    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campaignlayout);
        LoyagramCampaignView loyagramCampaignView = findViewById(R.id.loaygramCampaignView);
        loyagramCampaignView.setLoyagramCampaignListener(new CampaignCallback() {
            @Override
            public void onSuccess() {
                Log.i("Campaign", "Success");
            }

            @Override
            public void onError() {
                Log.i("Campaign", "Failed");
            }
        });
        btnExit = (Button) findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CampaignActivity.this.finish();
            }
        });
    }

}

