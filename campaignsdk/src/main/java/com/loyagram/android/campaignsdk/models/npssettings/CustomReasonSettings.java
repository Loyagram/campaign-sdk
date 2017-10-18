package com.loyagram.android.campaignsdk.models.npssettings;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.loyagram.android.campaignsdk.models.npssettings.ReasonSetting;

/**
 * Created by renju on 18/10/16.
 */
public class CustomReasonSettings implements Parcelable {
    public static final Parcelable.Creator<CustomReasonSettings> CREATOR = new Parcelable.Creator<CustomReasonSettings>() {
        @Override
        public CustomReasonSettings createFromParcel(Parcel source) {
            return new CustomReasonSettings(source);
        }

        @Override
        public CustomReasonSettings[] newArray(int size) {
            return new CustomReasonSettings[size];
        }
    };
    @SerializedName("detractors")
    private ReasonSetting detractors = null;
    @SerializedName("passives")
    private ReasonSetting passives = null;
    @SerializedName("promoters")
    private ReasonSetting promoters = null;

    public CustomReasonSettings() {
    }

    protected CustomReasonSettings(Parcel in) {
        this.detractors = in.readParcelable(ReasonSetting.class.getClassLoader());
        this.passives = in.readParcelable(ReasonSetting.class.getClassLoader());
        this.promoters = in.readParcelable(ReasonSetting.class.getClassLoader());
    }

    public ReasonSetting getDetractors() {
        return detractors;
    }

    public void setDetractors(ReasonSetting detractors) {
        this.detractors = detractors;
    }

    public ReasonSetting getPassives() {
        return passives;
    }

    public void setPassives(ReasonSetting passives) {
        this.passives = passives;
    }

    public ReasonSetting getPromoters() {
        return promoters;
    }

    public void setPromoters(ReasonSetting promoters) {
        this.promoters = promoters;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.detractors, flags);
        dest.writeParcelable(this.passives, flags);
        dest.writeParcelable(this.promoters, flags);
    }
}
