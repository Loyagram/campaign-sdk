package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 * Created by user1 on 19/4/17.
 */

public class LabelTranslation implements Parcelable {

    @SerializedName("l_id")
    private BigDecimal labelId = null;
    @SerializedName("language_code")
    private String code = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("text")
    private String translation = null;

    protected LabelTranslation(Parcel in) {
        boolean isPresent = false;

        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.labelId = (BigDecimal) in.readSerializable();
        } else {
            this.labelId = null;
        }

        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.code = in.readString();
        } else {
            this.code = null;
        }

        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.name = in.readString();
        } else {
            this.name = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.translation = in.readString();
        } else {
            this.translation = null;
        }
    }

    public BigDecimal getLabelId() {
        return labelId;
    }

    public void setLabelId(BigDecimal labelId) {
        this.labelId = labelId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    public static final Creator<LabelTranslation> CREATOR = new Creator<LabelTranslation>() {
        @Override
        public LabelTranslation createFromParcel(Parcel in) {
            return new LabelTranslation(in);
        }

        @Override
        public LabelTranslation[] newArray(int size) {
            return new LabelTranslation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        if (this.labelId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.labelId);
        }

        if (this.code == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.code);
        }

        if (this.name == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.name);
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
        sb.append("class LabelTranslation {\n");

        sb.append("  labelId: ").append(labelId).append("\n");
        sb.append("  code: ").append(code).append("\n");
        sb.append("  name: ").append(name).append("\n");
        sb.append("  translation: ").append(translation).append("\n");
        sb.append("}\n");
        return sb.toString();
    }


}
