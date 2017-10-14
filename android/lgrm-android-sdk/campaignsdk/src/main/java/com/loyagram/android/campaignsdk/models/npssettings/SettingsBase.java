package com.loyagram.android.campaignsdk.models.npssettings;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user1 on 24/4/17.
 */

public class SettingsBase implements Parcelable {

    @SerializedName("settings")
    private Settings settings = null;


    public SettingsBase() {
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    protected SettingsBase(Parcel in) {
        this.settings = in.readParcelable(Settings.class.getClassLoader());

    }

    public static final Creator<SettingsBase> CREATOR = new Creator<SettingsBase>() {
        @Override
        public SettingsBase createFromParcel(Parcel in) {
            return new SettingsBase(in);
        }

        @Override
        public SettingsBase[] newArray(int size) {
            return new SettingsBase[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.settings, flags);
    }
}
