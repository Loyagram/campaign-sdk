package com.loyagram.android.campaignsdk.models.npssettings;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by renju on 18/10/16.
 */
public class ReasonSetting implements Parcelable {
    @SerializedName("disabled")
    private boolean disabled = false;
    @SerializedName("message")
    private String message = "";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public ReasonSetting(boolean disabled, String message) {
        this.disabled = disabled;
        this.message = message;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(disabled ? (byte) 1 : (byte) 0);
        dest.writeString(this.message);
    }

    protected ReasonSetting(Parcel in) {
        this.disabled = in.readByte() != 0;
        this.message = in.readString();
    }

    public static final Parcelable.Creator<ReasonSetting> CREATOR = new Parcelable.Creator<ReasonSetting>() {
        @Override
        public ReasonSetting createFromParcel(Parcel source) {
            return new ReasonSetting(source);
        }

        @Override
        public ReasonSetting[] newArray(int size) {
            return new ReasonSetting[size];
        }
    };
}
