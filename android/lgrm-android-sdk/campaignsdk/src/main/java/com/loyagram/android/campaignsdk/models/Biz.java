package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Biz implements Parcelable {

    @SerializedName("id")
    private BigDecimal id = null;

    @SerializedName("name")
    private String bizName = null;
    @SerializedName("number")
    private String bizNumber = null;
    @SerializedName("email")
    private String bizEmail = null;
    @SerializedName("img_url")
    private String imgUrl = null;
    @SerializedName("country_name")
    private String countryName = null;
    @SerializedName("country_code")
    private String countryCode = null;
    @SerializedName("country_dial_code")
    private String countryDialCode = null;
    @SerializedName("created_at")
    private Date createdAt = null;
    @SerializedName("retention_enabled")
    private Boolean retentionEnabled = null;
    @SerializedName("feedback_enabled")
    private Boolean feedbackEnabled = null;
    @SerializedName("locations")
    private List<Location> locations = null;


    /**
     **/

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }


    /**
     **/

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }


    /**
     **/

    public String getBizNumber() {
        return bizNumber;
    }

    public void setBizNumber(String bizNumber) {
        this.bizNumber = bizNumber;
    }


    /**
     **/

    public String getBizEmail() {
        return bizEmail;
    }

    public void setBizEmail(String bizEmail) {
        this.bizEmail = bizEmail;
    }


    /**
     **/

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    /**
     **/

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }


    /**
     **/

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    /**
     **/

    public String getCountryDialCode() {
        return countryDialCode;
    }

    public void setCountryDialCode(String countryDialCode) {
        this.countryDialCode = countryDialCode;
    }


    /**
     **/

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    /**
     **/

    public Boolean getRetentionEnabled() {
        return retentionEnabled;
    }

    public void setRetentionEnabled(Boolean retentionEnabled) {
        this.retentionEnabled = retentionEnabled;
    }


    /**
     **/

    public Boolean getFeedbackEnabled() {
        return feedbackEnabled;
    }

    public void setFeedbackEnabled(Boolean feedbackEnabled) {
        this.feedbackEnabled = feedbackEnabled;
    }


    /**
     **/

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }


    public Biz() {
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Biz> CREATOR = new Parcelable.Creator<Biz>() {
        @Override
        public Biz createFromParcel(Parcel in) {
            return new Biz(in);
        }

        @Override
        public Biz[] newArray(int size) {
            return new Biz[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        if (this.id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.id);
        }


        if (this.bizName == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.bizName);
        }


        if (this.bizNumber == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.bizNumber);
        }


        if (this.bizEmail == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.bizEmail);
        }


        if (this.imgUrl == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.imgUrl);
        }


        if (this.countryName == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.countryName);
        }


        if (this.countryCode == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.countryCode);
        }


        if (this.countryDialCode == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.countryDialCode);
        }


        if (this.createdAt == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(this.createdAt.getTime());
        }


        dest.writeValue(this.retentionEnabled);


        dest.writeValue(this.feedbackEnabled);


        if (this.locations == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(this.locations);
        }

    }

    public Biz(Parcel in) {
        boolean isPresent = false;


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.id = (BigDecimal) in.readSerializable();
        } else {
            this.id = null;
        }





        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.bizName = in.readString();
        } else {
            this.bizName = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.bizNumber = in.readString();
        } else {
            this.bizNumber = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.bizEmail = in.readString();
        } else {
            this.bizEmail = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.imgUrl = in.readString();
        } else {
            this.imgUrl = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.countryName = in.readString();
        } else {
            this.countryName = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.countryCode = in.readString();
        } else {
            this.countryCode = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.countryDialCode = in.readString();
        } else {
            this.countryDialCode = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.createdAt = new Date(in.readLong());
        } else {
            this.createdAt = null;
        }


        retentionEnabled = (Boolean) in.readValue(Boolean.class.getClassLoader());


        feedbackEnabled = (Boolean) in.readValue(Boolean.class.getClassLoader());


        if (in.readByte() == 0x01) {
            this.locations = new ArrayList<Location>();
            in.readList(this.locations, Location.class.getClassLoader());
        } else {
            this.locations = new ArrayList<Location>();
        }

    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Biz {\n");

        sb.append("  id: ").append(id).append("\n");

        sb.append("  bizName: ").append(bizName).append("\n");
        sb.append("  bizNumber: ").append(bizNumber).append("\n");
        sb.append("  bizEmail: ").append(bizEmail).append("\n");
        sb.append("  imgUrl: ").append(imgUrl).append("\n");
        sb.append("  countryName: ").append(countryName).append("\n");
        sb.append("  countryCode: ").append(countryCode).append("\n");
        sb.append("  countryDialCode: ").append(countryDialCode).append("\n");
        sb.append("  createdAt: ").append(createdAt).append("\n");
        sb.append("  retentionEnabled: ").append(retentionEnabled).append("\n");
        sb.append("  feedbackEnabled: ").append(feedbackEnabled).append("\n");
        sb.append("  locations: ").append(locations).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
