package com.loyagram.android.campaignsdk.globals;

/**
 * Saves current question number and campaign id used when handling orientation change.
 */

public class QuestionStatus {
    private static QuestionStatus instance = null;
    private String campaignId = null;
    private int questionNumber = -1;

    private QuestionStatus() {

    }

    public static QuestionStatus getInstance() {

        if (instance == null) {
            instance = new QuestionStatus();
        }
        return instance;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public void resetQuestionStats() {
        campaignId = null;
        questionNumber = -1;
    }
}
