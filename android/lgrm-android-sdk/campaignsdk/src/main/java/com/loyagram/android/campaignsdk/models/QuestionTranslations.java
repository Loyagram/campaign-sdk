package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 * Created by user1 on 19/4/17.
 */

public class QuestionTranslations implements Parcelable {

    @SerializedName("q_id")
    private BigDecimal questionId = null;
    @SerializedName("lang")
    private String code = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("text")
    private String translation = null;


    public BigDecimal getQuestionId() {
        return questionId;
    }

    public void setQuestionId(BigDecimal questionId) {
        this.questionId = questionId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    public static final Creator<QuestionTranslations> CREATOR = new Creator<QuestionTranslations>() {
        @Override
        public QuestionTranslations createFromParcel(Parcel in) {
            return new QuestionTranslations(in);
        }

        @Override
        public QuestionTranslations[] newArray(int size) {
            return new QuestionTranslations[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        if (this.questionId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.questionId);
        }

        if (this.code == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.code);
        }

        if (this.name == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.name);
        }


        if (this.translation == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.translation);
        }

    }

    public QuestionTranslations(Parcel in) {

        boolean isPresent = false;

        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.questionId = (BigDecimal) in.readSerializable();
        } else {
            this.questionId = null;
        }

        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.code = in.readString();
        } else {
            this.code = null;
        }

        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.name = in.readString();
        } else {
            this.name = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.translation = in.readString();
        } else {
            this.translation = null;
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class QuestionTranslation {\n");

        sb.append("  questionId: ").append(questionId).append("\n");
        sb.append("  code: ").append(code).append("\n");
        sb.append("  name: ").append(name).append("\n");
        sb.append("  translation: ").append(translation).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}
