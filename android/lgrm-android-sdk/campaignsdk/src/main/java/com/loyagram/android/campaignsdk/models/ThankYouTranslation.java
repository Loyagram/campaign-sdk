package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.loyagram.android.campaignsdk.models.npssettings.ThankYouAndRedirectSettings;

/**
 * Created by user1 on 19/5/17.
 */

public class ThankYouTranslation implements Parcelable {
    @SerializedName("lang")
    private String code = null;
    @SerializedName("text")
    private ThankYouAndRedirectSettings thankYouAndRedirectSettings = null;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public ThankYouAndRedirectSettings getThankYouAndRedirectSettings() {
        return thankYouAndRedirectSettings;
    }

    public void setThankYouAndRedirectSettings(ThankYouAndRedirectSettings thankYouAndRedirectSettings) {
        this.thankYouAndRedirectSettings = thankYouAndRedirectSettings;
    }


    protected ThankYouTranslation(Parcel in) {

        boolean isPresent = false;

        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.code = in.readString();
        } else {
            this.code = null;
        }

        this.thankYouAndRedirectSettings = in.readParcelable(ThankYouAndRedirectSettings.class.getClassLoader());

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ThankYouTranslation {\n");

        sb.append("  code: ").append(code).append("\n");
        sb.append("  translation: ").append(thankYouAndRedirectSettings).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

    public static final Creator<ThankYouTranslation> CREATOR = new Creator<ThankYouTranslation>() {
        @Override
        public ThankYouTranslation createFromParcel(Parcel in) {
            return new ThankYouTranslation(in);
        }

        @Override
        public ThankYouTranslation[] newArray(int size) {
            return new ThankYouTranslation[size];
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

        dest.writeParcelable(this.thankYouAndRedirectSettings, flags);
    }
}
