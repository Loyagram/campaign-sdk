package com.loyagram.android.campaignsdk.models.npssettings;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by renju on 18/10/16.
 */
public class RequestReasonSettings implements Parcelable {
    @SerializedName("type")
    private String type = null;
    @SerializedName("all")
    private ReasonSetting all = null;
    @SerializedName("custom")
    private CustomReasonSettings custom = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ReasonSetting getAll() {
        return all;
    }

    public void setAll(ReasonSetting all) {
        this.all = all;
    }

    public CustomReasonSettings getCustom() {
        return custom;
    }

    public void setCustom(CustomReasonSettings custom) {
        this.custom = custom;
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

    public RequestReasonSettings() {
    }

    protected RequestReasonSettings(Parcel in) {
        this.type = in.readString();
        this.all = in.readParcelable(ReasonSetting.class.getClassLoader());
        this.custom = in.readParcelable(CustomReasonSettings.class.getClassLoader());
    }

    public static final Parcelable.Creator<RequestReasonSettings> CREATOR = new Parcelable.Creator<RequestReasonSettings>() {
        @Override
        public RequestReasonSettings createFromParcel(Parcel source) {
            return new RequestReasonSettings(source);
        }

        @Override
        public RequestReasonSettings[] newArray(int size) {
            return new RequestReasonSettings[size];
        }
    };
}
