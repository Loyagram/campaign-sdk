package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user1 on 8/6/17.
 */


public class StaticTextTransalation implements Parcelable {

    @SerializedName("static_text_id")
    private String staticTextId = null;
    @SerializedName("lang")
    private String code = null;
    @SerializedName("text")
    private String translation = null;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    public String getStaticTextId() {
        return staticTextId;
    }

    public void setStaticTextId(String staticTextId) {
        this.staticTextId = staticTextId;
    }

    protected StaticTextTransalation(Parcel in) {

        boolean isPresent = false;

        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.staticTextId = in.readString();
        } else {
            this.staticTextId = null;
        }

        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.code = in.readString();
        } else {
            this.code = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.translation = in.readString();
        } else {
            this.translation = null;
        }
    }

    public static final Creator<StaticTextTransalation> CREATOR = new Creator<StaticTextTransalation>() {
        @Override
        public StaticTextTransalation createFromParcel(Parcel in) {
            return new StaticTextTransalation(in);
        }

        @Override
        public StaticTextTransalation[] newArray(int size) {
            return new StaticTextTransalation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        if (this.staticTextId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.staticTextId);
        }

        if (this.code == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.code);
        }

        if (this.translation == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.translation);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StaticTextTranslation {\n");

        sb.append("  staticTextId: ").append(staticTextId).append("\n");
        sb.append("  code: ").append(code).append("\n");
        sb.append("  translation: ").append(translation).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
