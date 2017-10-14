package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.loyagram.android.campaignsdk.models.npssettings.SettingsTranslation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Campaign implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Campaign> CREATOR = new Parcelable.Creator<Campaign>() {
        @Override
        public Campaign createFromParcel(Parcel in) {
            return new Campaign(in);
        }

        @Override
        public Campaign[] newArray(int size) {
            return new Campaign[size];
        }
    };
    @SerializedName("id")
    private BigDecimal id = null;
    @SerializedName("str_id")
    private String campaignId = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("note")
    private String note = null;
    @SerializedName("biz_id")
    private BigDecimal bizId = null;
    @SerializedName("biz_loc_id")
    private BigDecimal bizLocId = null;
    @SerializedName("biz_user_id")
    private BigDecimal bizUserId = null;
    @SerializedName("deleted")
    private Boolean deleted = null;
    @SerializedName("q_count")
    private Integer qCount = null;
    @SerializedName("response_count")
    private Integer responseCount = null;
    @SerializedName("published")
    private Boolean published = null;
    @SerializedName("device_id")
    private String deviceId = null;
    @SerializedName("type")
    private String type = null;
    @SerializedName("color_primary")
    private String colorPriamry = null;
    @SerializedName("logo_url")
    private String logo_url = null;
    @SerializedName("questions")
    private List<Question> results = null;
    @SerializedName("biz")
    private Biz biz = null;
    @SerializedName("settings")
    private LanguageBase languageBase = null;
    @SerializedName("primary_lang")
    private String primaryLang = null;
    @SerializedName("thankyou_message_enabled")
    private Boolean isThankYouEnabled = null;
    @SerializedName("welcome_message_enabled")
    private Boolean isWelcomeEnabled = null;
    @SerializedName("thank_you_and_redirect_settings_translations")
    private List<ThankYouTranslation> thankYouMessages = null;
    @SerializedName("welcome_message_translations")
    private List<WelcomeTranslation> welcomeMessages = null;
    @SerializedName("brand_title")
    private String brandTitle = null;
    @SerializedName("static_texts")
    private List<StaticTextTransalation> staticTextTranslations = null;
    @SerializedName("updated_date")
    private long updatedDate = 0;


    public Campaign() {
    }

    public Campaign(Parcel in) {
        boolean isPresent = false;


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.id = (BigDecimal) in.readSerializable();
        } else {
            this.id = null;
        }

        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.campaignId = in.readString();
        } else {
            this.campaignId = null;
        }

        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.name = in.readString();
        } else {
            this.name = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.note = in.readString();
        } else {
            this.note = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.bizId = (BigDecimal) in.readSerializable();
        } else {
            this.bizId = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.bizLocId = (BigDecimal) in.readSerializable();
        } else {
            this.bizLocId = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.bizUserId = (BigDecimal) in.readSerializable();
        } else {
            this.bizUserId = null;
        }


        deleted = (Boolean) in.readValue(Boolean.class.getClassLoader());


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.qCount = in.readInt();
        } else {
            this.qCount = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.responseCount = in.readInt();
        } else {
            this.responseCount = null;
        }


        published = (Boolean) in.readValue(Boolean.class.getClassLoader());


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.deviceId = in.readString();
        } else {
            this.deviceId = null;
        }

        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.type = in.readString();
        } else {
            this.type = null;
        }

        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.colorPriamry = in.readString();
        } else {
            this.colorPriamry = null;
        }

        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.logo_url = in.readString();
        } else {
            this.logo_url = null;
        }

        if (in.readByte() == 0x01) {
            this.results = new ArrayList<Question>();
            in.readList(this.results, Question.class.getClassLoader());
        } else {
            this.results = new ArrayList<Question>();
        }

        if (in.readByte() == 0x01) {
            this.biz = new Biz();
            in.readParcelable(Biz.class.getClassLoader());
        } else {
            this.biz = new Biz();
        }

        if (in.readByte() == 0x01) {
            this.languageBase = new LanguageBase();
            in.readParcelable(LanguageBase.class.getClassLoader());
        } else {
            this.languageBase = new LanguageBase();
        }

        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.primaryLang = in.readString();
        } else {
            this.primaryLang = null;
        }

        isThankYouEnabled = (Boolean) in.readValue(Boolean.class.getClassLoader());
        isWelcomeEnabled = (Boolean) in.readValue(Boolean.class.getClassLoader());

        if (in.readByte() == 0x01) {
            this.thankYouMessages = new ArrayList<ThankYouTranslation>();
            in.readList(this.thankYouMessages, ThankYouTranslation.class.getClassLoader());
        } else {
            this.thankYouMessages = new ArrayList<ThankYouTranslation>();
        }

        if (in.readByte() == 0x01) {
            this.welcomeMessages = new ArrayList<WelcomeTranslation>();
            in.readList(this.welcomeMessages, WelcomeTranslation.class.getClassLoader());
        } else {
            this.welcomeMessages = new ArrayList<WelcomeTranslation>();
        }
        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.brandTitle = in.readString();
        } else {
            this.brandTitle = null;
        }

        if (in.readByte() == 0x01) {
            this.staticTextTranslations = new ArrayList<>();
            in.readList(this.staticTextTranslations, StaticTextTransalation.class.getClassLoader());
        } else {
            this.staticTextTranslations = new ArrayList<>();
        }

        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.updatedDate = in.readLong();
        } else {
            this.updatedDate = 0;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColorPriamry() {
        return colorPriamry;
    }

    public void setColorPriamry(String colorPriamry) {
        this.colorPriamry = type;
    }

    public String getLogoUrl() {
        return logo_url;
    }

    public void setLogoUrl(String logo_url) {
        this.logo_url = logo_url;
    }

    public Integer getqCount() {
        return qCount;
    }

    public void setqCount(Integer qCount) {
        this.qCount = qCount;
    }

    /**
     **/

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getStringId() {
        return campaignId;
    }

    public void setStringId(String id) {
        this.campaignId = id;
    }


    /**
     **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     **/

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    /**
     **/

    public BigDecimal getBizId() {
        return bizId;
    }

    public void setBizId(BigDecimal bizId) {
        this.bizId = bizId;
    }

    /**
     **/

    public BigDecimal getBizLocId() {
        return bizLocId;
    }

    public void setBizLocId(BigDecimal bizLocId) {
        this.bizLocId = bizLocId;
    }

    /**
     **/

    public BigDecimal getBizUserId() {
        return bizUserId;
    }

    public void setBizUserId(BigDecimal bizUserId) {
        this.bizUserId = bizUserId;
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

    public Integer getQCount() {
        return qCount;
    }

    public void setQCount(Integer qCount) {
        this.qCount = qCount;
    }

    /**
     **/

    public Integer getResponseCount() {
        return responseCount;
    }

    public void setResponseCount(Integer responseCount) {
        this.responseCount = responseCount;
    }

    /**
     **/

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Boolean getThankYouEnabled() {
        return isThankYouEnabled;
    }

    public void setThankYouEnabled(Boolean thankYouEnabled) {
        isThankYouEnabled = thankYouEnabled;
    }

    public Boolean getWelcomeEnabled() {
        return isWelcomeEnabled;
    }

    public void setWelcomeEnabled(Boolean welcomeEnabled) {
        isWelcomeEnabled = welcomeEnabled;
    }

    /**
     **/


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public List<Question> getResults() {
        return results;
    }

    public void setResults(List<Question> results) {
        this.results = results;
    }

    public Biz getBiz() {
        return biz;
    }

    public void setBiz(Biz biz) {
        this.biz = biz;
    }

    public LanguageBase getLanguageBase() {
        return languageBase;
    }

    public void setLanguageBase(LanguageBase languageBase) {
        this.languageBase = languageBase;
    }

    public String getPrimaryLang() {
        return primaryLang;
    }

    public void setPrimaryLang(String primaryLang) {
        this.primaryLang = primaryLang;
    }

    public String getBrandTitle() {
        return brandTitle;
    }

    public void setBrandTitle(String brandTitle) {
        this.brandTitle = brandTitle;
    }


    public List<ThankYouTranslation> getThankYouMessages() {
        return thankYouMessages;
    }

    public void setWelcomeMessages(List<WelcomeTranslation> welcomeMessages) {
        this.welcomeMessages = welcomeMessages;
    }

    public List<WelcomeTranslation> getWelcomeMessages() {
        return welcomeMessages;
    }

    public void setThankYouMessages(List<ThankYouTranslation> thankYouMessages) {
        this.thankYouMessages = thankYouMessages;
    }

    public List<StaticTextTransalation> getStaticTextTranslations() {
        return this.staticTextTranslations;
    }

    public void setStaticTextTranslations(List<StaticTextTransalation> staticTextTranslations) {
        this.staticTextTranslations = staticTextTranslations;
    }

    public long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(long updatedDate) {
        this.updatedDate = updatedDate;
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
            dest.writeString(this.campaignId);
        }

        if (this.name == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.name);
        }


        if (this.note == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.note);
        }


        if (this.bizId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.bizId);
        }


        if (this.bizLocId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.bizLocId);
        }


        if (this.bizUserId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.bizUserId);
        }


        dest.writeValue(this.deleted);


        if (this.qCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(this.qCount);
        }


        if (this.responseCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(this.responseCount);
        }


        dest.writeValue(this.published);


        if (this.deviceId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.deviceId);
        }

        if (this.type == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.type);
        }

        if (this.colorPriamry == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.colorPriamry);
        }

        if (this.logo_url == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.logo_url);
        }

        if (this.results == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(this.results);
        }

        if (this.biz == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeParcelable(this.biz, flags);
        }

        if (this.languageBase == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeParcelable(this.languageBase, flags);
        }

        if (this.primaryLang == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.primaryLang);
        }

        dest.writeValue(this.isThankYouEnabled);
        dest.writeValue(this.isWelcomeEnabled);

        if (this.thankYouMessages == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(this.thankYouMessages);
        }

        if (this.brandTitle == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.brandTitle);
        }

        if (this.staticTextTranslations == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(this.staticTextTranslations);
        }

        if (this.updatedDate == 0) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(this.updatedDate);
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Campaign {\n");

        sb.append("  id: ").append(id).append("\n");
        sb.append("  campaignId: ").append(campaignId).append("\n");
        sb.append("  name: ").append(name).append("\n");
        sb.append("  note: ").append(note).append("\n");
        sb.append("  bizId: ").append(bizId).append("\n");
        sb.append("  bizLocId: ").append(bizLocId).append("\n");
        sb.append("  bizUserId: ").append(bizUserId).append("\n");
        sb.append("  deleted: ").append(deleted).append("\n");
        sb.append("  qCount: ").append(qCount).append("\n");
        sb.append("  responseCount: ").append(responseCount).append("\n");
        sb.append("  published: ").append(published).append("\n");
        sb.append("  deviceId: ").append(deviceId).append("\n");
        sb.append("  type: ").append(type).append("\n");
        sb.append("  colorPrimary: ").append(colorPriamry).append("\n");
        sb.append("  logoUrl: ").append(logo_url).append("\n");
        sb.append("  questions: ").append(results).append("\n");
        sb.append("  biz: ").append(biz).append("\n");
        sb.append("  languageBase: ").append(languageBase).append("\n");
        sb.append("  languageBase: ").append(primaryLang).append("\n");
        sb.append("  thankYouEnabled: ").append(isThankYouEnabled).append("\n");
        sb.append("  welcomeEnabled: ").append(isWelcomeEnabled).append("\n");
        sb.append("  thankYouMessages: ").append(thankYouMessages).append("\n");
        sb.append("  welcomeMessages: ").append(welcomeMessages).append("\n");
        sb.append("  brandTitle: ").append(brandTitle).append("\n");
        sb.append("  StaticTextTranslations: ").append(staticTextTranslations).append("\n");
        sb.append("  updatedDate: ").append(updatedDate).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
