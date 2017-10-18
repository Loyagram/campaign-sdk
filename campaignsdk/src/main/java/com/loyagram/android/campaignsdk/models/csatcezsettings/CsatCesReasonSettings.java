package com.loyagram.android.campaignsdk.models.csatcezsettings;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user1 on 23/6/17.
 */

public class CsatCesReasonSettings implements Parcelable {
    @SerializedName("type")
    private String type = null;
    @SerializedName("all")
    private CsatCesReason all = null;
    @SerializedName("custom")
    private CsatCesCustomSettings custom = null;

    public String getType() {
        return type;
    }

    public CsatCesReason getAll() {
        return all;
    }

    public CsatCesCustomSettings getCustom() {
        return custom;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAll(CsatCesReason all) {
        this.all = all;
    }

    public void setCustom(CsatCesCustomSettings custom) {
        this.custom = custom;
    }

    protected CsatCesReasonSettings(Parcel in) {
        this.type = in.readString();
        this.all = in.readParcelable(CsatCesReason.class.getClassLoader());
        this.custom = in.readParcelable(CsatCesCustomSettings.class.getClassLoader());
    }

    public static final Creator<CsatCesReasonSettings> CREATOR = new Creator<CsatCesReasonSettings>() {
        @Override
        public CsatCesReasonSettings createFromParcel(Parcel in) {
            return new CsatCesReasonSettings(in);
        }

        @Override
        public CsatCesReasonSettings[] newArray(int size) {
            return new CsatCesReasonSettings[size];
        }
    };

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
}
