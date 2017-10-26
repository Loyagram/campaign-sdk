package com.loyagram.android.campaignsdk.campaignbase;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loyagram.android.campaignsdk.campaigndialog.LoyagramCampaignDialog;
import com.loyagram.android.campaignsdk.globals.LoyagramAttributes;
import com.loyagram.android.campaignsdk.globals.LoyagramCampaignSdk;
import com.loyagram.android.campaignsdk.R;
import com.loyagram.android.campaignsdk.asynctask.AsyncTaskSendResponse;
import com.loyagram.android.campaignsdk.campaignactvity.LoyagramCampaignActivity;
import com.loyagram.android.campaignsdk.animation.LoyagramAnimate;
import com.loyagram.android.campaignsdk.asynctask.AsyncTasKRequestQuestion;
import com.loyagram.android.campaignsdk.campaigncallback.CampaignCallback;
import com.loyagram.android.campaignsdk.models.Campaign;
import com.loyagram.android.campaignsdk.models.ErrorModel;
import com.loyagram.android.campaignsdk.models.Response;
import com.loyagram.android.campaignsdk.restapi.APIResultCallback;
import com.loyagram.android.campaignsdk.restapi.ApiBase;
import com.loyagram.android.campaignsdk.ui.LoyagramCampaignView;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Act as a Base class for the campaign. Contains Methods for loading the widgets in different styles.
 */


public class LoyagramCampaignManager {


    /**
     * Method to display the campaign view as new activity
     * Methods initially checks for an active internet connection. if there is no internet connectivity it exits.
     *
     * @param context          Appliation context.
     * @param campaignId       campaignId to to request campaign from server
     * @param colorPrimary     Custom theme includes color as well as logo
     * @param campaignCallback either success or error call back for campaign submit
     */

    public static void showAsActivity(Context context, String campaignId, String colorPrimary, HashMap<String, String> customAttr, CampaignCallback campaignCallback) {
        Intent intent = new Intent(context, LoyagramCampaignActivity.class);
        intent.putExtra("colorPrimary", colorPrimary);
        intent.putExtra("campaingId", campaignId);
        if (customAttr != null && !customAttr.isEmpty()) {
            intent.putExtra("customAttributes", customAttr);
        }
        LoyagramCampaignSdk.getInstance().setCampaignCallback(campaignCallback);
        LoyagramCampaignSdk.getInstance().setCampaignId(campaignId);
        context.startActivity(intent);
    }

    /**
     * Method to display the campaign view as new activity
     *
     * @param context      context
     * @param campaignId   campaign Id
     * @param colorPrimary color theme
     */
    public static void showAsActivity(Context context, String campaignId, String colorPrimary, HashMap<String, String> customAttr) {
        Intent intent = new Intent(context, LoyagramCampaignActivity.class);
        intent.putExtra("colorPrimary", colorPrimary);
        intent.putExtra("campaingId", campaignId);
        if (customAttr != null && !customAttr.isEmpty()) {
            intent.putExtra("customAttributes", customAttr);
        }
        context.startActivity(intent);
    }

    /**
     * Method to display the campaign view as new activity.
     * used for In-Store app. Repeats the same campaign infinitely.
     *
     * @param context      context
     * @param campaignId   campaign Id
     * @param colorPrimary color theme
     * @param token        token used for authentication on backpress
     */
    public static void showAsRepeatMode(Context context, String campaignId, String colorPrimary, String token, BigDecimal locationId, String domainType) {

        if (domainType != null) {
            LoyagramCampaignSdk.getInstance().setDomainType(domainType);
        }
        Intent intent = new Intent(context, LoyagramCampaignActivity.class);
        intent.putExtra("colorPrimary", colorPrimary);
        intent.putExtra("campaingId", campaignId);
        intent.putExtra("repeatMode", true);
        intent.putExtra("token", token);
        intent.putExtra("locationId", locationId.toString());
        context.startActivity(intent);
        if (ApiBase.isEverythingOK(context)) {
            sendPendingResponses(context);
        }
    }

    /**
     * Shows campaign as a preview. No response will be saved and send to server.
     * Used for In-store app.
     *
     * @param context      context
     * @param campaignId   campaign Id
     * @param colorPrimary color theme
     */
    public static void showAsPreview(Context context, String campaignId, String colorPrimary, String domainType) {

        if (domainType != null) {
            LoyagramCampaignSdk.getInstance().setDomainType(domainType);
        }
        Intent intent = new Intent(context, LoyagramCampaignActivity.class);
        intent.putExtra("colorPrimary", colorPrimary);
        intent.putExtra("campaingId", campaignId);
        intent.putExtra("previewMode", true);
        context.startActivity(intent);
    }

