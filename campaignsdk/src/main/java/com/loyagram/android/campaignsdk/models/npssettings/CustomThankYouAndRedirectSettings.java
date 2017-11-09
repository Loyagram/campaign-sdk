package com.loyagram.android.campaignsdk.models.npssettings;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by renju on 18/10/16.
 */
public class CustomThankYouAndRedirectSettings implements Parcelable {
    @SerializedName("detractors")
    private ThankYouAndRedirectSetting detractors = null;
    @SerializedName("passives")
    private ThankYouAndRedirectSetting passives = null;
    @SerializedName("promoters")
    private ThankYouAndRedirectSetting promoters = null;
    @SerializedName("very_dissatisfied")
    private ThankYouAndRedirectSetting veryDissatisfied = null;
    @SerializedName("somewhat_dissatisfied")
    private ThankYouAndRedirectSetting somewhatDissatisfied = null;
    @SerializedName("neither_satisfied_nor_dissatisfied")
    private ThankYouAndRedirectSetting neitherSatisfiedNorDissatisfied = null;
    @SerializedName("somewhat_satisfied")
    private ThankYouAndRedirectSetting somewhatSatisfied = null;
    @SerializedName("very_satisfied")
    private ThankYouAndRedirectSetting verySatisfied = null;
    @SerializedName("neither_easy_nor_difficult")
    private ThankYouAndRedirectSetting neither = null;
    @SerializedName("easy")
    private ThankYouAndRedirectSetting easy = null;
    @SerializedName("difficult")
    private ThankYouAndRedirectSetting difficult = null;
    @SerializedName("very_easy")
    private ThankYouAndRedirectSetting veryEasy = null;
    @SerializedName("very_difficult")
    private ThankYouAndRedirectSetting veryDifficult = null;
    @SerializedName("dissatisfied")
    private ThankYouAndRedirectSetting unsatisfied = null;
    @SerializedName("neutral")
    private ThankYouAndRedirectSetting neutral = null;
    @SerializedName("satisfied")
    private ThankYouAndRedirectSetting satisfied = null;
    @SerializedName("disagree")
    private ThankYouAndRedirectSetting disagree = null;
    @SerializedName("neither_agree_nor_disagree")
    private ThankYouAndRedirectSetting neitherDisagreeOrAgree = null;
    @SerializedName("agree")
    private ThankYouAndRedirectSetting agree = null;




    public CustomThankYouAndRedirectSettings() {
    }

    public ThankYouAndRedirectSetting getDetractors() {
        return detractors;
    }

    public void setDetractors(ThankYouAndRedirectSetting detractors) {
        this.detractors = detractors;
    }

    public ThankYouAndRedirectSetting getPromoters() {
        return promoters;
    }

    public void setPromoters(ThankYouAndRedirectSetting promoters) {
        this.promoters = promoters;
    }

    public ThankYouAndRedirectSetting getPassives() {
        return passives;
    }

    public void setPassives(ThankYouAndRedirectSetting passives) {
        this.passives = passives;
    }

    public ThankYouAndRedirectSetting getVeryDissatisfied() {
        return veryDissatisfied;
    }

    public ThankYouAndRedirectSetting getSomewhatDissatisfied() {
        return somewhatDissatisfied;
    }

    public ThankYouAndRedirectSetting getNeitherSatisfiedNorDissatisfied() {
        return neitherSatisfiedNorDissatisfied;
    }

    public ThankYouAndRedirectSetting getSomewhatSatisfied() {
        return somewhatSatisfied;
    }

    public ThankYouAndRedirectSetting getVerySatisfied() {
        return verySatisfied;
    }


    public void setVeryDissatisfied(ThankYouAndRedirectSetting veryDissatisfied) {
        this.veryDissatisfied = veryDissatisfied;
    }

    public void setSomewhatDissatisfied(ThankYouAndRedirectSetting somewhatDissatisfied) {
        this.somewhatDissatisfied = somewhatDissatisfied;
    }

    public void setNeitherSatisfiedNorDissatisfied(ThankYouAndRedirectSetting neitherSatisfiedNorDissatisfied) {
        this.neitherSatisfiedNorDissatisfied = neitherSatisfiedNorDissatisfied;
    }

    public void setSomewhatSatisfied(ThankYouAndRedirectSetting somewhatSatisfied) {
        this.somewhatSatisfied = somewhatSatisfied;
    }

    public void setVerySatisfied(ThankYouAndRedirectSetting verySatisfied) {
        this.verySatisfied = verySatisfied;
    }


