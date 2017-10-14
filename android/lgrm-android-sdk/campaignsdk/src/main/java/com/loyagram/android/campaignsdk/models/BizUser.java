package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BizUser implements Parcelable {

    @SerializedName("id")
    private BigDecimal id = null;
    @SerializedName("username")
    private String username = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("email")
    private String bizUserEmail = null;
    @SerializedName("number")
    private String bizUserNumber = null;
    @SerializedName("current_biz_id")
    private BigDecimal currentBizId = null;
    @SerializedName("current_loc_id")
    private BigDecimal currentLocId = null;
    @SerializedName("password")
    private String password = null;
    @SerializedName("role")
    private String role = null;
    @SerializedName("registered_at")
    private Date registeredAt = null;
    @SerializedName("pin")
    private String pin = null;
    @SerializedName("device_id")
    private String deviceId = null;
    @SerializedName("bizs")
    private List<Biz> bizs = null;


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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    /**
     **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     **/

    public String getBizUserEmail() {
        return bizUserEmail;
    }

    public void setBizUserEmail(String bizUserEmail) {
        this.bizUserEmail = bizUserEmail;
    }


    /**
     **/

    public String getBizUserNumber() {
        return bizUserNumber;
    }

    public void setBizUserNumber(String bizUserNumber) {
        this.bizUserNumber = bizUserNumber;
    }


    /**
     **/

    public BigDecimal getCurrentBizId() {
        return currentBizId;
    }

    public void setCurrentBizId(BigDecimal currentBizId) {
        this.currentBizId = currentBizId;
    }


    /**
     **/

    public BigDecimal getCurrentLocId() {
        return currentLocId;
    }

    public void setCurrentLocId(BigDecimal currentLocId) {
        this.currentLocId = currentLocId;
    }


    /**
     **/

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /**
     **/

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    /**
     **/

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }


    /**
     **/

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }


    /**
     **/

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }


    /**
     **/

    public List<Biz> getBizs() {
        return bizs;
    }

    public void setBizs(List<Biz> bizs) {
        this.bizs = bizs;
    }


    public BizUser() {
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<BizUser> CREATOR = new Parcelable.Creator<BizUser>() {
        @Override
        public BizUser createFromParcel(Parcel in) {
            return new BizUser(in);
        }

        @Override
        public BizUser[] newArray(int size) {
            return new BizUser[size];
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


        if (this.username == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.username);
        }


        if (this.name == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.name);
        }


        if (this.bizUserEmail == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.bizUserEmail);
        }


        if (this.bizUserNumber == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.bizUserNumber);
        }


        if (this.currentBizId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.currentBizId);
        }


        if (this.currentLocId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.currentLocId);
        }


        if (this.password == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.password);
        }


        if (this.role == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.role);
        }


        if (this.registeredAt == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(this.registeredAt.getTime());
        }


        if (this.pin == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.pin);
        }


        if (this.deviceId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.deviceId);
        }


        if (this.bizs == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(this.bizs);
        }

    }

    public BizUser(Parcel in) {
        boolean isPresent = false;


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.id = (BigDecimal) in.readSerializable();
        } else {
            this.id = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.username = in.readString();
        } else {
            this.username = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.name = in.readString();
        } else {
            this.name = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.bizUserEmail = in.readString();
        } else {
            this.bizUserEmail = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.bizUserNumber = in.readString();
        } else {
            this.bizUserNumber = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.currentBizId = (BigDecimal) in.readSerializable();
        } else {
            this.currentBizId = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.currentLocId = (BigDecimal) in.readSerializable();
        } else {
            this.currentLocId = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.password = in.readString();
        } else {
            this.password = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.role = in.readString();
        } else {
            this.role = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.registeredAt = new Date(in.readLong());
        } else {
            this.registeredAt = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.pin = in.readString();
        } else {
            this.pin = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.deviceId = in.readString();
        } else {
            this.deviceId = null;
        }


        if (in.readByte() == 0x01) {
            this.bizs = new ArrayList<Biz>();
            in.readList(this.bizs, Biz.class.getClassLoader());
        } else {
            this.bizs = new ArrayList<Biz>();
        }

    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BizUser {\n");

        sb.append("  id: ").append(id).append("\n");
        sb.append("  username: ").append(username).append("\n");
        sb.append("  name: ").append(name).append("\n");
        sb.append("  bizUserEmail: ").append(bizUserEmail).append("\n");
        sb.append("  bizUserNumber: ").append(bizUserNumber).append("\n");
        sb.append("  currentBizId: ").append(currentBizId).append("\n");
        sb.append("  currentLocId: ").append(currentLocId).append("\n");
        sb.append("  password: ").append(password).append("\n");
        sb.append("  role: ").append(role).append("\n");
        sb.append("  registeredAt: ").append(registeredAt).append("\n");
        sb.append("  pin: ").append(pin).append("\n");
        sb.append("  deviceId: ").append(deviceId).append("\n");
        sb.append("  bizs: ").append(bizs).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