    /**
     * Loads campaign as a Dialog Fragment.
     * Sets success and error call backs.
     *
     * @param context          context of the application
     * @param campaignId       campaignId to request campaign from server
     * @param colorPrimary     custom theme includes color as well as logo
     * @param campaignCallback either success or error call back for campaign submit
     */
    public static void showAsDialog(Context context, String campaignId, String colorPrimary, HashMap<String, String> customAttr, CampaignCallback campaignCallback) {

        final Activity activity = (Activity) context;
        FragmentManager fragmentManager = activity.getFragmentManager();
        LoyagramCampaignDialog dialog = new LoyagramCampaignDialog();
        dialog.init(campaignId, colorPrimary, customAttr);
        dialog.show(fragmentManager, "Dialog");
        LoyagramCampaignSdk.getInstance().setCampaignCallback(campaignCallback);
        LoyagramCampaignSdk.getInstance().setCampaignId(campaignId);

    }

    /**
     * Loads campaign as a dialog.
     *
     * @param context      context
     * @param campaignId   campaign id
     * @param colorPrimary color theme for widget
     */
    public static void showAsDialog(Context context, String campaignId, String colorPrimary, HashMap<String, String> customAttr) {
        final Activity activity = (Activity) context;
        FragmentManager fragmentManager = activity.getFragmentManager();
        LoyagramCampaignDialog dialog = new LoyagramCampaignDialog();
        dialog.init(campaignId, colorPrimary, customAttr);
        dialog.show(fragmentManager, "Dialog");

    }

    /**
     * Loads campaign from bottom with slide up animation. Hides the campaign launch button once campaign is loaded.
     *
     * @param context          context of the application
     * @param campaignId       campaignId to request campaign from server
     * @param colorPrimary     custom theme includes color as well as logo
     * @param widgetContainer  container view to place the campaign view
     * @param button           button
     * @param campaignCallback either success or error call back for campaign submit
     */

