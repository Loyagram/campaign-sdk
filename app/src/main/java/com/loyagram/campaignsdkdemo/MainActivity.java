package com.loyagram.campaignsdkdemo;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.loyagram.android.campaignsdk.campaignbase.LoyagramCampaignManager;
import com.loyagram.android.campaignsdk.campaigncallback.CampaignCallback;
import com.loyagram.android.campaignsdk.globals.LoyagramCampaignSdk;

import java.math.BigDecimal;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    LinearLayout llmaincontianer = null;
    Button btnNewActivity = null;
    Button btnDialog = null;
    Button btnShowFromBottom = null;
    Button btnshowFromXml = null;
    Button btnPreview = null;
    RadioGroup radioGroup = null;
    RadioButton rdbNps = null;
    RadioButton rdbSurvey = null;
    String logo = "logo.png";
    String campaignId = "1020-49bd1a50-1c51-445d-8043-fa8f907a0078";
    String colorPrimary = "#1abc9c";
    HashMap<String, String> customAttributes = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            getSupportActionBar().hide();
        } catch (Exception ignored) {

        }
        setCustomAttibutes();
        init();
    }

    public void init() {


        llmaincontianer = (LinearLayout) findViewById(R.id.mainContianer);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rdbNps = (RadioButton) findViewById(R.id.rdbNps);
        rdbSurvey = (RadioButton) findViewById(R.id.rdbSurvey);
        btnNewActivity = (Button) findViewById(R.id.btnActivity);
        btnDialog = (Button) findViewById(R.id.btnDialog);
        btnShowFromBottom = (Button) findViewById(R.id.btnFromBottom);
        btnshowFromXml = (Button) findViewById(R.id.fromXml);
        btnPreview = (Button) findViewById(R.id.btnPreview);
        btnNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // LoyagramCampaignManager.showAsActivity(MainActivity.this, campaignId, colorPrimary, true, "24078c9e-c811-4ba3-8671-16708d148716");
              /*  LoyagramCampaignManager.showAsActivity(MainActivity.this, campaignId, theme, new CampaignCallback() {
                    @Override
                    public void onSuccess() {
                        Log.i("Campaign", "Success");
                    }

                    @Override
                    public void onError() {
                        Log.i("Campaign", "Failed");
                    }
                });
                */

                LoyagramCampaignManager.showAsActivity(MainActivity.this, campaignId, null, null);
                //LoyagramCampaignManager.showAsRepeatMode(MainActivity.this, campaignId, null, "13bcae64-14b9-4f3d-81ea-a1bb1acf69b2", new BigDecimal(10));

            }
        });

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoyagramCampaignManager.showAsDialog(MainActivity.this, campaignId, null, customAttributes, new CampaignCallback() {

                    @Override
                    public void onSuccess() {
                        Log.i("Campaign", "Success");
                    }

                    @Override
                    public void onError() {
                        Log.i("Campaign", "Failed");
                    }
                });
            }
        });

        btnShowFromBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LoyagramCampaignManager.addAttribute("userId", "123456");
                //LoyagramCampaignManager.addAttribute("serviceId", "12345");
                LinearLayout widgetContainer = (LinearLayout) findViewById(R.id.campaignContainer);
                LoyagramCampaignManager.showFromBottom(MainActivity.this, campaignId, null, widgetContainer, btnShowFromBottom, customAttributes, new CampaignCallback() {
                    @Override
                    public void onSuccess() {
                        Log.i("Campaign", "Success");
                    }

                    @Override
                    public void onError() {
                        Log.i("Campaign", "Failed");
                    }
                });
            }
        });

        btnshowFromXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LoyagramCampaignManager.addAttribute("userId", "123456");
                LoyagramCampaignManager.addAttribute("productId", "1234");
                //LoyagramCampaignManager.addAttributes(customAttributes);
                Intent intent = new Intent(MainActivity.this, CampaignActivity.class);
                MainActivity.this.startActivity(intent);

            }
        });

        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoyagramCampaignManager.showAsPreview(MainActivity.this, campaignId, null, null);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                if (checkedId == R.id.rdbNps) {
                    campaignId = "1020-49bd1a50-1c51-445d-8043-fa8f907a0078";
                } else if (checkedId == R.id.rdbCsat) {
                    campaignId = "1020-2d06b020-aeb6-472b-8321-556f3d4ec510";
                } else if (checkedId == R.id.rdbSurvey) {
                    campaignId = "1020-80c64203-5484-4a52-b41d-5ef485cc80f1";
                } else if(checkedId == R.id.rdbCes) {
                    campaignId = "1020-193be5eb-870d-4d27-971a-268cff4add88";
                }
            }
        });
    }

    public HashMap setCustomAttibutes() {
        customAttributes.put("userId", "123456");
        customAttributes.put("productId", "1234");
        return customAttributes;
    }

    public void onDestroy() {
        super.onDestroy();
        //Fix for InputMethodManager memory leaks
        fixInputMethodManager();
    }

    // code courtesy http://stackoverflow.com/questions/5038158/main-activity-is-not-garbage-collected-after-destruction-because-it-is-reference
    private void fixInputMethodManager() {
        final Object imm = getSystemService(Context.INPUT_METHOD_SERVICE);
        final Reflector.TypedObject windowToken
                = new Reflector.TypedObject(getWindow().getDecorView().getWindowToken(), IBinder.class);

        Reflector.invokeMethodExceptionSafe(imm, "windowDismissed", windowToken);

        final Reflector.TypedObject view
                = new Reflector.TypedObject(null, View.class);

        Reflector.invokeMethodExceptionSafe(imm, "startGettingWindowFocus", view);
    }
}
