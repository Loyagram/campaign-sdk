package com.loyagram.android.campaignsdk.asynctask;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loyagram.android.campaignsdk.R;
import com.loyagram.android.campaignsdk.globals.LoyagramCampaignSdk;
import com.loyagram.android.campaignsdk.models.Campaign;
import com.loyagram.android.campaignsdk.restapi.APIResultCallback;
import com.loyagram.android.campaignsdk.restapi.ApiBase;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

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
            urlConnection.setConnectTimeout(20000);
            urlConnection.setReadTimeout(20000);
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
            campaign = gson.fromJson(getQuestion(), Campaign.class);
            //campaign = gson.fromJson(jsonObject.toString(), Campaign.class);
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
        //Log.d("AsyncTask", "FirstonPostExecute()");
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
        // return "/in-store/" + campaignId + "?lang=all";

        // return api.replaceAll("\\{" + "campaignId" + "\\}", campaignId.toString());
    }

    private static String getAppname(Context context) {
        return context.getResources().getString(R.string.app_name);
    }


    private static String getQuestion(){

        String json = "{\n" +
                "  \"static_texts\": [\n" +
                "    {\n" +
                "      \"text\": \"Start\",\n" +
                "      \"static_text_id\": \"CAMPAIGN_MODE_START_BUTTON_TEXT\",\n" +
                "      \"language_code\": \"en\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Previous\",\n" +
                "      \"static_text_id\": \"CAMPAIGN_MODE_BACK_BUTTON_TEXT\",\n" +
                "      \"language_code\": \"en\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Next\",\n" +
                "      \"static_text_id\": \"CAMPAIGN_MODE_NEXT_BUTTON_TEXT\",\n" +
                "      \"language_code\": \"en\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Submit\",\n" +
                "      \"static_text_id\": \"CAMPAIGN_MODE_SUBMIT_BUTTON_TEXT\",\n" +
                "      \"language_code\": \"en\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"You rated {8} out of {10}\",\n" +
                "      \"static_text_id\": \"SCORE_MESSAGE_TEXT\",\n" +
                "      \"language_code\": \"en\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Change answer\",\n" +
                "      \"static_text_id\": \"CHANGE_SCORE_BUTTON_TEXT\",\n" +
                "      \"language_code\": \"en\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Powered by Loyagram\",\n" +
                "      \"static_text_id\": \"POWERED_BY\",\n" +
                "      \"language_code\": \"en\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"YES\",\n" +
                "      \"static_text_id\": \"PLUGIN_DIALOGUE_BOX_ACTIVE_BUTTON_TEXT\",\n" +
                "      \"language_code\": \"en\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"NO\",\n" +
                "      \"static_text_id\": \"PLUGIN_DIALOGUE_BOX_PASSIVE_BUTTON_TEXT\",\n" +
                "      \"language_code\": \"en\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"You rated\",\n" +
                "      \"static_text_id\": \"SCORE_MESSAGE_YOU_RATED_TEXT\",\n" +
                "      \"language_code\": \"en\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"out of\",\n" +
                "      \"static_text_id\": \"SCORE_MESSAGE_OUT_OF_TEXT\",\n" +
                "      \"language_code\": \"en\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"you rated {current_score} out of {max_score}\",\n" +
                "      \"static_text_id\": \"SCORE_TEXT_TEMPLATE\",\n" +
                "      \"language_code\": \"en\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"you rated __ out of ___\",\n" +
                "      \"static_text_id\": \"SCORE_TEXT_TEMPLATE_SPC\",\n" +
                "      \"language_code\": \"en\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"I would like to receive a follow up.\",\n" +
                "      \"static_text_id\": \"FOLLOW_UP_REQUEST_CHECKBOX_LABEL\",\n" +
                "      \"language_code\": \"en\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"This is a required question.\",\n" +
                "      \"static_text_id\": \"MANDATORY_QUESTION_TEXT\",\n" +
                "      \"language_code\": \"en\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"* Takes less than 25 seconds.\",\n" +
                "      \"static_text_id\": \"WIDGET_WELCOME_TIP\",\n" +
                "      \"language_code\": \"en\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Enter your email...\",\n" +
                "      \"static_text_id\": \"EMAIL_ADDRESS_PLACEHOLDER_TEXT\",\n" +
                "      \"language_code\": \"en\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Please type here...\",\n" +
                "      \"static_text_id\": \"INPUT_PLACEHOLDER_TEXT\",\n" +
                "      \"language_code\": \"en\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Invalid data\",\n" +
                "      \"static_text_id\": \"VALIDATION_FAILED_TEXT\",\n" +
                "      \"language_code\": \"en\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Invalid email\",\n" +
                "      \"static_text_id\": \"EMAIL_NOT_VALID_TEXT\",\n" +
                "      \"language_code\": \"en\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Inicio\",\n" +
                "      \"static_text_id\": \"CAMPAIGN_MODE_START_BUTTON_TEXT\",\n" +
                "      \"language_code\": \"es\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Anterior\",\n" +
                "      \"static_text_id\": \"CAMPAIGN_MODE_BACK_BUTTON_TEXT\",\n" +
                "      \"language_code\": \"es\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Siguiente\",\n" +
                "      \"static_text_id\": \"CAMPAIGN_MODE_NEXT_BUTTON_TEXT\",\n" +
                "      \"language_code\": \"es\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Enviar\",\n" +
                "      \"static_text_id\": \"CAMPAIGN_MODE_SUBMIT_BUTTON_TEXT\",\n" +
                "      \"language_code\": \"es\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Nominal {8} fuera {10}\",\n" +
                "      \"static_text_id\": \"SCORE_MESSAGE_TEXT\",\n" +
                "      \"language_code\": \"es\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Respuesta de cambio\",\n" +
                "      \"static_text_id\": \"CHANGE_SCORE_BUTTON_TEXT\",\n" +
                "      \"language_code\": \"es\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Powered by Loyagram\",\n" +
                "      \"static_text_id\": \"POWERED_BY\",\n" +
                "      \"language_code\": \"es\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"S\\u00ed\",\n" +
                "      \"static_text_id\": \"PLUGIN_DIALOGUE_BOX_ACTIVE_BUTTON_TEXT\",\n" +
                "      \"language_code\": \"es\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"No\",\n" +
                "      \"static_text_id\": \"PLUGIN_DIALOGUE_BOX_PASSIVE_BUTTON_TEXT\",\n" +
                "      \"language_code\": \"es\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Nominal\",\n" +
                "      \"static_text_id\": \"SCORE_MESSAGE_YOU_RATED_TEXT\",\n" +
                "      \"language_code\": \"es\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"hacia fuera de\",\n" +
                "      \"static_text_id\": \"SCORE_MESSAGE_OUT_OF_TEXT\",\n" +
                "      \"language_code\": \"es\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"clasificado {current_score} de {max_score}\",\n" +
                "      \"static_text_id\": \"SCORE_TEXT_TEMPLATE\",\n" +
                "      \"language_code\": \"es\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"nominal __ de ___\",\n" +
                "      \"static_text_id\": \"SCORE_TEXT_TEMPLATE_SPC\",\n" +
                "      \"language_code\": \"es\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"\\u00bfQuieres seguirnos? Incribete aqu\\u00ed \",\n" +
                "      \"static_text_id\": \"FOLLOW_UP_REQUEST_CHECKBOX_LABEL\",\n" +
                "      \"language_code\": \"es\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Esta pregunta es obligatoria.\",\n" +
                "      \"static_text_id\": \"MANDATORY_QUESTION_TEXT\",\n" +
                "      \"language_code\": \"es\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"toma menos de 25 segundos.\",\n" +
                "      \"static_text_id\": \"WIDGET_WELCOME_TIP\",\n" +
                "      \"language_code\": \"es\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Ingresa su correo aqu\\u00ed...\",\n" +
                "      \"static_text_id\": \"EMAIL_ADDRESS_PLACEHOLDER_TEXT\",\n" +
                "      \"language_code\": \"es\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Por favor escribe aqu\\u00ed...\",\n" +
                "      \"static_text_id\": \"INPUT_PLACEHOLDER_TEXT\",\n" +
                "      \"language_code\": \"es\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Datos inv\\u00e1lido\",\n" +
                "      \"static_text_id\": \"VALIDATION_FAILED_TEXT\",\n" +
                "      \"language_code\": \"es\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"Correo inv\\u00e1lido\",\n" +
                "      \"static_text_id\": \"EMAIL_NOT_VALID_TEXT\",\n" +
                "      \"language_code\": \"es\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"thankyou_message_enabled\": true,\n" +
                "  \"in_web_channel\": true,\n" +
                "  \"in_app_channel\": true,\n" +
                "  \"updated_at\": \"2017-11-16T05:43:24.474129+00:00\",\n" +
                "  \"in_store_channel\": true,\n" +
                "  \"thank_you_and_redirect_settings\": {\n" +
                "    \"all\": {\n" +
                "      \"disabled\": false,\n" +
                "      \"message\": \"Thank you for the response! We value your feedback and assure to improve based on it.\",\n" +
                "      \"links\": [\n" +
                "        \n" +
                "      ]\n" +
                "    },\n" +
                "    \"type\": \"custom\"\n" +
                "  },\n" +
                "  \"in_sms_channel\": true,\n" +
                "  \"questions\": [\n" +
                "    {\n" +
                "      \"order_no\": 1,\n" +
                "      \"settings\": {\n" +
                "        \n" +
                "      },\n" +
                "      \"optional\": false,\n" +
                "      \"labels\": [\n" +
                "        {\n" +
                "          \"field_type\": \"SHORT_ANSWER\",\n" +
                "          \"name\": \"\",\n" +
                "          \"order_no\": 1,\n" +
                "          \"settings\": {\n" +
                "            \n" +
                "          },\n" +
                "          \"deleted\": false,\n" +
                "          \"max_value\": 5,\n" +
                "          \"min_value\": 1,\n" +
                "          \"enabled\": true,\n" +
                "          \"value\": \"1\",\n" +
                "          \"label\": \"\",\n" +
                "          \"label_translations\": [\n" +
                "            {\n" +
                "              \"text\": \"\",\n" +
                "              \"auto\": false,\n" +
                "              \"language_code\": \"en\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"text\": \"\",\n" +
                "              \"auto\": false,\n" +
                "              \"language_code\": \"es\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"step_value\": 1,\n" +
                "          \"img_url\": null,\n" +
                "          \"id\": 1009,\n" +
                "          \"question_id\": 1007\n" +
                "        }\n" +
                "      ],\n" +
                "      \"question\": \"What did you like the most about us?\",\n" +
                "      \"campaign_id\": 1004,\n" +
                "      \"question_translations\": [\n" +
                "        {\n" +
                "          \"text\": \"What did you like the most about us?\",\n" +
                "          \"language_code\": \"en\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"text\": \"What did you like the most about us?\",\n" +
                "          \"language_code\": \"es\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"others_entry\": false,\n" +
                "      \"placeholders\": \"\",\n" +
                "      \"parent_question_id\": null,\n" +
                "      \"type\": \"SHORT_ANSWER\",\n" +
                "      \"id\": 1007,\n" +
                "      \"img_url\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"order_no\": 2,\n" +
                "      \"settings\": {\n" +
                "        \n" +
                "      },\n" +
                "      \"optional\": true,\n" +
                "      \"labels\": [\n" +
                "        {\n" +
                "          \"field_type\": \"PARAGRAPH\",\n" +
                "          \"name\": \"\",\n" +
                "          \"order_no\": 1,\n" +
                "          \"settings\": {\n" +
                "            \n" +
                "          },\n" +
                "          \"deleted\": false,\n" +
                "          \"max_value\": 5,\n" +
                "          \"min_value\": 1,\n" +
                "          \"enabled\": true,\n" +
                "          \"value\": \"1\",\n" +
                "          \"label\": \"\",\n" +
                "          \"label_translations\": [\n" +
                "            {\n" +
                "              \"text\": \"\",\n" +
                "              \"auto\": false,\n" +
                "              \"language_code\": \"en\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"text\": \"\",\n" +
                "              \"auto\": false,\n" +
                "              \"language_code\": \"es\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"step_value\": 1,\n" +
                "          \"img_url\": null,\n" +
                "          \"id\": 1010,\n" +
                "          \"question_id\": 1008\n" +
                "        }\n" +
                "      ],\n" +
                "      \"question\": \"Describe a bit more about you?\",\n" +
                "      \"campaign_id\": 1004,\n" +
                "      \"question_translations\": [\n" +
                "        {\n" +
                "          \"text\": \"Describe a bit more about you?\",\n" +
                "          \"language_code\": \"en\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"text\": \"Describe a bit more about you?\",\n" +
                "          \"language_code\": \"es\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"others_entry\": false,\n" +
                "      \"placeholders\": \"\",\n" +
                "      \"parent_question_id\": null,\n" +
                "      \"type\": \"PARAGRAPH\",\n" +
                "      \"id\": 1008,\n" +
                "      \"img_url\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"order_no\": 3,\n" +
                "      \"settings\": {\n" +
                "        \n" +
                "      },\n" +
                "      \"optional\": true,\n" +
                "      \"labels\": [\n" +
                "        {\n" +
                "          \"field_type\": \"EMAIL\",\n" +
                "          \"name\": \"\",\n" +
                "          \"order_no\": 1,\n" +
                "          \"settings\": {\n" +
                "            \n" +
                "          },\n" +
                "          \"deleted\": false,\n" +
                "          \"max_value\": 5,\n" +
                "          \"min_value\": 1,\n" +
                "          \"enabled\": true,\n" +
                "          \"value\": \"1\",\n" +
                "          \"label\": \"\",\n" +
                "          \"label_translations\": [\n" +
                "            {\n" +
                "              \"text\": \"\",\n" +
                "              \"auto\": false,\n" +
                "              \"language_code\": \"en\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"text\": \"\",\n" +
                "              \"auto\": false,\n" +
                "              \"language_code\": \"es\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"step_value\": 1,\n" +
                "          \"img_url\": null,\n" +
                "          \"id\": 1011,\n" +
                "          \"question_id\": 1009\n" +
                "        }\n" +
                "      ],\n" +
                "      \"question\": \"Please provide your email?\",\n" +
                "      \"campaign_id\": 1004,\n" +
                "      \"question_translations\": [\n" +
                "        {\n" +
                "          \"text\": \"Please provide your email?\",\n" +
                "          \"language_code\": \"en\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"text\": \"Please provide your email?\",\n" +
                "          \"language_code\": \"es\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"others_entry\": false,\n" +
                "      \"placeholders\": \"\",\n" +
                "      \"parent_question_id\": null,\n" +
                "      \"type\": \"EMAIL\",\n" +
                "      \"id\": 1009,\n" +
                "      \"img_url\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"order_no\": 4,\n" +
                "      \"settings\": {\n" +
                "        \n" +
                "      },\n" +
                "      \"optional\": true,\n" +
                "      \"labels\": [\n" +
                "        {\n" +
                "          \"field_type\": \"NUMBER\",\n" +
                "          \"name\": \"\",\n" +
                "          \"order_no\": 1,\n" +
                "          \"settings\": {\n" +
                "            \n" +
                "          },\n" +
                "          \"deleted\": false,\n" +
                "          \"max_value\": 5,\n" +
                "          \"min_value\": 1,\n" +
                "          \"enabled\": true,\n" +
                "          \"value\": \"1\",\n" +
                "          \"label\": \"\",\n" +
                "          \"label_translations\": [\n" +
                "            {\n" +
                "              \"text\": \"\",\n" +
                "              \"auto\": false,\n" +
                "              \"language_code\": \"en\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"text\": \"\",\n" +
                "              \"auto\": false,\n" +
                "              \"language_code\": \"es\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"step_value\": 1,\n" +
                "          \"img_url\": null,\n" +
                "          \"id\": 1012,\n" +
                "          \"question_id\": 1010\n" +
                "        }\n" +
                "      ],\n" +
                "      \"question\": \"Mobile number please?\",\n" +
                "      \"campaign_id\": 1004,\n" +
                "      \"question_translations\": [\n" +
                "        {\n" +
                "          \"text\": \"Mobile number please?\",\n" +
                "          \"language_code\": \"en\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"text\": \"Mobile number please?\",\n" +
                "          \"language_code\": \"es\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"others_entry\": false,\n" +
                "      \"placeholders\": \"\",\n" +
                "      \"parent_question_id\": null,\n" +
                "      \"type\": \"NUMBER\",\n" +
                "      \"id\": 1010,\n" +
                "      \"img_url\": \"\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"thankyou_message\": \"Thank you for the response! We value your feedback and assure to improve based on it.\",\n" +
                "  \"brand_title\": \"sandhil\",\n" +
                "  \"id\": 1004,\n" +
                "  \"in_url_channel\": true,\n" +
                "  \"user_id\": 1002,\n" +
                "  \"welcome_message\": \"We would love to hear from you!\",\n" +
                "  \"note\": \"test\",\n" +
                "  \"color_accent\": null,\n" +
                "  \"translation_status\": \"PENDING\",\n" +
                "  \"logo_url\": \"\\/uploads\\/logo.png\",\n" +
                "  \"type\": \"SURVEY\",\n" +
                "  \"welcome_message_enabled\": true,\n" +
                "  \"thank_you_and_redirect_settings_translations\": [\n" +
                "    {\n" +
                "      \"text\": {\n" +
                "        \"all\": {\n" +
                "          \"disabled\": false,\n" +
                "          \"auto\": true,\n" +
                "          \"message\": \"Thank you for the response! We value your feedback and assure to improve based on it.\",\n" +
                "          \"links\": [\n" +
                "            \n" +
                "          ]\n" +
                "        },\n" +
                "        \"type\": \"custom\"\n" +
                "      },\n" +
                "      \"language_code\": \"en\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": {\n" +
                "        \"all\": {\n" +
                "          \"disabled\": false,\n" +
                "          \"auto\": true,\n" +
                "          \"message\": \"Thank you for the response! We value your feedback and assure to improve based on it.\",\n" +
                "          \"links\": [\n" +
                "            \n" +
                "          ]\n" +
                "        },\n" +
                "        \"type\": \"custom\"\n" +
                "      },\n" +
                "      \"language_code\": \"es\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"deleted\": false,\n" +
                "  \"biz_id\": 1001,\n" +
                "  \"in_pack_channel\": true,\n" +
                "  \"biz\": {\n" +
                "    \"color_accent\": null,\n" +
                "    \"in_url_channel\": true,\n" +
                "    \"color_primary\": \"#1ABC9C\",\n" +
                "    \"name\": \"sandhil\",\n" +
                "    \"color_secondary\": null,\n" +
                "    \"in_web_channel\": true,\n" +
                "    \"in_app_channel\": true,\n" +
                "    \"number\": \"\",\n" +
                "    \"id\": 1001,\n" +
                "    \"in_pack_channel\": true,\n" +
                "    \"user_updated\": null,\n" +
                "    \"in_sms_channel\": false,\n" +
                "    \"in_store_channel\": true,\n" +
                "    \"img_url\": \"\\/uploads\\/logo.png\",\n" +
                "    \"email\": \"sandhil@loyagram.com\",\n" +
                "    \"in_mail_channel\": true\n" +
                "  },\n" +
                "  \"active_to\": null,\n" +
                "  \"active\": false,\n" +
                "  \"str_id\": \"1001-d5bc92d6-6de4-4c15-b842-f5d8d5bab79c\",\n" +
                "  \"color_primary\": \"#1ABC9C\",\n" +
                "  \"in_mail_channel\": true,\n" +
                "  \"color_secondary\": null,\n" +
                "  \"welcome_message_translations\": [\n" +
                "    {\n" +
                "      \"text\": \"We would love to hear from you!\",\n" +
                "      \"language_code\": \"en\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"\\u00a1Nos encantar\\u00eda saber de t\\u00ed!\",\n" +
                "      \"auto\": true,\n" +
                "      \"language_code\": \"es\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"name\": \"survey\",\n" +
                "  \"settings\": {\n" +
                "    \"follow_up_question_enabled\": true,\n" +
                "    \"translations\": [\n" +
                "      {\n" +
                "        \"language_code\": \"en\",\n" +
                "        \"name_en\": \"English\",\n" +
                "        \"selected\": true,\n" +
                "        \"name\": \"English\",\n" +
                "        \"primary\": true\n" +
                "      },\n" +
                "      {\n" +
                "        \"status\": \"PENDING\",\n" +
                "        \"name_en\": \"Spanish\",\n" +
                "        \"selected\": true,\n" +
                "        \"name\": \"Espa\\u00f1ol\",\n" +
                "        \"language_code\": \"es\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"created_at\": \"2017-11-16T05:43:24.479152+00:00\",\n" +
                "  \"in_web_settings\": {\n" +
                "    \"idle_seconds\": 30,\n" +
                "    \"show_on_scroll_to_bottom\": false,\n" +
                "    \"custom_css\": null,\n" +
                "    \"widget_position\": \"BOTTOM_RIGHT\",\n" +
                "    \"do_not_show_button\": false,\n" +
                "    \"show_on_window_close\": false,\n" +
                "    \"button_position\": \"BOTTOM_RIGHT\",\n" +
                "    \"campaign_id\": null,\n" +
                "    \"is_modal\": false,\n" +
                "    \"idle_for_enabled\": false,\n" +
                "    \"show_on_window_mouse_out\": false,\n" +
                "    \"button_shape\": \"TRIANGLE\",\n" +
                "    \"id\": 1\n" +
                "  },\n" +
                "  \"published\": false,\n" +
                "  \"active_from\": null,\n" +
                "  \"primary_lang\": \"en\"\n" +
                "}";
        return json;

    }
}
