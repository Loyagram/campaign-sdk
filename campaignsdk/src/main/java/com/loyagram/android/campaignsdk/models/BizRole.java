package com.loyagram.android.campaignsdk.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BizRole implements Parcelable {
  
  @SerializedName("biz_user_role")
  private String bizUserRole = null;
  @SerializedName("has_entry_perm")
  private Boolean hasEntryPerm = null;
  @SerializedName("has_customer_insight_perm")
  private Boolean hasCustomerInsightPerm = null;
  @SerializedName("has_campaign_manage_perm")
  private Boolean hasCampaignManagePerm = null;
  @SerializedName("has_campaign_entry_perm")
  private Boolean hasCampaignEntryPerm = null;
  @SerializedName("has_campaign_insight_perm")
  private Boolean hasCampaignInsightPerm = null;
  @SerializedName("has_add_loc_perm")
  private Boolean hasAddLocPerm = null;
  @SerializedName("has_add_manager_perm")
  private Boolean hasAddManagerPerm = null;

  
  /**
   **/
  
  public String getBizUserRole() {
    return bizUserRole;
  }
  public void setBizUserRole(String bizUserRole) {
    this.bizUserRole = bizUserRole;
  }

  
  /**
   **/
  
  public Boolean getHasEntryPerm() {
    return hasEntryPerm;
  }
  public void setHasEntryPerm(Boolean hasEntryPerm) {
    this.hasEntryPerm = hasEntryPerm;
  }

  
  /**
   **/
  
  public Boolean getHasCustomerInsightPerm() {
    return hasCustomerInsightPerm;
  }
  public void setHasCustomerInsightPerm(Boolean hasCustomerInsightPerm) {
    this.hasCustomerInsightPerm = hasCustomerInsightPerm;
  }

  
  /**
   **/
  
  public Boolean getHasCampaignManagePerm() {
    return hasCampaignManagePerm;
  }
  public void setHasCampaignManagePerm(Boolean hasCampaignManagePerm) {
    this.hasCampaignManagePerm = hasCampaignManagePerm;
  }

  
  /**
   **/
  
  public Boolean getHasCampaignEntryPerm() {
    return hasCampaignEntryPerm;
  }
  public void setHasCampaignEntryPerm(Boolean hasCampaignEntryPerm) {
    this.hasCampaignEntryPerm = hasCampaignEntryPerm;
  }

  
  /**
   **/
  
  public Boolean getHasCampaignInsightPerm() {
    return hasCampaignInsightPerm;
  }
  public void setHasCampaignInsightPerm(Boolean hasCampaignInsightPerm) {
    this.hasCampaignInsightPerm = hasCampaignInsightPerm;
  }

  
  /**
   **/
  
  public Boolean getHasAddLocPerm() {
    return hasAddLocPerm;
  }
  public void setHasAddLocPerm(Boolean hasAddLocPerm) {
    this.hasAddLocPerm = hasAddLocPerm;
  }

  
  /**
   **/
  
  public Boolean getHasAddManagerPerm() {
    return hasAddManagerPerm;
  }
  public void setHasAddManagerPerm(Boolean hasAddManagerPerm) {
    this.hasAddManagerPerm = hasAddManagerPerm;
  }

  

    public BizRole(){}

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<BizRole> CREATOR = new Parcelable.Creator<BizRole>() {
    @Override
    public BizRole createFromParcel(Parcel in) {
        return new BizRole(in);
    }

    @Override
    public BizRole[] newArray(int size) {
    return new BizRole[size];
    }
    };

    @Override
    public int describeContents() {
    return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
    
        
        
            if(this.bizUserRole == null)
            {
            dest.writeByte((byte)0);
            }
            else{
            dest.writeByte((byte)1);
            dest.writeString(this.bizUserRole);
            }


        
        
        
        
        
        
        
        dest.writeValue(this.hasEntryPerm); 
        
        
        
        
        
        
        
        
        dest.writeValue(this.hasCustomerInsightPerm); 
        
        
        
        
        
        
        
        
        dest.writeValue(this.hasCampaignManagePerm);
        
        
        
        
        
        
        
        
        dest.writeValue(this.hasCampaignEntryPerm);
        
        
        
        
        
        
        
        
        dest.writeValue(this.hasCampaignInsightPerm);
        
        
        
        
        
        
        
        
        dest.writeValue(this.hasAddLocPerm); 
        
        
        
        
        
        
        
        
        dest.writeValue(this.hasAddManagerPerm); 
        
        
        
        
        
        
        
        
        
    }

    public BizRole(Parcel in) {
        boolean isPresent = false;
    

        
        
            isPresent = in.readByte() == 1;
            if(isPresent){
            this.bizUserRole = in.readString();
            }
            else{
            this.bizUserRole = null;
            }
        
        
        
        
        
        
        
        hasEntryPerm = (Boolean) in.readValue(Boolean.class.getClassLoader());

        
        
        
        
        
        
        
        
        hasCustomerInsightPerm = (Boolean) in.readValue(Boolean.class.getClassLoader());

        
        
        
        
        
        
        
        
        hasCampaignManagePerm = (Boolean) in.readValue(Boolean.class.getClassLoader());

        
        
        
        
        
        
        
        
        hasCampaignEntryPerm = (Boolean) in.readValue(Boolean.class.getClassLoader());

        
        
        
        
        
        
        
        
        hasCampaignInsightPerm = (Boolean) in.readValue(Boolean.class.getClassLoader());

        
        
        
        
        
        
        
        
        hasAddLocPerm = (Boolean) in.readValue(Boolean.class.getClassLoader());

        
        
        
        
        
        
        
        
        hasAddManagerPerm = (Boolean) in.readValue(Boolean.class.getClassLoader());

        
        
        
        
        
        
        
        
        
    }


@Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class BizRole {\n");
    
    sb.append("  bizUserRole: ").append(bizUserRole).append("\n");
    sb.append("  hasEntryPerm: ").append(hasEntryPerm).append("\n");
    sb.append("  hasCustomerInsightPerm: ").append(hasCustomerInsightPerm).append("\n");
    sb.append("  hasCampaignManagePerm: ").append(hasCampaignManagePerm).append("\n");
    sb.append("  hasCampaignEntryPerm: ").append(hasCampaignEntryPerm).append("\n");
    sb.append("  hasCampaignInsightPerm: ").append(hasCampaignInsightPerm).append("\n");
    sb.append("  hasAddLocPerm: ").append(hasAddLocPerm).append("\n");
    sb.append("  hasAddManagerPerm: ").append(hasAddManagerPerm).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
