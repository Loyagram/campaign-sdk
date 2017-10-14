package com.loyagram.android.campaignsdk.asynctask;

import android.os.AsyncTask;

import com.loyagram.android.campaignsdk.globals.LoyagramCampaignSdk;
import com.loyagram.android.campaignsdk.restapi.APIResultCallback;
import com.loyagram.android.campaignsdk.restapi.ApiBase;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Submit users response to Loyagram server in the background. if request is success, "Thanks" response will be received.
 */
@SuppressWarnings("WeakerAccess")
public class AsyncTaskSendResponse extends AsyncTask<String, Void, Void> {

    //public static final String api = "/responses";
    APIResultCallback<Boolean> apiResultCallback;
    Exception exception;


    /**
     * Methods for setting the callback
     *
     * @param apiResultCallback callback once the task is finished.
     */
    public void setListener(APIResultCallback<Boolean> apiResultCallback) {
        this.apiResultCallback = apiResultCallback;
    }

    /**
     * Performs HTTP request in background thread.
     *
     * @param params json data to be send
     * @return null
     */
    @Override
    protected Void doInBackground(String... params) {
        String sendJson = params[0];
        String urlString = ApiBase.getApiPath() + getResponseBasePath();
       // String urlString = ApiBase.getApiPath() + api;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
       // String responseJson = null;
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-type", "application/json");
            urlConnection.setConnectTimeout(20000);
            urlConnection.setReadTimeout(20000);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            //Send request
            DataOutputStream wr = new DataOutputStream(
                    urlConnection.getOutputStream());
            wr.writeBytes(sendJson);
            wr.flush();
            wr.close();
            //Get response
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder builder = new StringBuilder();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            if (builder.length() == 0) {
                return null;
            }
            return null;
        } catch (Exception ex) {
            exception = ex;
            //Log.i("Exception", ex.toString());
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
     * send corresponding callbacks.
     *
     * @param result null
     */
    @Override
    protected void onPostExecute(Void result) {
        if (exception != null) {
            apiResultCallback.parseResult(exception, null);
        } else {
            apiResultCallback.parseResult(null, true);
        }
    }

    public static String getResponseBasePath() {

        String apiKey = LoyagramCampaignSdk.getInstance().getApiKey();
        String accessSecret = LoyagramCampaignSdk.getInstance().getAccessSecret();
        return  "/responses?apiKey=" + apiKey + "&secretKey=" + accessSecret;
        // return "/in-store/" + campaignId + "?lang=all";

        // return api.replaceAll("\\{" + "campaignId" + "\\}", campaignId.toString());
    }
}
