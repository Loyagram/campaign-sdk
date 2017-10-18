package com.loyagram.android.campaignsdk.models.csatcezsettings;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user1 on 23/6/17.
 */

public class CsatCesCustomSettings implements Parcelable {
    @SerializedName("very_dissatisfied")
    private CsatCesReason veryDissatisfied = null;
    @SerializedName("somewhat_dissatisfied")
    private CsatCesReason somewhatDissatisfied = null;
    @SerializedName("neither_satisfied_nor_dissatisfied")
    private CsatCesReason neitherSatisfiedNorDissatisfied = null;
    @SerializedName("somewhat_satisfied")
    private CsatCesReason somewhatSatisfied = null;
    @SerializedName("very_satisfied")
    private CsatCesReason verySatisfied = null;
    @SerializedName("neither_easy_nor_difficult")
    private CsatCesReason neither = null;
    @SerializedName("easy")
    private CsatCesReason easy = null;
    @SerializedName("difficult")
    private CsatCesReason difficult = null;
    @SerializedName("very_easy")
    private CsatCesReason veryEasy = null;
    @SerializedName("very_difficult")
    private CsatCesReason veryDifficult = null;

    public CsatCesReason getVeryDissatisfied() {
        return veryDissatisfied;
    }

    public CsatCesReason getSomewhatDissatisfied() {
        return somewhatDissatisfied;
    }

    public CsatCesReason getNeitherSatisfiedNorDissatisfied() {
        return neitherSatisfiedNorDissatisfied;
    }

    public CsatCesReason getSomewhatSatisfied() {
        return somewhatSatisfied;
    }

    public CsatCesReason getVerySatisfied() {
        return verySatisfied;
    }

    public void setVeryDissatisfied(CsatCesReason veryDissatisfied) {
        this.veryDissatisfied = veryDissatisfied;
    }

    public void setSomewhatDissatisfied(CsatCesReason somewhatDissatisfied) {
        this.somewhatDissatisfied = somewhatDissatisfied;
    }

    public void setNeitherSatisfiedNorDissatisfied(CsatCesReason neitherSatisfiedNorDissatisfied) {
        this.neitherSatisfiedNorDissatisfied = neitherSatisfiedNorDissatisfied;
    }

    public void setSomewhatSatisfied(CsatCesReason somewhatSatisfied) {
        this.somewhatSatisfied = somewhatSatisfied;
    }

    public void setVerySatisfied(CsatCesReason verySatisfied) {
        this.verySatisfied = verySatisfied;
    }

    public CsatCesReason getNeither() {
        return neither;
    }

    public CsatCesReason getEasy() {
        return easy;
    }

    public CsatCesReason getDifficult() {
        return difficult;
    }

    public void setNeither(CsatCesReason neither) {
        this.neither = neither;
    }

    public void setEasy(CsatCesReason easy) {
        this.easy = easy;
    }

    public void setDifficult(CsatCesReason difficult) {
        this.difficult = difficult;
    }

    public CsatCesReason getVeryEasy() {
        return veryEasy;
    }

    public CsatCesReason getVeryDifficult() {
        return veryDifficult;
    }

    public void setVeryEasy(CsatCesReason veryEasy) {
        this.veryEasy = veryEasy;
    }

    public void setVeryDifficult(CsatCesReason veryDifficult) {
        this.veryDifficult = veryDifficult;
    }

    protected CsatCesCustomSettings(Parcel in) {
        this.veryDissatisfied = in.readParcelable(CsatCesReason.class.getClassLoader());
        this.somewhatDissatisfied = in.readParcelable(CsatCesReason.class.getClassLoader());
        this.neitherSatisfiedNorDissatisfied = in.readParcelable(CsatCesReason.class.getClassLoader());
        this.somewhatSatisfied = in.readParcelable(CsatCesReason.class.getClassLoader());
        this.veryDissatisfied = in.readParcelable(CsatCesReason.class.getClassLoader());
        this.neither = in.readParcelable(CsatCesReason.class.getClassLoader());
        this.easy = in.readParcelable(CsatCesReason.class.getClassLoader());
        this.difficult = in.readParcelable(CsatCesReason.class.getClassLoader());
        this.veryEasy = in.readParcelable(CsatCesReason.class.getClassLoader());
        this.veryDifficult = in.readParcelable(CsatCesReason.class.getClassLoader());
    }

    public static final Creator<CsatCesCustomSettings> CREATOR = new Creator<CsatCesCustomSettings>() {
        @Override
        public CsatCesCustomSettings createFromParcel(Parcel in) {
            return new CsatCesCustomSettings(in);
        }

        @Override
        public CsatCesCustomSettings[] newArray(int size) {
            return new CsatCesCustomSettings[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.veryDissatisfied, flags);
        dest.writeParcelable(this.somewhatDissatisfied, flags);
        dest.writeParcelable(this.neitherSatisfiedNorDissatisfied, flags);
        dest.writeParcelable(this.somewhatSatisfied, flags);
        dest.writeParcelable(this.verySatisfied, flags);
        dest.writeParcelable(this.neither, flags);
        dest.writeParcelable(this.easy, flags);
        dest.writeParcelable(this.difficult, flags);
        dest.writeParcelable(this.veryEasy, flags);
        dest.writeParcelable(this.veryDifficult, flags);
    }
}
