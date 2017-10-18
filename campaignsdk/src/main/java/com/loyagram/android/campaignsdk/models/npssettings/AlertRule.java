package com.loyagram.android.campaignsdk.models.npssettings;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by renju on 18/10/16.
 */
public class AlertRule implements Parcelable {
    @SerializedName("customer_type")
    private String customerType = "";
    @SerializedName("emailss")
    private String emails = "";
    @SerializedName("disabled")
    private boolean disabled = false;

    public AlertRule(String customerType, String emails, boolean disabled) {
        this.customerType = customerType;
        this.emails = emails;
        this.disabled = disabled;
    }

    public AlertRule() {
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.customerType);
        dest.writeString(this.emails);
        dest.writeByte(disabled ? (byte) 1 : (byte) 0);
    }

    protected AlertRule(Parcel in) {
        this.customerType = in.readString();
        this.emails = in.readString();
        this.disabled = in.readByte() != 0;
    }

    public static final Parcelable.Creator<AlertRule> CREATOR = new Parcelable.Creator<AlertRule>() {
        @Override
        public AlertRule createFromParcel(Parcel source) {
            return new AlertRule(source);
        }

        @Override
        public AlertRule[] newArray(int size) {
            return new AlertRule[size];
        }
    };
}
