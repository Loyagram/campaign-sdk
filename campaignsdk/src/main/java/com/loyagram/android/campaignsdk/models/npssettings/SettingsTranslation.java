package com.loyagram.android.campaignsdk.models.npssettings;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user1 on 20/4/17.
 */

public class SettingsTranslation implements Parcelable {
    @SerializedName("language_code")
    private String code = null;
    @SerializedName("text")
    private SettingsBase settingsBase = null;

    protected SettingsTranslation(Parcel in) {
        boolean isPresent = false;

        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.code = in.readString();
        } else {
            this.code = null;
        }

        if (in.readByte() == 0x01) {
            this.settingsBase = new SettingsBase();
            in.readParcelable(SettingsBase.class.getClassLoader());
        } else {
            this.settingsBase = new SettingsBase();
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SettingsBase getSettingsBase() {
        return settingsBase;
    }

    public void setSettingsBase(SettingsBase settingsBase) {
        this.settingsBase = settingsBase;
    }

    public static final Creator<SettingsTranslation> CREATOR = new Creator<SettingsTranslation>() {
        @Override
        public SettingsTranslation createFromParcel(Parcel in) {
            return new SettingsTranslation(in);
        }

        @Override
        public SettingsTranslation[] newArray(int size) {
            return new SettingsTranslation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (this.code == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.code);
        }

        if (this.settingsBase == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeParcelable(this.settingsBase, flags);
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SettingsTranslation {\n");

        sb.append("  code: ").append(code).append("\n");
        sb.append("  settingsBase: ").append(settingsBase).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
