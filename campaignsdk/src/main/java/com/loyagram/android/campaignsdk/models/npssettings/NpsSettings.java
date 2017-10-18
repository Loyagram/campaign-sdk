package com.loyagram.android.campaignsdk.models.npssettings;

import android.net.wifi.WifiConfiguration;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by renju on 18/10/16.
 */
public class NpsSettings implements Parcelable {
    @SerializedName("request_reason_settings")
    private RequestReasonSettings requestReasonSettings = null;
    @SerializedName("thank_you_and_redirect_settings")
    private ThankYouAndRedirectSettings thankYouAndRedirectSettings = null;
    @SerializedName("referral_settings")
    private ReferralSettings referralSettings = null;
    @SerializedName("shape")
    private String shape = null;
    @SerializedName("nps_type")
    private String npsType = null;
    @SerializedName("widget")
    private Widget widget = null;

    public RequestReasonSettings getRequestReasonSettings() {
        return requestReasonSettings;
    }

    public void setRequestReasonSettings(RequestReasonSettings requestReasonSettings) {
        this.requestReasonSettings = requestReasonSettings;
    }

    public String getNpsType() {
        return npsType;
    }

    public void setNpsType(String npsType) {
        this.npsType = npsType;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public NpsSettings() {
    }

    public ThankYouAndRedirectSettings getThankYouAndRedirectSettings() {
        return thankYouAndRedirectSettings;
    }

    public void setThankYouAndRedirectSettings(ThankYouAndRedirectSettings thankYouAndRedirectSettings) {
        this.thankYouAndRedirectSettings = thankYouAndRedirectSettings;
    }

    public ReferralSettings getReferralSettings() {
        return referralSettings;
    }

    public void setReferralSettings(ReferralSettings referralSettings) {
        this.referralSettings = referralSettings;
    }

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.requestReasonSettings, flags);
        dest.writeParcelable(this.thankYouAndRedirectSettings, flags);
        dest.writeParcelable(this.referralSettings, flags);
        dest.writeString(this.shape);
        dest.writeString(this.npsType);
        dest.writeParcelable(this.widget, flags);
    }

    protected NpsSettings(Parcel in) {
        this.requestReasonSettings = in.readParcelable(RequestReasonSettings.class.getClassLoader());
        this.thankYouAndRedirectSettings = in.readParcelable(ThankYouAndRedirectSettings.class.getClassLoader());
        this.referralSettings = in.readParcelable(ReferralSettings.class.getClassLoader());
        this.shape = in.readString();
        this.npsType = in.readString();
        this.widget = in.readParcelable(Widget.class.getClassLoader());
    }

    public static final Creator<NpsSettings> CREATOR = new Creator<NpsSettings>() {
        @Override
        public NpsSettings createFromParcel(Parcel source) {
            return new NpsSettings(source);
        }

        @Override
        public NpsSettings[] newArray(int size) {
            return new NpsSettings[size];
        }
    };
}
