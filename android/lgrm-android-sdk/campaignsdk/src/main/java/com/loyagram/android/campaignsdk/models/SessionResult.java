package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by renju on 29/3/16.
 */
public class SessionResult implements Parcelable {

    @SerializedName("token")
    private String token = null;


    /**
     **/

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public SessionResult() {
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SessionResult> CREATOR = new Parcelable.Creator<SessionResult>() {
        @Override
        public SessionResult createFromParcel(Parcel in) {
            return new SessionResult(in);
        }

        @Override
        public SessionResult[] newArray(int size) {
            return new SessionResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        if (this.token == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.token);
        }


    }

    public SessionResult(Parcel in) {
        boolean isPresent = false;
        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.token = in.readString();
        } else {
            this.token = null;
        }


    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SessionResult {\n");

        sb.append("  token: ").append(token).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
