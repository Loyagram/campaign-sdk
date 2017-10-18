package com.loyagram.android.campaignsdk.models.npssettings;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by renju on 18/10/16.
 */
public class ReferralSettings implements Parcelable {
    @SerializedName("disabled")
    private boolean disabled = false;
    @SerializedName("message")
    private String message = "";
    @SerializedName("limit")
    private int limit = 0;
    @SerializedName("url")
    private String url = "";

    public ReferralSettings() {
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(disabled ? (byte) 1 : (byte) 0);
        dest.writeString(this.message);
        dest.writeInt(this.limit);
        dest.writeString(this.url);
    }

    protected ReferralSettings(Parcel in) {
        this.disabled = in.readByte() != 0;
        this.message = in.readString();
        this.limit = in.readInt();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<ReferralSettings> CREATOR = new Parcelable.Creator<ReferralSettings>() {
        @Override
        public ReferralSettings createFromParcel(Parcel source) {
            return new ReferralSettings(source);
        }

        @Override
        public ReferralSettings[] newArray(int size) {
            return new ReferralSettings[size];
        }
    };
}
