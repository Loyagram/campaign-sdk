package com.loyagram.android.campaignsdk.models.csatcezsettings;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user1 on 22/6/17.
 */

public class CsatCesRules implements Parcelable {
    @SerializedName("customer_type")
    private String customerType = "";
    @SerializedName("emailss")
    private String emails = "";

    public String getCustomerType() {
        return customerType;
    }

    public String getEmails() {
        return emails;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    protected CsatCesRules(Parcel in) {
        this.customerType = in.readString();
        this.emails = in.readString();
    }

    public static final Creator<CsatCesRules> CREATOR = new Creator<CsatCesRules>() {
        @Override
        public CsatCesRules createFromParcel(Parcel in) {
            return new CsatCesRules(in);
        }

        @Override
        public CsatCesRules[] newArray(int size) {
            return new CsatCesRules[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.customerType);
        dest.writeString(this.emails);
    }
}
