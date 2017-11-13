package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user1 on 19/4/17.
 */

public class LanguageBase implements Parcelable{

    @SerializedName("translations")
    private List<Language> language = null;
    @SerializedName("follow_up_question_enabled")
    private Boolean isFollowUpEnabled = false;
    @SerializedName("follow_up_request_enabled")
    private Boolean isEmailFollowUpEnabled = false;

    public LanguageBase() {
    }

    public List<Language> getLanguage() {
        return language;
    }

    public void setLanguage(List<Language> language) {
        this.language = language;
    }

    public Boolean getFollowUpEnabled() {
        return isFollowUpEnabled;
    }

    public void setFollowUpEnabled(Boolean followUpEnabled) {
        isFollowUpEnabled = followUpEnabled;
    }

    public Boolean getEmailFollowUpEnabled() {
        return isEmailFollowUpEnabled;
    }

    public void setEmailFollowUpEnabled(Boolean emailFollowUpEnabled) {
        isEmailFollowUpEnabled = emailFollowUpEnabled;
    }

    protected LanguageBase(Parcel in) {
        if (in.readByte() == 0x01) {
            this.language = new ArrayList<Language>();
            in.readList(this.language, QuestionTranslations.class.getClassLoader());
        } else {
            this.language = new ArrayList<Language>();
        }

        isFollowUpEnabled = (Boolean) in.readValue(Boolean.class.getClassLoader());
        isEmailFollowUpEnabled = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<LanguageBase> CREATOR = new Creator<LanguageBase>() {
        @Override
        public LanguageBase createFromParcel(Parcel in) {
            return new LanguageBase(in);
        }

        @Override
        public LanguageBase[] newArray(int size) {
            return new LanguageBase[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (this.language == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(this.language);
        }

        dest.writeValue(this.isFollowUpEnabled);
        dest.writeValue(this.isEmailFollowUpEnabled);
    }
}
