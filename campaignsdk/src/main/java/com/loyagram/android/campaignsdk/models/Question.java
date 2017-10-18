package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.loyagram.android.campaignsdk.models.npssettings.Settings;
import com.loyagram.android.campaignsdk.models.npssettings.SettingsTranslation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Question implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
    @SerializedName("id")
    private BigDecimal id = null;
    @SerializedName("campaign_id")
    private BigDecimal campaignId = null;
    @SerializedName("question")
    private String question = null;
    @SerializedName("type")
    private String type = null;
    @SerializedName("others_entry")
    private Boolean othersEntry = null;
    @SerializedName("optional")
    private Boolean optional = null;
    @SerializedName("parent_question_id")
    private BigDecimal parentQuestionId = null;
    @SerializedName("img_url")
    private String imgUrl = null;
    @SerializedName("deleted")
    private Boolean deleted = null;
    @SerializedName("order_no")
    private Integer orderNo = null;
    @SerializedName("preset_name")
    private String presetName = null;
    @SerializedName("labels")
    private List<QuestionLabel> labels = null;
    @SerializedName("settings")
    private Settings settings = null;
    @SerializedName("question_translations")
    private List<QuestionTranslations> translations = null;
    @SerializedName("settings_translations")
    private List<SettingsTranslation> settingsTranslation = null;

    public Question() {
    }


    public Question(Parcel in) {
        boolean isPresent = false;


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.id = (BigDecimal) in.readSerializable();
        } else {
            this.id = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.campaignId = (BigDecimal) in.readSerializable();
        } else {
            this.campaignId = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.question = in.readString();
        } else {
            this.question = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.type = in.readString();
        } else {
            this.type = null;
        }


        othersEntry = (Boolean) in.readValue(Boolean.class.getClassLoader());


        optional = (Boolean) in.readValue(Boolean.class.getClassLoader());


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.parentQuestionId = (BigDecimal) in.readSerializable();
        } else {
            this.parentQuestionId = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.imgUrl = in.readString();
        } else {
            this.imgUrl = null;
        }


        deleted = (Boolean) in.readValue(Boolean.class.getClassLoader());


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.orderNo = in.readInt();
        } else {
            this.orderNo = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.presetName = in.readString();
        } else {
            this.presetName = null;
        }


        if (in.readByte() == 0x01) {
            this.labels = new ArrayList<QuestionLabel>();
            in.readList(this.labels, QuestionLabel.class.getClassLoader());
        } else {
            this.labels = new ArrayList<QuestionLabel>();
        }

        if (in.readByte() == 0x01) {
            this.settings = new Settings();
            in.readParcelable(Settings.class.getClassLoader());
        } else {
            this.settings = new Settings();
        }

        if (in.readByte() == 0x01) {
            this.translations = new ArrayList<QuestionTranslations>();
            in.readList(this.translations, QuestionTranslations.class.getClassLoader());
        } else {
            this.translations = new ArrayList<QuestionTranslations>();
        }

        if (in.readByte() == 0x01) {
            this.settingsTranslation = new ArrayList<SettingsTranslation>();
            in.readList(this.settingsTranslation, SettingsTranslation.class.getClassLoader());
        } else {
            this.settingsTranslation = new ArrayList<SettingsTranslation>();
        }

    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    /**
     **/

    public BigDecimal getId() {

        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     **/

    public BigDecimal getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(BigDecimal campaignId) {
        this.campaignId = campaignId;
    }

    /**
     **/

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     **/

    public String getType() {
        return type;
    }

    public void setType(String questionType) {
        this.type = questionType;
    }

    /**
     **/

    public Boolean getOthersEntry() {
        return othersEntry;
    }

    public void setOthersEntry(Boolean othersEntry) {
        this.othersEntry = othersEntry;
    }

    /**
     **/

    public Boolean getOptional() {
        return optional;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

    /**
     **/

    public BigDecimal getParentQuestionId() {
        return parentQuestionId;
    }

    public void setParentQuestionId(BigDecimal parentQuestionId) {
        this.parentQuestionId = parentQuestionId;
    }

    /**
     **/

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     **/

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     **/

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    /**
     **/

    public String getPresetName() {
        return presetName;
    }

    public void setPresetName(String presetName) {
        this.presetName = presetName;
    }

    /**
     **/

    public List<QuestionLabel> getLabels() {
        return labels;
    }

    public void setLabels(List<QuestionLabel> labels) {
        this.labels = labels;
    }

    public void setTranslations(List<QuestionTranslations> translations) {
        this.translations = translations;
    }

    public List<QuestionTranslations> getTranslations() {

        return translations;
    }
    public List<SettingsTranslation> getSettingsTranslation() {
        return settingsTranslation;
    }

    public void setSettingsTranslation(List<SettingsTranslation> settingsTranslation) {
        this.settingsTranslation = settingsTranslation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        if (this.id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.id);
        }


        if (this.campaignId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.campaignId);
        }


        if (this.question == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.question);
        }


        if (this.type == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.type);
        }


        dest.writeValue(this.othersEntry);


        dest.writeValue(this.optional);


        if (this.parentQuestionId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.parentQuestionId);
        }


        if (this.imgUrl == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.imgUrl);
        }


        dest.writeValue(this.deleted);


        if (this.orderNo == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(this.orderNo);
        }


        if (this.presetName == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.presetName);
        }


        if (this.labels == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(this.labels);
        }

        if (this.settings == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeParcelable(this.settings, flags);
        }

        if (this.translations == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(this.translations);
        }

        if (this.settingsTranslation == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(this.settingsTranslation);
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Question {\n");

        sb.append("  id: ").append(id).append("\n");
        sb.append("  campaignId: ").append(campaignId).append("\n");
        sb.append("  question: ").append(question).append("\n");
        sb.append("  questionType: ").append(type).append("\n");
        sb.append("  othersEntry: ").append(othersEntry).append("\n");
        sb.append("  optional: ").append(optional).append("\n");
        sb.append("  parentQuestionId: ").append(parentQuestionId).append("\n");
        sb.append("  imgUrl: ").append(imgUrl).append("\n");
        sb.append("  deleted: ").append(deleted).append("\n");
        sb.append("  orderNo: ").append(orderNo).append("\n");
        sb.append("  presetName: ").append(presetName).append("\n");
        sb.append("  labels: ").append(labels).append("\n");
        sb.append("  settings: ").append(settings).append("\n");
        sb.append("  translations: ").append(translations).append("\n");
        sb.append("  settingsTranslation: ").append(settingsTranslation).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}


