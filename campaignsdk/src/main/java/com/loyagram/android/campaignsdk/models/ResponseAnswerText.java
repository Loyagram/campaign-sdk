package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;


public class ResponseAnswerText implements Parcelable {

    @SerializedName("id")
    private BigDecimal id = null;
    @SerializedName("response_answer_id")
    private String campaignedAnswerId = null;
    @SerializedName("text")
    private String text = null;


    /**
     **/
    
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }


    /**
     **/
    
    public String getCampaignedAnswerId() {
        return campaignedAnswerId;
    }

    public void setCampaignedAnswerId(String campaignedAnswerId) {
        this.campaignedAnswerId = campaignedAnswerId;
    }


    /**
     **/
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public ResponseAnswerText() {
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ResponseAnswerText> CREATOR = new Parcelable.Creator<ResponseAnswerText>() {
        @Override
        public ResponseAnswerText createFromParcel(Parcel in) {
            return new ResponseAnswerText(in);
        }

        @Override
        public ResponseAnswerText[] newArray(int size) {
            return new ResponseAnswerText[size];
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
            dest.writeSerializable(this.id);
        }


        if (this.campaignedAnswerId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.campaignedAnswerId);
        }


        if (this.text == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.text);
        }


    }

    public ResponseAnswerText(Parcel in) {
        boolean isPresent = false;


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.id = (BigDecimal) in.readSerializable();
        } else {
            this.id = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.campaignedAnswerId = in.readString();
        } else {
            this.campaignedAnswerId = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.text = in.readString();
        } else {
            this.text = null;
        }


    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ResponseAnswerText {\n");
        sb.append("  id: ").append(id).append("\n");
        sb.append("  campaignedAnswerId: ").append(campaignedAnswerId).append("\n");
        sb.append("  text: ").append(text).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
