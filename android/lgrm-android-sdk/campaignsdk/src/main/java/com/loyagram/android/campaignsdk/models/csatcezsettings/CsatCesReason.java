package com.loyagram.android.campaignsdk.models.csatcezsettings;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user1 on 23/6/17.
 */

public class CsatCesReason implements Parcelable {
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

    protected CsatCesReason(Parcel in) {
        this.disabled = in.readByte() != 0;
        this.message = in.readString();
    }


    public static final Creator<CsatCesReason> CREATOR = new Creator<CsatCesReason>() {
        @Override
        public CsatCesReason createFromParcel(Parcel in) {
            return new CsatCesReason(in);
        }

        @Override
        public CsatCesReason[] newArray(int size) {
            return new CsatCesReason[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(disabled ? (byte) 1 : (byte) 0);
        dest.writeString(this.message);
    }
}
