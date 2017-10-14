package com.loyagram.android.campaignsdk.models.npssettings;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user1 on 21/4/17.
 */

public class Widget implements Parcelable {

    @SerializedName("very_likely")
    private String veryLikely = null;
    @SerializedName("not_likely")
    private String notLikely = null;

    public String getVeryLikely() {
        return veryLikely;
    }

    public String getNotLikely() {
        return notLikely;
    }

    public void setVeryLikely(String veryLikely) {
        this.veryLikely = veryLikely;
    }

    public void setNotLikely(String notLikely) {
        this.notLikely = notLikely;
    }

    protected Widget(Parcel in) {
        this.veryLikely = in.readString();
        this.notLikely = in.readString();
    }

    public static final Creator<Widget> CREATOR = new Creator<Widget>() {
        @Override
        public Widget createFromParcel(Parcel in) {
            return new Widget(in);
        }

        @Override
        public Widget[] newArray(int size) {
            return new Widget[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.veryLikely);
        dest.writeString(this.notLikely);
    }
}
