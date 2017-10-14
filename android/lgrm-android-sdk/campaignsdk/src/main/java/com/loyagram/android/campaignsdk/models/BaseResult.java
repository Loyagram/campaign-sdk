package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renju on 28/3/16.
 */
public class BaseResult implements Parcelable {
    @SerializedName("id")
    protected String id;
    @SerializedName("errors")
    protected List<ErrorModel> errors = null;
    @SerializedName("warnings")
    protected List<WarningModel> warnings = null;
    @SerializedName("status")
    protected String status = null;
    @SerializedName("paging")
    protected PagingResult paging = null;
    @SerializedName("session")
    protected SessionResult session = null;


    /**
     **/

    public List<ErrorModel> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorModel> errors) {
        this.errors = errors;
    }


    /**
     **/

    public List<WarningModel> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<WarningModel> warnings) {
        this.warnings = warnings;
    }


    /**
     **/

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public PagingResult getPaging() {
        return paging;
    }

    public void setPaging(PagingResult paging) {
        this.paging = paging;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SessionResult getSession() {
        return session;
    }

    public void setSession(SessionResult session) {
        this.session = session;
    }

    public BaseResult() {
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<BaseResult> CREATOR = new Parcelable.Creator<BaseResult>() {
        @Override
        public BaseResult createFromParcel(Parcel in) {
            return new BaseResult(in);
        }

        @Override
        public BaseResult[] newArray(int size) {
            return new BaseResult[size];
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
        if (this.errors == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(this.errors);
        }


        if (this.warnings == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(this.warnings);
        }


        if (this.status == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.status);
        }


        dest.writeParcelable(this.paging, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
        dest.writeParcelable(this.session, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
    }

    public BaseResult(Parcel in) {


        if (in.readByte() == 0x01) {
            this.id = in.readString();
        } else {
            this.id = null;
        }

        if (in.readByte() == 0x01) {
            this.errors = new ArrayList<ErrorModel>();
            in.readList(this.errors, Error.class.getClassLoader());
        } else {
            this.errors = new ArrayList<ErrorModel>();
        }


        if (in.readByte() == 0x01) {
            this.warnings = new ArrayList<WarningModel>();
            in.readList(this.warnings, WarningModel.class.getClassLoader());
        } else {
            this.warnings = new ArrayList<WarningModel>();
        }


        if (in.readByte() == 0x01) {
            this.status = in.readString();
        } else {
            this.status = null;
        }


        this.paging = in.readParcelable(PagingResult.class.getClassLoader());

        this.session = in.readParcelable(SessionResult.class.getClassLoader());
    }


//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("class BaseResult {\n");
//
//        sb.append("  errors: ").append(errors).append("\n");
//        sb.append("  warnings: ").append(warnings).append("\n");
//        sb.append("  status: ").append(status).append("\n");
//
//        sb.append("  paging: ").append(paging).append("\n");
//        sb.append("}\n");
//        return sb.toString();
//    }

}
