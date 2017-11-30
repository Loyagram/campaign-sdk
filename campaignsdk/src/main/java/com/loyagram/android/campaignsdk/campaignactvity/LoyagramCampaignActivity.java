package com.loyagram.android.campaignsdk.campaignactvity;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.loyagram.android.campaignsdk.globals.LoyagramCampaignSdk;
import com.loyagram.android.campaignsdk.campaignbase.LoyagramCampaignManager;
import com.loyagram.android.campaignsdk.campaigncallback.CampaignCallback;
import com.loyagram.android.campaignsdk.models.Campaign;
import com.loyagram.android.campaignsdk.restapi.ApiBase;
import com.loyagram.android.campaignsdk.ui.LoyagramCampaignView;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Loads campaign widget in full screen activity.
 */

public class LoyagramCampaignActivity extends Activity {
    LoyagramCampaignView loyagramCampaignView;
    Boolean repeatMode;
    Boolean previewMode;
    String campaignId;

    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        repeatMode = getIntent().getBooleanExtra("repeatMode", false);
        previewMode = getIntent().getBooleanExtra("previewMode", false);

        //If repeat mode or primary mode campaign primary color is used from json data or from apps primary color
        if (repeatMode || previewMode) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            loyagramCampaignView = new LoyagramCampaignView(this, true);
        } else {
            loyagramCampaignView = new LoyagramCampaignView(this);
        }
        campaignId = getIntent().getStringExtra("campaingId");
        String colorPrimary = getIntent().getStringExtra("colorPrimary");
        String token = getIntent().getStringExtra("token");
        String strLocationId = getIntent().getStringExtra("locationId");

        //set location id for in-store campaigns
        if (strLocationId != null) {
            BigDecimal locationId = new BigDecimal(strLocationId);
            loyagramCampaignView.setRepeatMode(repeatMode, locationId);
        } else {
            loyagramCampaignView.setRepeatMode(repeatMode, null);
        }
        loyagramCampaignView.setPreviewMode(previewMode);
        loyagramCampaignView.setCampaignType(0);
        //Sets token for authentication
        if (token != null) {
            loyagramCampaignView.setToken(token);
        }

        //Sets custom atributes to send to server with campaign response
        if (getIntent().getSerializableExtra("customAttributes") instanceof HashMap) {
            HashMap<String, String> hashMap = (HashMap<String, String>) getIntent().getSerializableExtra("customAttributes");
            loyagramCampaignView.setCustomAtributes(hashMap);
        }

        //Sets campaign call backs to notify once the campaign has finished
        String singletonCampaignId = LoyagramCampaignSdk.getInstance().getCampaignId();
        CampaignCallback campaignCallback = LoyagramCampaignSdk.getInstance().getCampaignCallback();
        if (campaignCallback != null && singletonCampaignId.equals(campaignId)) {
            loyagramCampaignView.setLoyagramCampaignListener(campaignCallback);
        }
        LinearLayout llcampaingContainer = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        llcampaingContainer.setLayoutParams(params);
        llcampaingContainer.setLayoutTransition(new LayoutTransition());
        llcampaingContainer.addView(loyagramCampaignView);

        if (colorPrimary != null && !colorPrimary.isEmpty()) {
            loyagramCampaignView.setColorPrimary(colorPrimary);
            loyagramCampaignView.setActivityBg(llcampaingContainer);
            loyagramCampaignView.setActivityBgColor();
        } else {
            loyagramCampaignView.setActivityBg(llcampaingContainer);
        }
        setContentView(llcampaingContainer);

        getCampaign();
    }

    /**
     * Method to get campaign. Checks if campaign is saved in preference, if found it will be returned else request from server
     */
    public void getCampaign() {

        if(!ApiBase.isEverythingOK(this)) {
            Campaign campaign = LoyagramCampaignManager.getCampaignFromPreference(campaignId, this);
            if(campaign != null) {
                loyagramCampaignView.setCampaign(campaign);
            } else {
                loyagramCampaignView.setCampaign(null);
                Toast.makeText(this, "No active internet connection.",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            loyagramCampaignView.showProgress();
            LoyagramCampaignManager.requestCampaignFromServer(this, campaignId, loyagramCampaignView);
        }

        //Gets campaign from preference, if not exits request from server.
        /*
        Campaign campaign = LoyagramCampaignManager.getCampaignFromPreference(campaignId, this);
        if (campaign == null) {
            if (!ApiBase.isEverythingOK(this)) {
                loyagramCampaignView.setCampaign(null);
                Toast.makeText(this, "No internet connection. Could not load campaign",
                        Toast.LENGTH_SHORT).show();
            } else {
                loyagramCampaignView.showProgress();
                LoyagramCampaignManager.requestCampaignFromServer(this, campaignId, loyagramCampaignView);
            }
        } else {
            loyagramCampaignView.setCampaign(campaign);
        } */
    }

    /**
     * Activity on destroy call back. resets campaign call back.
     */
    public void onDestroy() {
        super.onDestroy();
        LoyagramCampaignSdk.getInstance().resetSingleton();

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (previewMode || repeatMode) {
            if (!hasFocus) {
                // Close every kind of system dialog
                Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
                this.sendBroadcast(closeDialog);

                try {

                    String permission = "android.permission.REORDER_TASKS";
                    int res = this.checkCallingOrSelfPermission(permission);
                    if (res == PackageManager.PERMISSION_GRANTED) {
                        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                        am.moveTaskToFront(getTaskId(), ActivityManager.MOVE_TASK_WITH_HOME);

                    }
                } catch (Exception ignored) {

                }
            }

        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        if (previewMode || repeatMode) {
            return event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN || event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP || super.dispatchKeyEvent(event);
        } else {
            return super.dispatchKeyEvent(event);
        }
    }


}
