package com.loyagram.android.campaignsdk.asynctask;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.loyagram.android.campaignsdk.globals.LoyagramCampaignSdk;
import com.loyagram.android.campaignsdk.models.Campaign;
import com.loyagram.android.campaignsdk.restapi.APIResultCallback;
import com.loyagram.android.campaignsdk.restapi.ApiBase;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class for executing the http request in background thread to get the campaign details.
 * It returns either a success call back or error call back on completion.
 */
@SuppressWarnings("WeakerAccess")
public class AsyncTasKRequestQuestion extends AsyncTask<String, Void, Void> {

    Context context;
    APIResultCallback<Campaign> apiResultCallback;
    //public static final String api = "/js_plugin_api?campaignId={campaignId}";
    Exception exception = null;
    Campaign campaign = null;
    SharedPreferences sharedPreferences;

    public AsyncTasKRequestQuestion(Context context) {
        this.context = context;
    }

    /**
     * Methods for setting the callback
     *
     * @param apiResultCallback callback once the task is finished.
     */
    public void setListener(APIResultCallback<Campaign> apiResultCallback) {
        this.apiResultCallback = apiResultCallback;
    }

    /**
     * Performs the http request in background thread
     *
     * @param params contains campaignId
     * @return null once the task completes and exit the background thread.
     */
    @Override
    protected Void doInBackground(String... params) {
        String campaignId = params[0];
        String urlString = ApiBase.getApiPath() + getQuestionBasePath(campaignId);
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(15000);
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder stringBuilder = new StringBuilder();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            if (stringBuilder.length() == 0) {
                return null;
            }

            Gson gson = new Gson();
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            campaign = gson.fromJson(jsonObject.toString(), Campaign.class);
            //Log.i("campaign", campaign.toString());
            return null;
        } catch (Exception ex) {
            exception = ex;
            // Log.i("Exception", ex.toString());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {

                }
            }
        }
        return null;
    }

    /**
     * @param result contains the result of the task done in background
     */
    @Override
    protected void onPostExecute(Void result) {
        if (exception != null) {
            apiResultCallback.parseResult(exception, null);
        } else {
            apiResultCallback.parseResult(null, campaign);
        }
    }

    /**
     * Method to append the campaignId with base URL
     *
     * @param campaignId campaignId to append in the URL
     * @return url for the http requet to get campaign
     */
    public static String getQuestionBasePath(String campaignId) {

        String apiKey = LoyagramCampaignSdk.getInstance().getApiKey();
        String accessSecret = LoyagramCampaignSdk.getInstance().getAccessSecret();
        return  "/in-store/" + campaignId + "?lang=all" + "&apiKey=" + apiKey + "&secretKey=" + accessSecret;
    }

}
