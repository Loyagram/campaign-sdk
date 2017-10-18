package com.loyagram.android.campaignsdk.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class MetaResult implements Parcelable {

    @SerializedName("server_mesage")
    private String serverMesage = null;


    /**
     **/

    public String getServerMesage() {
        return serverMesage;
    }

    public void setServerMesage(String serverMesage) {
        this.serverMesage = serverMesage;
    }


    public MetaResult() {
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MetaResult> CREATOR = new Parcelable.Creator<MetaResult>() {
        @Override
        public MetaResult createFromParcel(Parcel in) {
            return new MetaResult(in);
        }

        @Override
        public MetaResult[] newArray(int size) {
            return new MetaResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        if (this.serverMesage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.serverMesage);
        }


    }

    public MetaResult(Parcel in) {
        boolean isPresent = false;


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.serverMesage = in.readString();
        } else {
            this.serverMesage = null;
        }


    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MetaResult {\n");

        sb.append("  serverMesage: ").append(serverMesage).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
