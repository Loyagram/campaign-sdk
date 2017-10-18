package com.loyagram.android.campaignsdk.models.csatcezsettings;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user1 on 22/6/17.
 */

public class CsatCesSettings implements Parcelable {

    @SerializedName("alert_settings")
    private CsatCesAlertSettings csatCesAlertSettings = null;
    @SerializedName("request_reason_settings")
    private CsatCesReasonSettings requestReasonSettings = null;

    public CsatCesReasonSettings getRequestReasonSettings() {
        return requestReasonSettings;
    }

    public void setRequestReasonSettings(CsatCesReasonSettings requestReasonSettings) {
        this.requestReasonSettings = requestReasonSettings;
    }

    public CsatCesAlertSettings getCsatCesAlertSettings() {
        return csatCesAlertSettings;
    }

    public void setCsatCesAlertSettings(CsatCesAlertSettings csatCesAlertSettings) {
        this.csatCesAlertSettings = csatCesAlertSettings;
    }

    protected CsatCesSettings(Parcel in) {
        this.csatCesAlertSettings = in.readParcelable(CsatCesAlertSettings.class.getClassLoader());
        this.requestReasonSettings = in.readParcelable(CsatCesReasonSettings.class.getClassLoader());
    }

    public static final Creator<CsatCesSettings> CREATOR = new Creator<CsatCesSettings>() {
        @Override
        public CsatCesSettings createFromParcel(Parcel in) {
            return new CsatCesSettings(in);
        }

        @Override
        public CsatCesSettings[] newArray(int size) {
            return new CsatCesSettings[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.csatCesAlertSettings, flags);
        dest.writeParcelable(this.requestReasonSettings, flags);
    }
}
