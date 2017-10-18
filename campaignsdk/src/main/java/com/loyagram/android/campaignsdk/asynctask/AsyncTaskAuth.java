package com.loyagram.android.campaignsdk.asynctask;


import android.os.AsyncTask;

import com.loyagram.android.campaignsdk.restapi.APIResultCallback;
import com.loyagram.android.campaignsdk.restapi.ApiBase;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;


/**
 * Handles the authentication process in the background when the user hits the back button.
 */
@SuppressWarnings("WeakerAccess")
public class AsyncTaskAuth extends AsyncTask<String, Void, Void> {
    public static final String api = "/api/v0.0.1beta/bizs/{bizId}/checkpassword";
    APIResultCallback<Boolean> apiResultCallback;
    Exception exception;
    public static BigDecimal bizId;
    String token;
    String responseResult;

    public AsyncTaskAuth(BigDecimal currentBizId, String token) {
        bizId = currentBizId;
        this.token = token;
    }

    public void setListener(APIResultCallback<Boolean> apiResultCallback) {
        this.apiResultCallback = apiResultCallback;
    }

    /**
     * send authetication request in background thread
     *
     * @param params password
     * @return null
     */
    @Override
    protected Void doInBackground(String... params) {

        String password = params[0];
        String urlString = ApiBase.getApiPath() + getAuthApiPath() + "?token=" + token;
        HttpURLConnection urlConnection;
        BufferedReader reader;
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(15000);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            JSONObject postDataParams = new JSONObject();
            postDataParams.put("password", password);
            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));
            writer.flush();
            writer.close();
            urlConnection.connect();
            //int code = urlConnection.getResponseCode();
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
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            responseResult = jsonObject.getString("status");

        } catch (Exception ignored) {

        }
        return null;
    }

    protected void onPostExecute(Void result) {
        if (exception != null) {
            apiResultCallback.parseResult(exception, null);
        } else {
            if (responseResult.equals("OK")) {
                apiResultCallback.parseResult(null, true);
            } else {
                apiResultCallback.parseResult(null, false);
            }
        }
    }


    public static String getAuthApiPath() {
        return api.replaceAll("\\{" + "bizId" + "\\}", bizId.toString());
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();
        while (itr.hasNext()) {
            String key = itr.next();
            Object value = params.get(key);
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }
}
