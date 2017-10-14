package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;


public class ResponseAnswer implements Parcelable {

    @SerializedName("id")
    private String id = null;
    @SerializedName("biz_id")
    private BigDecimal bizId = null;
    @SerializedName("biz_loc_id")
    private BigDecimal bizLocId = null;
    @SerializedName("biz_user_id")
    private BigDecimal bizUserId = null;
    @SerializedName("campaign_id")
    private BigDecimal campaignId = null;
    @SerializedName("response_id")
    private String campaignedId = null;
    @SerializedName("question_id")
    private BigDecimal questionId = null;
    @SerializedName("question_label_id")
    private BigDecimal questionLabelId = null;
    @SerializedName("at")
    private long at = 0;
    @SerializedName("answer")
    private BigDecimal value = null;
    @SerializedName("response_answer_text")
    private ResponseAnswerText responseAnswerText = null;


    /**
     **/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    /**
     **/

    public BigDecimal getBizId() {
        return bizId;
    }

    public void setBizId(BigDecimal bizId) {
        this.bizId = bizId;
    }


    /**
     **/

    public BigDecimal getBizLocId() {
        return bizLocId;
    }

    public void setBizLocId(BigDecimal bizLocId) {
        this.bizLocId = bizLocId;
    }


    /**
     **/

    public BigDecimal getBizUserId() {
        return bizUserId;
    }

    public void setBizUserId(BigDecimal bizUserId) {
        this.bizUserId = bizUserId;
    }


    /**
     **/

    public BigDecimal getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(BigDecimal campaignId) {
        this.campaignId = campaignId;
    }


    /**
     **/

    public String getCampaignedId() {
        return campaignedId;
    }

    public void setCampaignedId(String campaignedId) {
        this.campaignedId = campaignedId;
    }


    /**
     **/

    public BigDecimal getQuestionId() {
        return questionId;
    }

    public void setQuestionId(BigDecimal questionId) {
        this.questionId = questionId;
    }


    /**
     **/

    public BigDecimal getQuestionLabelId() {
        return questionLabelId;
    }

    public void setQuestionLabelId(BigDecimal questionLabelId) {
        this.questionLabelId = questionLabelId;
    }


    /**
     **/

    public long getAt() {
        return at;
    }

    public void setAt(long at) {
        this.at = at;
    }


    /**
     **/

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }


    /**
     **/

    public ResponseAnswerText getResponseAnswerText() {
        return responseAnswerText;
    }

    public void setResponseAnswerText(ResponseAnswerText responseAnswerText) {
        this.responseAnswerText = responseAnswerText;
    }


    public ResponseAnswer() {
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ResponseAnswer> CREATOR = new Parcelable.Creator<ResponseAnswer>() {
        @Override
        public ResponseAnswer createFromParcel(Parcel in) {
            return new ResponseAnswer(in);
        }

        @Override
        public ResponseAnswer[] newArray(int size) {
            return new ResponseAnswer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        if (this.id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.id);
        }


        if (this.bizId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.bizId);
        }


        if (this.bizLocId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.bizLocId);
        }


        if (this.bizUserId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.bizUserId);
        }


        if (this.campaignId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.campaignId);
        }


        if (this.campaignedId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.campaignedId);
        }


        if (this.questionId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.questionId);
        }


        if (this.questionLabelId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.questionLabelId);
        }


        if (this.at == 0) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(this.at);
        }


        if (this.value == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.value);
        }


        dest.writeParcelable(this.responseAnswerText, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
    }

    public ResponseAnswer(Parcel in) {
        boolean isPresent = false;


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.id = in.readString();
        } else {
            this.id = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.bizId = (BigDecimal) in.readSerializable();
        } else {
            this.bizId = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.bizLocId = (BigDecimal) in.readSerializable();
        } else {
            this.bizLocId = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.bizUserId = (BigDecimal) in.readSerializable();
        } else {
            this.bizUserId = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.campaignId = (BigDecimal) in.readSerializable();
        } else {
            this.campaignId = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.campaignedId = in.readString();
        } else {
            this.campaignedId = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.questionId = (BigDecimal) in.readSerializable();
        } else {
            this.questionId = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.questionLabelId = (BigDecimal) in.readSerializable();
        } else {
            this.questionLabelId = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.at = in.readLong();
        } else {
            this.at = 0;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.value = (BigDecimal) in.readSerializable();
        } else {
            this.value = null;
        }


        this.responseAnswerText = in.readParcelable(ResponseAnswerText.class.getClassLoader());
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ResponseAnswer {\n");

        sb.append("  id: ").append(id).append("\n");
        sb.append("  bizId: ").append(bizId).append("\n");
        sb.append("  bizLocId: ").append(bizLocId).append("\n");
        sb.append("  bizUserId: ").append(bizUserId).append("\n");
        sb.append("  campaignId: ").append(campaignId).append("\n");
        sb.append("  campaignedId: ").append(campaignedId).append("\n");
        sb.append("  questionId: ").append(questionId).append("\n");
        sb.append("  questionSettingsId: ").append(questionLabelId).append("\n");
        sb.append("  at: ").append(at).append("\n");
        sb.append("  value: ").append(value).append("\n");
        sb.append("  responseAnswerText: ").append(responseAnswerText).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
