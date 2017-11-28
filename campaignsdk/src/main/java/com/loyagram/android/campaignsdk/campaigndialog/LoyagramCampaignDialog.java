package com.loyagram.android.campaignsdk.campaigndialog;


import android.app.Dialog;
import android.app.DialogFragment;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.loyagram.android.campaignsdk.R;
import com.loyagram.android.campaignsdk.campaignbase.LoyagramCampaignManager;
import com.loyagram.android.campaignsdk.campaigncallback.CampaignCallback;
import com.loyagram.android.campaignsdk.globals.LoyagramCampaignSdk;
import com.loyagram.android.campaignsdk.models.Campaign;
import com.loyagram.android.campaignsdk.restapi.ApiBase;
import com.loyagram.android.campaignsdk.ui.LoyagramCampaignView;

import java.util.HashMap;

/**
 * Custom dialog to show campaign as a dialog.
 */

public class LoyagramCampaignDialog extends DialogFragment {

    String campaignId;
    String colorPrimary;
    HashMap<String, String> customAttr;


    public void init(String campaignId, String colorPrimary, HashMap<String, String> customAttr) {
        this.campaignId = campaignId;
        this.colorPrimary = colorPrimary;
        this.customAttr = customAttr;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.dialog);
        this.setCancelable(false);
        if (savedInstanceState != null) {
            campaignId = savedInstanceState.getString("campaignId");
            colorPrimary = savedInstanceState.getString("colorPrimary");
            customAttr = (HashMap<String, String>) savedInstanceState.getSerializable("customAttr");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LoyagramCampaignView loyagramCampaignView = new LoyagramCampaignView(getActivity());
        if (colorPrimary != null && !colorPrimary.isEmpty()) {
            loyagramCampaignView.setColorPrimary(colorPrimary);
        }

        if (customAttr != null && !customAttr.isEmpty()) {
            loyagramCampaignView.setCustomAtributes(customAttr);
        }

        //Sets campaign call backs
        String singletonCampaignId = LoyagramCampaignSdk.getInstance().getCampaignId();
        CampaignCallback campaignCallback = LoyagramCampaignSdk.getInstance().getCampaignCallback();
        if (campaignCallback != null && singletonCampaignId.equals(campaignId)) {
            loyagramCampaignView.setLoyagramCampaignListener(campaignCallback);
        }


        if(!ApiBase.isEverythingOK(getActivity())) {
            Campaign campaign = LoyagramCampaignManager.getCampaignFromPreference(campaignId, getActivity());
            if(campaign != null) {
                loyagramCampaignView.setCampaign(campaign);
            } else {
                loyagramCampaignView.setCampaign(null);
                Toast.makeText(getActivity(), "No active internet connection.",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            loyagramCampaignView.showProgress();
            LoyagramCampaignManager.requestCampaignFromServer(getActivity(), campaignId, loyagramCampaignView);
        }


        /*Campaign campaign = LoyagramCampaignManager.getCampaignFromPreference(campaignId, getActivity());
        if (campaign == null) {
            if (!ApiBase.isEverythingOK(getActivity())) {
                loyagramCampaignView.setCampaign(null);
                Toast.makeText(getActivity(), "No internet connection. Could not load campaign",
                        Toast.LENGTH_SHORT).show();
            } else {
                loyagramCampaignView.showProgress();
                LoyagramCampaignManager.requestCampaignFromServer(getActivity(), campaignId, loyagramCampaignView);
            }
        } else {
            loyagramCampaignView.setCampaign(campaign);
        } */
        loyagramCampaignView.setCampaignType(1);
        loyagramCampaignView.setCampaignDialog(this);
        return loyagramCampaignView;
    }

    @Override
    public @NonNull Dialog  onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity(), R.style.dialog);
        dialog.show();
        try {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        } catch (Exception ignored) {

        }
        Window window = dialog.getWindow();
        int pixels;
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            pixels = getResources().getDimensionPixelSize(R.dimen.dialog_widget_height_large);
        } else {
            pixels = getResources().getDimensionPixelSize(R.dimen.dialog_widget_height_landscape);
        }

        //int pixels = getResources().getDimensionPixelSize(R.dimen.dialog_widget_height);
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, pixels);
        return dialog;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("campaignId", campaignId);
        outState.putString("colorPrimary", colorPrimary);
        outState.putSerializable("customAttr", customAttr);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LoyagramCampaignSdk.getInstance().resetSingleton();

    }
}
