package com.loyagram.android.campaignsdk.models.npssettings;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.loyagram.android.campaignsdk.models.csatcezsettings.CsatCesSettings;

/**
 * Created by renju on 18/10/16.
 */
public class Settings implements Parcelable {
    @SerializedName("nps_settings")
    private NpsSettings npsSettings = null;
    @SerializedName("csat_settings")
    private CsatCesSettings csatSettings = null;
    @SerializedName("ces_settings")
    private CsatCesSettings cesSettings = null;

    public Settings() {
    }

    public NpsSettings getNpsSettings() {
        return npsSettings;
    }

    public void setNpsSettings(NpsSettings npsSettings) {
        this.npsSettings = npsSettings;
    }


    public CsatCesSettings getCsatSettings() {
        return csatSettings;
    }

    public CsatCesSettings getCesSettings() {
        return cesSettings;
    }

    public void setCsatSettings(CsatCesSettings csatSettings) {
        this.csatSettings = csatSettings;
    }

    public void setCesSettings(CsatCesSettings cesSettings) {
        this.cesSettings = cesSettings;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.npsSettings, flags);
        dest.writeParcelable(this.cesSettings, flags);
        dest.writeParcelable(this.csatSettings, flags);
    }

    protected Settings(Parcel in) {
        this.npsSettings = in.readParcelable(NpsSettings.class.getClassLoader());
        this.cesSettings = in.readParcelable(CsatCesSettings.class.getClassLoader());
        this.csatSettings = in.readParcelable(CsatCesSettings.class.getClassLoader());
    }

    public static final Parcelable.Creator<Settings> CREATOR = new Parcelable.Creator<Settings>() {
        @Override
        public Settings createFromParcel(Parcel source) {
            return new Settings(source);
        }

        @Override
        public Settings[] newArray(int size) {
            return new Settings[size];
        }
    };
}
