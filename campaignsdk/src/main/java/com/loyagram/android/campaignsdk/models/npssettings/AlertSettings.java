package com.loyagram.android.campaignsdk.models.npssettings;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by renju on 18/10/16.
 */
public class AlertSettings implements Parcelable {
    @SerializedName("disabled")
    private boolean disabled = false;
    @SerializedName("rules")
    private ArrayList<AlertRule> rules = null;

    public AlertSettings() {
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public ArrayList<AlertRule> getRules() {
        return rules;
    }

    public void setRules(ArrayList<AlertRule> rules) {
        this.rules = rules;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(disabled ? (byte) 1 : (byte) 0);
        dest.writeTypedList(rules);
    }

    protected AlertSettings(Parcel in) {
        this.disabled = in.readByte() != 0;
        this.rules = in.createTypedArrayList(AlertRule.CREATOR);
    }

    public static final Parcelable.Creator<AlertSettings> CREATOR = new Parcelable.Creator<AlertSettings>() {
        @Override
        public AlertSettings createFromParcel(Parcel source) {
            return new AlertSettings(source);
        }

        @Override
        public AlertSettings[] newArray(int size) {
            return new AlertSettings[size];
        }
    };
}
