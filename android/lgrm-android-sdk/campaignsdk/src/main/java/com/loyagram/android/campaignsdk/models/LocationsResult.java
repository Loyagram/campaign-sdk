package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class LocationsResult extends BaseResult implements Parcelable {


    @SerializedName("results")
    private List<Location> results = null;


    public List<Location> getResults() {
        return results;
    }

    public void setResults(List<Location> results) {
        this.results = results;
    }


    /**
     **/

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public LocationsResult() {
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<LocationsResult> CREATOR = new Parcelable.Creator<LocationsResult>() {
        @Override
        public LocationsResult createFromParcel(Parcel in) {
            return new LocationsResult(in);
        }

        @Override
        public LocationsResult[] newArray(int size) {
            return new LocationsResult[size];
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
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(this.results);
        }


    }

    public LocationsResult(Parcel in) {
        super(in);

        if (in.readByte() == 0x01) {
            this.results = new ArrayList<Location>();
            in.readList(this.results, Location.class.getClassLoader());
        } else {
            this.results = new ArrayList<Location>();
        }


    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BizLocationsResult {\n");
        sb.append("  " + super.toString()).append("\n");
        sb.append("  session: ").append(session).append("\n");

        sb.append("  warnings: ").append(warnings).append("\n");
        sb.append("  paging: ").append(paging).append("\n");
        sb.append("  results: ").append(results).append("\n");
        sb.append("  errors: ").append(errors).append("\n");
        sb.append("  status: ").append(status).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
