package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;
import java.util.List;


public class QuestionListResult extends BaseResult implements Parcelable {

    @SerializedName("questions")
    private List<Question> results = null;


    /**
     **/

    public List<Question> getResults() {
        return results;
    }

    public void setResults(List<Question> results) {
        this.results = results;
    }


    public QuestionListResult() {
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<QuestionListResult> CREATOR = new Parcelable.Creator<QuestionListResult>() {
        @Override
        public QuestionListResult createFromParcel(Parcel in) {
            return new QuestionListResult(in);
        }

        @Override
        public QuestionListResult[] newArray(int size) {
            return new QuestionListResult[size];
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

    public QuestionListResult(Parcel in) {


        if (in.readByte() == 0x01) {
            this.results = new ArrayList<Question>();
            in.readList(this.results, Question.class.getClassLoader());
        } else {
            this.results = new ArrayList<Question>();
        }


    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CampaignQuestionListResult {\n");
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
