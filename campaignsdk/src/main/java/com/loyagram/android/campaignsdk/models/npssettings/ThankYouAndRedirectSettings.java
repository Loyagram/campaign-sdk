package com.loyagram.android.campaignsdk.models.npssettings;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by renju on 18/10/16.
 */
public class ThankYouAndRedirectSettings implements Parcelable {
    @SerializedName("type")
    private String type = null;
    @SerializedName("all")
    private ThankYouAndRedirectSetting all = null;
    @SerializedName("custom")
    private CustomThankYouAndRedirectSettings custom = null;

    public ThankYouAndRedirectSettings() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CustomThankYouAndRedirectSettings getCustom() {
        return custom;
    }

    public void setCustom(CustomThankYouAndRedirectSettings custom) {
        this.custom = custom;
    }

    public ThankYouAndRedirectSetting getAll() {
        return all;
    }

    public void setAll(ThankYouAndRedirectSetting all) {
        this.all = all;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeParcelable(this.all, flags);
        dest.writeParcelable(this.custom, flags);
    }

    protected ThankYouAndRedirectSettings(Parcel in) {
        this.type = in.readString();
        this.all = in.readParcelable(ThankYouAndRedirectSetting.class.getClassLoader());
        this.custom = in.readParcelable(CustomThankYouAndRedirectSettings.class.getClassLoader());
    }

    public static final Parcelable.Creator<ThankYouAndRedirectSettings> CREATOR = new Parcelable.Creator<ThankYouAndRedirectSettings>() {
        @Override
        public ThankYouAndRedirectSettings createFromParcel(Parcel source) {
            return new ThankYouAndRedirectSettings(source);
        }

        @Override
        public ThankYouAndRedirectSettings[] newArray(int size) {
            return new ThankYouAndRedirectSettings[size];
        }
    };
}
