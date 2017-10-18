package com.loyagram.android.campaignsdk.models.csatcezsettings;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by user1 on 22/6/17.
 */

public class CsatCesAlertSettings implements Parcelable {

    @SerializedName("type")
    private String type;
    @SerializedName("rules")
    private ArrayList<CsatCesRules> rules = null;

    public String getType() {
        return type;
    }

    public ArrayList<CsatCesRules> getRules() {
        return rules;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRules(ArrayList<CsatCesRules> rules) {
        this.rules = rules;
    }

    protected CsatCesAlertSettings(Parcel in) {
        this.type = in.readString();
        this.rules = in.createTypedArrayList(CsatCesRules.CREATOR);
    }

    public static final Creator<CsatCesAlertSettings> CREATOR = new Creator<CsatCesAlertSettings>() {
        @Override
        public CsatCesAlertSettings createFromParcel(Parcel in) {
            return new CsatCesAlertSettings(in);
        }

        @Override
        public CsatCesAlertSettings[] newArray(int size) {
            return new CsatCesAlertSettings[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeTypedList(rules);
    }
}
