package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class CampaignListResult extends BaseResult implements Parcelable {


    @SerializedName("results")
    private List<Campaign> results = null;


    /**
     **/

    public List<Campaign> getResults() {
        return results;
    }

    public void setResults(List<Campaign> results) {
        this.results = results;
    }


    public CampaignListResult() {
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CampaignListResult> CREATOR = new Parcelable.Creator<CampaignListResult>() {
        @Override
        public CampaignListResult createFromParcel(Parcel in) {
            return new CampaignListResult(in);
        }

        @Override
        public CampaignListResult[] newArray(int size) {
            return new CampaignListResult[size];
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

    public CampaignListResult(Parcel in) {

        super(in);


        if (in.readByte() == 0x01) {
            this.results = new ArrayList<Campaign>();
            in.readList(this.results, Campaign.class.getClassLoader());
        } else {
            this.results = new ArrayList<Campaign>();
        }


    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CampaignListResult {\n");
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
