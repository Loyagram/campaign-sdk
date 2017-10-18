package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user1 on 19/5/17.
 */

public class WelcomeTranslation implements Parcelable {

    @SerializedName("lang")
    private String code = null;
    @SerializedName("text")
    private String translation = null;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    protected WelcomeTranslation(Parcel in) {

        boolean isPresent = false;

        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.code = in.readString();
        } else {
            this.code = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.translation = in.readString();
        } else {
            this.translation = null;
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ThankYouTranslation {\n");

        sb.append("  code: ").append(code).append("\n");
        sb.append("  translation: ").append(translation).append("\n");
        sb.append("}\n");
        return sb.toString();
    }


    public static final Creator<WelcomeTranslation> CREATOR = new Creator<WelcomeTranslation>() {
        @Override
        public WelcomeTranslation createFromParcel(Parcel in) {
            return new WelcomeTranslation(in);
        }

        @Override
        public WelcomeTranslation[] newArray(int size) {
            return new WelcomeTranslation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        if (this.code == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.code);
        }

        if (this.translation == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.translation);
        }
    }
}
