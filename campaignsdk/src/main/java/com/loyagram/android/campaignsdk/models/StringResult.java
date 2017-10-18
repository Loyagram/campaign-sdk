package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class StringResult extends BaseResult implements Parcelable {

    @SerializedName("results")
    private String results = null;

    /**
     **/

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }


    public StringResult() {
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<StringResult> CREATOR = new Parcelable.Creator<StringResult>() {
        @Override
        public StringResult createFromParcel(Parcel in) {
            return new StringResult(in);
        }

        @Override
        public StringResult[] newArray(int size) {
            return new StringResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

        if (this.results == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.results);
        }
    }

    public StringResult(Parcel in) {
        super(in);
        boolean isPresent = false;


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.results = in.readString();
        } else {
            this.results = null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StringResult {\n");
        sb.append("  results: ").append(results).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
