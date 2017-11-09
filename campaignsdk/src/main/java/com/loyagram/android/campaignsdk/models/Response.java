package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class Response implements Parcelable {

    @SerializedName("id")
    private String id = null;
    @SerializedName("biz_id")
    private BigDecimal bizId = null;
    @SerializedName("location_id")
    private BigDecimal locationId = null;
    @SerializedName("user_id")
    private BigDecimal userId = null;
    @SerializedName("campaign_id")
    private BigDecimal campaignId = null;
    @SerializedName("customer_number")
    private String customerNumber = null;
    @SerializedName("customer_email")
    private String customerEmail = null;
    @SerializedName("customer_name")
    private String customerName = null;
    @SerializedName("customer_address")
    private String customerAddress = null;
    @SerializedName("started_at")
    private long startedAt = 0;
    @SerializedName("ended_at")
    private long endedAt = 0;
    @SerializedName("response_answers")
    private List<ResponseAnswer> responseAnswers = null;
    @SerializedName("device_id")
    private String deviceId = null;
    @SerializedName("channel")
    private String channel = null;
    @SerializedName("sub_channel")
    private String subChannel = null;
    @SerializedName("language_code")
    private String language = null;
    @SerializedName("attr")
    private HashMap<String, String> attributes = null;
    @SerializedName("email")
    private String email = null;


    /**
     **/

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }


    /**
     **/

    public BigDecimal getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(BigDecimal campaignId) {
        this.campaignId = campaignId;
    }


    /**
     **/

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }


    /**
     **/

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }


    /**
     **/

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    /**
     **/

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }


    /**
     **/

    public long getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(long startedAt) {
        this.startedAt = startedAt;
    }


    /**
     **/

    public long getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(long endedAt) {
        this.endedAt = endedAt;
    }


    /**
     **/

    public List<ResponseAnswer> getResponseAnswers() {
        return responseAnswers;
    }

    public void setResponseAnswers(List<ResponseAnswer> responseAnswers) {
        this.responseAnswers = responseAnswers;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     **/

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSubChannel() {
        return subChannel;
    }

    public void setSubChannel(String subChannel) {
        this.subChannel = subChannel;
    }

    public BigDecimal getLocationId() {
        return locationId;
    }

    public void setLocationId(BigDecimal locationId) {
        this.locationId = locationId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }

    public Response() {
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Response> CREATOR = new Parcelable.Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel in) {
            return new Response(in);
        }

        @Override
        public Response[] newArray(int size) {
            return new Response[size];
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
            dest.writeString(this.id);
        }


        if (this.bizId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.bizId);
        }


        if (this.locationId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.locationId);
        }


        if (this.userId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.userId);
        }


        if (this.campaignId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.campaignId);
        }


        if (this.customerNumber == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.customerNumber);
        }


        if (this.customerEmail == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.customerEmail);
        }


        if (this.customerName == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.customerName);
        }


        if (this.customerAddress == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.customerAddress);
        }


        if (this.startedAt == 0) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(this.startedAt);
        }


        if (this.endedAt == 0) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(this.endedAt);
        }


        if (this.responseAnswers == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(this.responseAnswers);
        }


        if (this.deviceId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.deviceId);
        }

        if (this.channel == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.channel);
        }

        if (this.subChannel == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.subChannel);
        }

        if (this.language == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.language);
        }

        dest.writeInt(this.attributes.size());

        for (HashMap.Entry<String, String> entry : this.attributes.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }

        if (this.email == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.email);
        }
    }

    public Response(Parcel in) {
        boolean isPresent = false;


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.id = in.readString();
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
            this.locationId = (BigDecimal) in.readSerializable();
        } else {
            this.locationId = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.userId = (BigDecimal) in.readSerializable();
        } else {
            this.userId = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.campaignId = (BigDecimal) in.readSerializable();
        } else {
            this.campaignId = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.customerNumber = in.readString();
        } else {
            this.customerNumber = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.customerEmail = in.readString();
        } else {
            this.customerEmail = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.customerName = in.readString();
        } else {
            this.customerName = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.customerAddress = in.readString();
        } else {
            this.customerAddress = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.startedAt = in.readLong();
        } else {
            this.startedAt = 0;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.endedAt = in.readLong();
        } else {
            this.endedAt = 0;
        }


        if (in.readByte() == 0x01) {
            this.responseAnswers = new ArrayList<ResponseAnswer>();
            in.readList(this.responseAnswers, ResponseAnswer.class.getClassLoader());
        } else {
            this.responseAnswers = new ArrayList<ResponseAnswer>();
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.deviceId = in.readString();
        } else {
            this.deviceId = null;
        }

        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.channel = in.readString();
        } else {
            this.channel = null;
        }

        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.subChannel = in.readString();
        } else {
            this.subChannel = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.language = in.readString();
        } else {
            this.language = null;
        }

        final int size = in.readInt();

        for (int i = 0; i < size; i++) {
            final String key = in.readString();
            final String value = in.readString();
            this.attributes.put(key, value);
        }

        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.email = in.readString();
        } else {
            this.email = null;
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Response {\n");

        sb.append("  id: ").append(id).append("\n");
        sb.append("  bizId: ").append(bizId).append("\n");
        sb.append("  bizLocId: ").append(locationId).append("\n");
        sb.append("  userId: ").append(userId).append("\n");
        sb.append("  campaignId: ").append(campaignId).append("\n");
        sb.append("  customerNumber: ").append(customerNumber).append("\n");
        sb.append("  customerEmail: ").append(customerEmail).append("\n");
        sb.append("  customerName: ").append(customerName).append("\n");
        sb.append("  customerAddress: ").append(customerAddress).append("\n");
        sb.append("  startedAt: ").append(startedAt).append("\n");
        sb.append("  endedAt: ").append(endedAt).append("\n");
        sb.append("  responseAnswers: ").append(responseAnswers).append("\n");
        sb.append("  deviceId: ").append(deviceId).append("\n");
        sb.append("  channel: ").append(channel).append("\n");
        sb.append("  subChannel: ").append(subChannel).append("\n");
        sb.append("  language: ").append(language).append("\n");
        sb.append("  email: ").append(email).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