    public static void showFromBottom(Context context, String campaignId, String colorPrimary, ViewGroup widgetContainer, View button, HashMap<String, String> customAttr, CampaignCallback campaignCallback) {
        LoyagramCampaignView loyagramCampaignView = new LoyagramCampaignView(context);
        loyagramCampaignView.setLoyagramCampaingListener(campaignCallback);

        //Sets campaign color if the color argument is not empty
        if (colorPrimary != null && !colorPrimary.isEmpty()) {
            loyagramCampaignView.setColorPrimary(colorPrimary);
        }
        //Sets custom attributes if not empty
        if (customAttr != null && !customAttr.isEmpty()) {
            loyagramCampaignView.setCustomAtributes(customAttr);
        }

        if (!ApiBase.isEverythingOK(context)) {
            Campaign campaign = LoyagramCampaignManager.getCampaignFromPreference(campaignId, context);
            if (campaign != null) {
                loyagramCampaignView.setCampaign(campaign);
            } else {
                loyagramCampaignView.setCampaign(null);
                Toast.makeText(context, "No active internet connection.",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            loyagramCampaignView.showProgress();
            LoyagramCampaignManager.requestCampaignFromServer(context, campaignId, loyagramCampaignView);
        }

        //Sets campaign type. 2 for show from bottom.
        loyagramCampaignView.setCampaignType(2);
        LoyagramAnimate.animate(widgetContainer, button, loyagramCampaignView);
    }

    /**
     * Loads campaign from bottom with slide up animation. Hides the campaign launch button once campaign is loaded.
     *
     * @param context         context
     * @param campignId       campaign id
     * @param colorPrimary    campaign color
     * @param widgetContainer campaign container view
     * @param button          campaign launch button
     */
    public static void showFromBottom(Context context, String campignId, String colorPrimary, ViewGroup widgetContainer, View button, HashMap<String, String> customAttr) {
        LoyagramCampaignView loyagramCampaignView = new LoyagramCampaignView(context);

        //Sets campaign color if the color argument is not empty
        if (colorPrimary != null && !colorPrimary.isEmpty()) {
            loyagramCampaignView.setColorPrimary(colorPrimary);
        }
        //Sets custom attributes if not empty
        if (customAttr != null && !customAttr.isEmpty()) {
            loyagramCampaignView.setCustomAtributes(customAttr);
        }

        if (!ApiBase.isEverythingOK(context)) {
            Campaign campaign = LoyagramCampaignManager.getCampaignFromPreference(campignId, context);
            if (campaign != null) {
                loyagramCampaignView.setCampaign(campaign);
            } else {
                loyagramCampaignView.setCampaign(null);
                Toast.makeText(context, "No active internet connection.",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            loyagramCampaignView.showProgress();
            LoyagramCampaignManager.requestCampaignFromServer(context, campignId, loyagramCampaignView);
        }

        /*
        Campaign campaign = getCampaignFromPreference(campignId, context);
        if (campaign == null) {
            if (!ApiBase.isEverythingOK(context)) {
                loyagramCampaignView.setCampaign(null);
                Toast.makeText(context, "No internet connection. Could not load campaign",
                        Toast.LENGTH_SHORT).show();
            } else {
                loyagramCampaignView.showProgress();
                requestCampaignFromServer(context, campignId, loyagramCampaignView);
            }
        } else {
            loyagramCampaignView.setCampaign(campaign);
        } */

        //Sets campaign type. 2 for show from bottom.
        loyagramCampaignView.setCampaignType(2);
        LoyagramAnimate.animate(widgetContainer, button, loyagramCampaignView);
    }

    /**
     * send campaign request to server if no campaign found in preference
     *
     * @param context              context
     * @param campaignId           campagn id
     * @param loyagramCampaignView Campaign widget's object
     */
    public static void requestCampaignFromServer(final Context context, final String campaignId, final LoyagramCampaignView loyagramCampaignView) {
        AsyncTasKRequestQuestion asyncTasKRequestQuestion = new AsyncTasKRequestQuestion(context.getApplicationContext());
        asyncTasKRequestQuestion.setListener(new APIResultCallback<Campaign>() {
            /**
             * Successfull receives campaign
             * @param campaign current campaign
             */
            @Override
            public void onSuccess(Campaign campaign) {

                /* server side implementation pending
                Campaign savedCampaign = getCampaignFromPreference(campaignId, context);
                if (savedCampaign != null && savedCampaign.getUpdatedDate() != 0) {
                    if (savedCampaign.getUpdatedDate() < campaign.getUpdatedDate()) {
                        removeCampaignFromPreference(campaignId, context);
                        saveCampaignToPreference(campaign, context);
                    }
                } else {
                    saveCampaignToPreference(campaign, context);
                }
                */
                removeCampaignFromPreference(campaignId, context);
                saveCampaignToPreference(campaign, context);
                loyagramCampaignView.setCampaign(campaign);
            }

            /**
             * Campaign Api error call backs
             * @param errors error
             */
            @Override
            public void onError(List<ErrorModel> errors) {
                loyagramCampaignView.setCampaign(null);
            }
        });
        asyncTasKRequestQuestion.execute(campaignId);
    }

    /**
     * Gets campaign from the saved campaign list in the preference.
     *
     * @param campaignId campaign id for searching
     * @param context    context
     * @return campaign campaign
     */
    public static Campaign getCampaignFromPreference(String campaignId, Context context) {

        String prefName = getAppname(context) + "campaign";
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        Campaign currentCampaign;
        String json = sharedPreferences.getString(prefName, null);
        if (json == null) {
            return null;
        }

        //Retrieve campaign from more than one campaigns.
        try {
            Campaign[] campaigns = gson.fromJson(json, Campaign[].class);
            for (Campaign campaign : campaigns) {
                String savedCampaignId = campaign.getStringId();
                if (savedCampaignId.equals(campaignId)) {
                    currentCampaign = campaign;
                    return currentCampaign;
                }
            }
        } catch (Exception ignored) {

        }
        //Retrieve if the campaign_Id matches the only campaign in preference.
        try {
            Campaign campaign = gson.fromJson(json, Campaign.class);
            if (campaignId.equals(campaign.getStringId())) {
                currentCampaign = campaign;
                return currentCampaign;
            }

        } catch (Exception ignored) {

        }


        return null;
    }


    public static void saveCampaignToPreference(Campaign campaign, Context context) {
        Gson gson = new Gson();
        String appName = getAppname(context) + "campaign";
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String campaigns = sharedPreferences.getString(appName, null);

        ArrayList<Campaign> arrayList = new ArrayList<>();
        if (campaigns == null) {
            arrayList.add(campaign);
        } else {
            Type type = new TypeToken<ArrayList<Campaign>>() {
            }.getType();
            arrayList = gson.fromJson(campaigns, type);
            arrayList.add(campaign);
        }

        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        String stringCampaign = gson.toJson(arrayList);
        prefsEditor.putString(appName, stringCampaign);
        prefsEditor.apply();
    }

    public static void removeCampaignFromPreference(String campaignId, Context context) {
        String prefName = getAppname(context) + "campaign";
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String savedCampaigns = sharedPreferences.getString(prefName, null);
        try {
            ArrayList<Campaign> arrayList;
            Type type = new TypeToken<ArrayList<Campaign>>() {
            }.getType();
            arrayList = gson.fromJson(savedCampaigns, type);
            if (arrayList != null) {
                for (Campaign campaign : arrayList) {
                    if (campaignId.equals(campaign.getStringId())) {
                        arrayList.remove(campaign);
                        break;
                    }
                }
                SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                if (arrayList.size() > 0) {
                    String json = gson.toJson(arrayList);
                    prefsEditor.putString(prefName, json);
                } else {
                    prefsEditor.remove(prefName);
                }
                prefsEditor.apply();
            }
        } catch (Exception ignored) {

        }
    }

    /**
     * Iterate over the pending response list and send response to server one at a time
     *
     * @param context context
     */
    public static void sendPendingResponses(Context context) {
        String prefName = getAppname(context) + "responseList";
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(prefName, null);
        if (json == null) {
            return;
        }
        //Sends more than one responses
        try {
            Response[] responses = gson.fromJson(json, Response[].class);
            for (Response response : responses) {
                String jsonResponse = gson.toJson(response);
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONObject sendJson = new JSONObject();
                sendJson.put("data", jsonObject);
                submitCampaignToLoyagram(sendJson.toString(), context, response.getCampaignId());
            }
        } catch (Exception ignored) {

        }
        //Sends only one response
        try {
            Response response = gson.fromJson(json, Response.class);
            String jsonResponse = gson.toJson(response);
            JSONObject jsonObject = new JSONObject(jsonResponse);
            submitCampaignToLoyagram(jsonObject.toString(), context, response.getCampaignId());

        } catch (Exception ignored) {

        }

    }

    /**
     * Submit pending campaign response to server
     *
     * @param sendJson   JSON response to be send
     * @param context    context
     * @param campaignId campaign ID
     */
    private static void submitCampaignToLoyagram(String sendJson, final Context context, final BigDecimal campaignId) {

        AsyncTaskSendResponse asyncTasKRequestQuestion = new AsyncTaskSendResponse();
        asyncTasKRequestQuestion.setListener(new APIResultCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {

                //Remove the saved response from preference as it has successfully submited to server
                removeSubmitedCampaignFromPreference(campaignId, context);

            }

            @Override
            public void onError(List<ErrorModel> errors) {
                //Do nothing
            }
        });
        asyncTasKRequestQuestion.execute(sendJson);
    }

    /**
     * Removes submited response from the pending response list.
     *
     * @param campaignId campaign id of the response to be removed
     * @param context    context
     */
    private static void removeSubmitedCampaignFromPreference(BigDecimal campaignId, Context context) {
        String prefName = getAppname(context) + "responseList";
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String savedResponses = sharedPreferences.getString(prefName, null);
        try {
            ArrayList<Response> arrayList;
            Type type = new TypeToken<ArrayList<Response>>() {
            }.getType();
            arrayList = gson.fromJson(savedResponses, type);
            if (arrayList != null) {
                for (Response response : arrayList) {
                    if (campaignId.equals(response.getCampaignId())) {
                        arrayList.remove(response);
                        break;
                    }
                }
                SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                if (arrayList.size() > 0) {
                    String json = gson.toJson(arrayList);
                    prefsEditor.putString(prefName, json);
                } else {
                    prefsEditor.remove(prefName);
                }
                prefsEditor.apply();
            }
        } catch (Exception ignored) {

        }

    }

    /**
     * Returns the application Name
     *
     * @param context context
     * @return Application name
     */
    private static String getAppname(Context context) {
        return context.getResources().getString(R.string.app_name);
    }

    public static void addAttribute(String key, String value) {
        LoyagramAttributes.getInstance().setAttribute(key, value);
    }

    public static void addAttributes(HashMap<String, String> attributes) {
        LoyagramAttributes.getInstance().setAttributes(attributes);
    }
}
