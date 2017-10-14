package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user1 on 19/4/17.
 */

public class Language implements Parcelable {

    @SerializedName("status")
    private String status = null;
    @SerializedName("selected")
    private Boolean selected = null;
    @SerializedName("code")
    private String code = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("primary")
    private Boolean isPrimary = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    protected Language(Parcel in) {
      Boolean  isPresent = false;
        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.status = in.readString();
        } else {
            this.status = null;
        }

        selected = (Boolean) in.readValue(Boolean.class.getClassLoader());

        if (isPresent) {
            this.code = in.readString();
        } else {
            this.code = null;
        }

        if (isPresent) {
            this.name = in.readString();
        } else {
            this.name = null;
        }

        selected = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<Language> CREATOR = new Creator<Language>() {
        @Override
        public Language createFromParcel(Parcel in) {
            return new Language(in);
        }

        @Override
        public Language[] newArray(int size) {
            return new Language[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        if (this.status == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.status);
        }

        dest.writeValue(this.selected);

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

        dest.writeValue(this.selected);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Question {\n");

        sb.append("  status: ").append(status).append("\n");
        sb.append("  selected: ").append(selected).append("\n");
        sb.append("  code: ").append(code).append("\n");
        sb.append("  name: ").append(name).append("\n");
        sb.append("  primary: ").append(isPrimary).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
