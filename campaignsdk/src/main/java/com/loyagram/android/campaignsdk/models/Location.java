package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Location implements Parcelable {

    @SerializedName("id")
    private BigDecimal id = null;
    @SerializedName("biz_id")
    private BigDecimal bizId = null;
    @SerializedName("location")
    private String location = null;
    @SerializedName("biz_loc_number")
    private String bizLocNumber = null;
    @SerializedName("biz_loc_email")
    private String bizLocEmail = null;
    @SerializedName("biz_loc_address")
    private String bizLocAddress = null;
    @SerializedName("lat")
    private BigDecimal lat = null;
    @SerializedName("lng")
    private BigDecimal lng = null;
    @SerializedName("roles")
    private BizRole roles = null;
    @SerializedName("managers")
    private List<BizUser> managers = null;


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

    public BigDecimal getBizId() {
        return bizId;
    }

    public void setBizId(BigDecimal bizId) {
        this.bizId = bizId;
    }


    /**
     **/

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    /**
     **/

    public String getBizLocNumber() {
        return bizLocNumber;
    }

    public void setBizLocNumber(String bizLocNumber) {
        this.bizLocNumber = bizLocNumber;
    }


    /**
     **/

    public String getBizLocEmail() {
        return bizLocEmail;
    }

    public void setBizLocEmail(String bizLocEmail) {
        this.bizLocEmail = bizLocEmail;
    }


    /**
     **/

    public String getBizLocAddress() {
        return bizLocAddress;
    }

    public void setBizLocAddress(String bizLocAddress) {
        this.bizLocAddress = bizLocAddress;
    }


    /**
     **/

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }


    /**
     **/

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }


    /**
     **/

    public BizRole getRoles() {
        return roles;
    }

    public void setRoles(BizRole roles) {
        this.roles = roles;
    }


    /**
     **/

    public List<BizUser> getManagers() {
        return managers;
    }

    public void setManagers(List<BizUser> managers) {
        this.managers = managers;
    }


    public Location() {
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
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


        if (this.bizId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.bizId);
        }


        if (this.location == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.location);
        }


        if (this.bizLocNumber == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.bizLocNumber);
        }


        if (this.bizLocEmail == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.bizLocEmail);
        }


        if (this.bizLocAddress == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.bizLocAddress);
        }


        if (this.lat == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.lat);
        }


        if (this.lng == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.lng);
        }


        dest.writeParcelable(this.roles, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);


        if (this.managers == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(this.managers);
        }

    }

    public Location(Parcel in) {
        boolean isPresent = false;


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.id = (BigDecimal) in.readSerializable();
        } else {
            this.id = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.bizId = (BigDecimal) in.readSerializable();
        } else {
            this.bizId = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.location = in.readString();
        } else {
            this.location = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.bizLocNumber = in.readString();
        } else {
            this.bizLocNumber = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.bizLocEmail = in.readString();
        } else {
            this.bizLocEmail = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.bizLocAddress = in.readString();
        } else {
            this.bizLocAddress = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.lat = (BigDecimal) in.readSerializable();
        } else {
            this.lat = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.lng = (BigDecimal) in.readSerializable();
        } else {
            this.lng = null;
        }


        this.roles = in.readParcelable(BizRole.class.getClassLoader());


        if (in.readByte() == 0x01) {
            this.managers = new ArrayList<BizUser>();
            in.readList(this.managers, BizUser.class.getClassLoader());
        } else {
            this.managers = new ArrayList<BizUser>();
        }

    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Location {\n");

        sb.append("  id: ").append(id).append("\n");
        sb.append("  bizId: ").append(bizId).append("\n");
        sb.append("  location: ").append(location).append("\n");
        sb.append("  bizLocNumber: ").append(bizLocNumber).append("\n");
        sb.append("  bizLocEmail: ").append(bizLocEmail).append("\n");
        sb.append("  bizLocAddress: ").append(bizLocAddress).append("\n");

        sb.append("  lat: ").append(lat).append("\n");
        sb.append("  lng: ").append(lng).append("\n");
        sb.append("  roles: ").append(roles).append("\n");
        sb.append("  managers: ").append(managers).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
