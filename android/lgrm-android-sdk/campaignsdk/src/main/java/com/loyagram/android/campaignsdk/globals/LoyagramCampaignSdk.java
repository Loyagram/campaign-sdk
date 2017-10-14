package com.loyagram.android.campaignsdk.globals;

import com.loyagram.android.campaignsdk.campaigncallback.CampaignCallback;

/**
 * LoyagramCampaignSdk class which holds the campaign call backs.
 */

public class LoyagramCampaignSdk {

    private static LoyagramCampaignSdk instance = null;
    private String campaignId;
    private CampaignCallback campaignCallback;
    private static String apiKey;
    private static String accessSecret;
    private String domainType;

    private LoyagramCampaignSdk() {
    }

    public static void init(String key, String secret) {
        getInstance();
        apiKey = key;
        accessSecret = secret;
    }

    public String getApiKey() {
        return apiKey;
    }


    public String getAccessSecret() {
        return accessSecret;
    }

    public String getDomainType() {
        return this.domainType;
    }

    public static LoyagramCampaignSdk getInstance() {

        if (instance == null) {
            instance = new LoyagramCampaignSdk();
        }
        return instance;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignId() {
        return this.campaignId;
    }

    public void setDomainType(String domainType) {
        this.domainType = domainType;
    }

    public void setCampaignCallback(CampaignCallback campaignCallback) {
        this.campaignCallback = campaignCallback;
    }

    public CampaignCallback getCampaignCallback() {
        return this.campaignCallback;
    }

    public void resetSingleton() {
        if (instance != null) {
            instance.campaignCallback = null;
        }
    }
}
