package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by renju on 28/3/16.
 */
public class PagingResult implements Parcelable {

    @SerializedName("next")
    private PagingModel next = null;
    @SerializedName("prev")
    private PagingModel prev = null;


    /**
     **/

    public PagingModel getNext() {
        return next;
    }
    public void setNext(PagingModel next) {
        this.next = next;
    }


    /**
     **/

    public PagingModel getPrev() {
        return prev;
    }
    public void setPrev(PagingModel prev) {
        this.prev = prev;
    }



    public PagingResult(){}

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PagingResult> CREATOR = new Parcelable.Creator<PagingResult>() {
        @Override
        public PagingResult createFromParcel(Parcel in) {
            return new PagingResult(in);
        }

        @Override
        public PagingResult[] newArray(int size) {
            return new PagingResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {









        dest.writeParcelable(this.next, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);








        dest.writeParcelable(this.prev, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
    }

    public PagingResult(Parcel in) {
        boolean isPresent = false;











        this.next = in.readParcelable(PagingModel.class.getClassLoader());










        this.prev = in.readParcelable(PagingModel.class.getClassLoader());
    }


    @Override
    public String toString()  {
        StringBuilder sb = new StringBuilder();
        sb.append("class PagingResult {\n");

        sb.append("  next: ").append(next).append("\n");
        sb.append("  prev: ").append(prev).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
