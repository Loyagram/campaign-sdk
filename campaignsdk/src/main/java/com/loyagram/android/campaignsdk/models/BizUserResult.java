package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BizUserResult extends BaseResult implements Parcelable {


    @SerializedName("results")
    private BizUser results = null;


    /**
     **/

    public BizUser getResults() {
        return results;
    }

    public void setResults(BizUser results) {
        this.results = results;
    }



    public BizUserResult() {
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<BizUserResult> CREATOR = new Parcelable.Creator<BizUserResult>() {
        @Override
        public BizUserResult createFromParcel(Parcel in) {
            return new BizUserResult(in);
        }

        @Override
        public BizUserResult[] newArray(int size) {
            return new BizUserResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

        dest.writeParcelable(this.results, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);

    }

    public BizUserResult(Parcel in) {
        super(in);
        boolean isPresent = false;

        this.results = in.readParcelable(BizUser.class.getClassLoader());

    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BizUserResult {\n");
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