    public ThankYouAndRedirectSetting getNeither() {
        return neither;
    }

    public ThankYouAndRedirectSetting getEasy() {
        return easy;
    }

    public ThankYouAndRedirectSetting getDifficult() {
        return difficult;
    }

    public void setNeither(ThankYouAndRedirectSetting neither) {
        this.neither = neither;
    }

    public void setEasy(ThankYouAndRedirectSetting easy) {
        this.easy = easy;
    }

    public void setDifficult(ThankYouAndRedirectSetting difficult) {
        this.difficult = difficult;
    }

    public ThankYouAndRedirectSetting getVeryEasy() {
        return veryEasy;
    }

    public ThankYouAndRedirectSetting getVeryDifficult() {
        return veryDifficult;
    }

    public void setVeryEasy(ThankYouAndRedirectSetting veryEasy) {
        this.veryEasy = veryEasy;
    }

    public void setVeryDifficult(ThankYouAndRedirectSetting veryDifficult) {
        this.veryDifficult = veryDifficult;
    }

    public ThankYouAndRedirectSetting getUnsatisfied() {
        return unsatisfied;
    }

    public ThankYouAndRedirectSetting getNeutral() {
        return neutral;
    }

    public ThankYouAndRedirectSetting getSatisfied() {
        return satisfied;
    }

    public ThankYouAndRedirectSetting getDisagree() {
        return disagree;
    }

    public ThankYouAndRedirectSetting getNeitherDisagreeOrAgree() {
        return neitherDisagreeOrAgree;
    }

    public ThankYouAndRedirectSetting getAgree() {
        return agree;
    }

    public void setUnsatisfied(ThankYouAndRedirectSetting unsatisfied) {
        this.unsatisfied = unsatisfied;
    }

    public void setNeutral(ThankYouAndRedirectSetting neutral) {
        this.neutral = neutral;
    }

    public void setSatisfied(ThankYouAndRedirectSetting satisfied) {
        this.satisfied = satisfied;
    }

    public void setDisagree(ThankYouAndRedirectSetting disagree) {
        this.disagree = disagree;
    }

    public void setNeitherDisagreeOrAgree(ThankYouAndRedirectSetting neitherDisagreeOrAgree) {
        this.neitherDisagreeOrAgree = neitherDisagreeOrAgree;
    }

    public void setAgree(ThankYouAndRedirectSetting agree) {
        this.agree = agree;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.detractors, flags);
        dest.writeParcelable(this.passives, flags);
        dest.writeParcelable(this.promoters, flags);
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

    protected CustomThankYouAndRedirectSettings(Parcel in) {
        this.detractors = in.readParcelable(ThankYouAndRedirectSetting.class.getClassLoader());
        this.passives = in.readParcelable(ThankYouAndRedirectSetting.class.getClassLoader());
        this.promoters = in.readParcelable(ThankYouAndRedirectSetting.class.getClassLoader());
        this.veryDissatisfied = in.readParcelable(ThankYouAndRedirectSetting.class.getClassLoader());
        this.somewhatDissatisfied = in.readParcelable(ThankYouAndRedirectSetting.class.getClassLoader());
        this.neitherSatisfiedNorDissatisfied = in.readParcelable(ThankYouAndRedirectSetting.class.getClassLoader());
        this.somewhatSatisfied = in.readParcelable(ThankYouAndRedirectSetting.class.getClassLoader());
        this.verySatisfied = in.readParcelable(ThankYouAndRedirectSetting.class.getClassLoader());
        this.neither = in.readParcelable(ThankYouAndRedirectSetting.class.getClassLoader());
        this.easy = in.readParcelable(ThankYouAndRedirectSetting.class.getClassLoader());
        this.difficult = in.readParcelable(ThankYouAndRedirectSetting.class.getClassLoader());
        this.veryEasy = in.readParcelable(ThankYouAndRedirectSetting.class.getClassLoader());
        this.veryDifficult = in.readParcelable(ThankYouAndRedirectSetting.class.getClassLoader());
    }

    public static final Parcelable.Creator<CustomThankYouAndRedirectSettings> CREATOR = new Parcelable.Creator<CustomThankYouAndRedirectSettings>() {
        @Override
        public CustomThankYouAndRedirectSettings createFromParcel(Parcel source) {
            return new CustomThankYouAndRedirectSettings(source);
        }

        @Override
        public CustomThankYouAndRedirectSettings[] newArray(int size) {
            return new CustomThankYouAndRedirectSettings[size];
        }
    };
}
