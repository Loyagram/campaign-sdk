package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by renju on 28/3/16.
 */
public class WarningModel implements Parcelable {

    @SerializedName("msg")
    private String msg = null;
    @SerializedName("field")
    private String field = null;
    @SerializedName("type")
    private String type = null;


    /**
     * Msg show to user
     **/

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }


    /**
     * if an input error from user
     **/

    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }


    /**
     * Nature of error, like EXISTS, NOT_VALID
     **/

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }



    public WarningModel(){}

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<WarningModel> CREATOR = new Parcelable.Creator<WarningModel>() {
        @Override
        public WarningModel createFromParcel(Parcel in) {
            return new WarningModel(in);
        }

        @Override
        public WarningModel[] newArray(int size) {
            return new WarningModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {



        if(this.msg == null)
        {
            dest.writeByte((byte)0);
        }
        else{
            dest.writeByte((byte)1);
            dest.writeString(this.msg);
        }












        if(this.field == null)
        {
            dest.writeByte((byte)0);
        }
        else{
            dest.writeByte((byte)1);
            dest.writeString(this.field);
        }












        if(this.type == null)
        {
            dest.writeByte((byte)0);
        }
        else{
            dest.writeByte((byte)1);
            dest.writeString(this.type);
        }










    }

    public WarningModel(Parcel in) {
        boolean isPresent = false;




        isPresent = in.readByte() == 1;
        if(isPresent){
            this.msg = in.readString();
        }
        else{
            this.msg = null;
        }











        isPresent = in.readByte() == 1;
        if(isPresent){
            this.field = in.readString();
        }
        else{
            this.field = null;
        }











        isPresent = in.readByte() == 1;
        if(isPresent){
            this.type = in.readString();
        }
        else{
            this.type = null;
        }








    }


    @Override
    public String toString()  {
        StringBuilder sb = new StringBuilder();
        sb.append("class Warning {\n");

        sb.append("  msg: ").append(msg).append("\n");
        sb.append("  field: ").append(field).append("\n");
        sb.append("  type: ").append(type).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}