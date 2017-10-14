package com.loyagram.android.campaignsdk.models.npssettings;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by renju on 18/10/16.
 */
public class ThankYouAndRedirectSetting implements Parcelable {
    @SerializedName("disabled")
    private boolean disabled = false;
    @SerializedName("message")
    private String message = "";

    public ThankYouAndRedirectSetting() {
    }

    @SerializedName("links")
    private ArrayList<Link> links = new ArrayList<>();

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Link> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(disabled ? (byte) 1 : (byte) 0);
        dest.writeString(this.message);
        dest.writeList(this.links);
    }

    protected ThankYouAndRedirectSetting(Parcel in) {
        this.disabled = in.readByte() != 0;
        this.message = in.readString();
        this.links = new ArrayList<Link>();
        in.readList(this.links, Link.class.getClassLoader());
    }

    public static final Parcelable.Creator<ThankYouAndRedirectSetting> CREATOR = new Parcelable.Creator<ThankYouAndRedirectSetting>() {
        @Override
        public ThankYouAndRedirectSetting createFromParcel(Parcel source) {
            return new ThankYouAndRedirectSetting(source);
        }

        @Override
        public ThankYouAndRedirectSetting[] newArray(int size) {
            return new ThankYouAndRedirectSetting[size];
        }
    };
}
