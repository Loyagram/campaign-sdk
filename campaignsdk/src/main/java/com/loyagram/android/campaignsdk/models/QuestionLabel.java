package com.loyagram.android.campaignsdk.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class QuestionLabel implements Parcelable {

    @SerializedName("id")
    private BigDecimal id = null;
    @SerializedName("question_id")
    private BigDecimal questionId = null;
    @SerializedName("name")
    private String name = null;
    @SerializedName("label")
    private String label = null;
    @SerializedName("value")
    private String value = null;
    @SerializedName("field_type")
    private String fieldType = null;
    @SerializedName("img_url")
    private String imgUrl = null;
    @SerializedName("deleted")
    private Boolean deleted = null;
    @SerializedName("order_no")
    private Integer orderNo = null;
    @SerializedName("min_value")
    private BigDecimal minValue = null;
    @SerializedName("max_value")
    private BigDecimal maxValue = null;
    @SerializedName("step_value")
    private BigDecimal stepValue = null;
    @SerializedName("enabled")
    private Boolean enabled = null;
    @SerializedName("response")
    private ResponseAnswer response = null;
    @SerializedName("label_translations")
    private List<LabelTranslation> labelTranslations = null;


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

    public BigDecimal getQuestionId() {
        return questionId;
    }

    public void setQuestionId(BigDecimal questionId) {
        this.questionId = questionId;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    /**
     **/

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    /**
     **/

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
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

    public BigDecimal getMinValue() {
        return minValue;
    }

    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }


    /**
     **/

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }


    /**
     **/

    public BigDecimal getStepValue() {
        return stepValue;
    }

    public void setStepValue(BigDecimal stepValue) {
        this.stepValue = stepValue;
    }


    /**
     **/

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    /**
     **/

    public ResponseAnswer getResponse() {
        return response;
    }

    public void setResponse(ResponseAnswer response) {
        this.response = response;
    }

    public List<LabelTranslation> getLabelTranslations() {
        return labelTranslations;
    }

    public void setLabelTranslations(List<LabelTranslation> labelTranslations) {
        this.labelTranslations = labelTranslations;
    }

    public QuestionLabel() {
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<QuestionLabel> CREATOR = new Parcelable.Creator<QuestionLabel>() {
        @Override
        public QuestionLabel createFromParcel(Parcel in) {
            return new QuestionLabel(in);
        }

        @Override
        public QuestionLabel[] newArray(int size) {
            return new QuestionLabel[size];
        }
    };

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


        if (this.questionId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.questionId);
        }


        if (this.name == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.name);
        }


        if (this.label == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.label);
        }


        if (this.value == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.value);
        }


        if (this.fieldType == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.fieldType);
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


        if (this.minValue == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.minValue);
        }


        if (this.maxValue == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.maxValue);
        }


        if (this.stepValue == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeSerializable(this.stepValue);
        }


        dest.writeValue(this.enabled);


        dest.writeParcelable(this.response, Parcelable.PARCELABLE_WRITE_RETURN_VALUE);


    }

    public QuestionLabel(Parcel in) {
        boolean isPresent = false;


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.id = (BigDecimal) in.readSerializable();
        } else {
            this.id = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.questionId = (BigDecimal) in.readSerializable();
        } else {
            this.questionId = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.name = in.readString();
        } else {
            this.name = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.label = in.readString();
        } else {
            this.label = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.value = in.readString();
        } else {
            this.value = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.fieldType = in.readString();
        } else {
            this.fieldType = null;
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
            this.minValue = (BigDecimal) in.readSerializable();
        } else {
            this.minValue = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.maxValue = (BigDecimal) in.readSerializable();
        } else {
            this.maxValue = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.stepValue = (BigDecimal) in.readSerializable();
        } else {
            this.stepValue = null;
        }


        enabled = (Boolean) in.readValue(Boolean.class.getClassLoader());


        this.response = in.readParcelable(ResponseAnswer.class.getClassLoader());

        if (in.readByte() == 0x01) {
            this.labelTranslations = new ArrayList<LabelTranslation>();
            in.readList(this.labelTranslations, LabelTranslation.class.getClassLoader());
        } else {
            this.labelTranslations = new ArrayList<LabelTranslation>();
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class QuestionLabel {\n");

        sb.append("  id: ").append(id).append("\n");
        sb.append("  questionId: ").append(questionId).append("\n");
        sb.append("  name: ").append(name).append("\n");
        sb.append("  label: ").append(label).append("\n");
        sb.append("  value: ").append(value).append("\n");
        sb.append("  fieldType: ").append(fieldType).append("\n");
        sb.append("  imgUrl: ").append(imgUrl).append("\n");
        sb.append("  deleted: ").append(deleted).append("\n");
        sb.append("  orderNo: ").append(orderNo).append("\n");
        sb.append("  minValue: ").append(minValue).append("\n");
        sb.append("  maxValue: ").append(maxValue).append("\n");
        sb.append("  stepValue: ").append(stepValue).append("\n");
        sb.append("  enabled: ").append(enabled).append("\n");
        sb.append("  response: ").append(response).append("\n");
        sb.append("  labelTranslations: ").append(labelTranslations).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
