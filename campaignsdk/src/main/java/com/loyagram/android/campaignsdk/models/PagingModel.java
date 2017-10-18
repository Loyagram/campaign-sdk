package com.loyagram.android.campaignsdk.models;

/**
 * Created by renju on 28/3/16.
 */


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class PagingModel implements Parcelable {

    @SerializedName("page")
    private Integer page = null;
    @SerializedName("limit")
    private Integer limit = null;
    @SerializedName("sort_by")
    private String sortBy = null;
    @SerializedName("sort_order")
    private String sortOrder = null;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @SerializedName("url")
    private String url = null;


    /**
     **/

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }


    /**
     **/

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }


    /**
     **/

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }


    /**
     **/

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }


    public PagingModel() {
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PagingModel> CREATOR = new Parcelable.Creator<PagingModel>() {
        @Override
        public PagingModel createFromParcel(Parcel in) {
            return new PagingModel(in);
        }

        @Override
        public PagingModel[] newArray(int size) {
            return new PagingModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        if (this.page == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(this.page);
        }


        if (this.limit == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(this.limit);
        }


        if (this.sortBy == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.sortBy);
        }


        if (this.sortOrder == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.sortOrder);
        }


        if (this.url == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(this.url);
        }


    }

    public PagingModel(Parcel in) {
        boolean isPresent = false;


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.page = in.readInt();
        } else {
            this.page = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.limit = in.readInt();
        } else {
            this.limit = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.sortBy = in.readString();
        } else {
            this.sortBy = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.sortOrder = in.readString();
        } else {
            this.sortOrder = null;
        }


        isPresent = in.readByte() == 1;
        if (isPresent) {
            this.url = in.readString();
        } else {
            this.url = null;
        }


    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Paging {\n");

        sb.append("  page: ").append(page).append("\n");
        sb.append("  limit: ").append(limit).append("\n");
        sb.append("  sortBy: ").append(sortBy).append("\n");
        sb.append("  sortOrder: ").append(sortOrder).append("\n");
        sb.append("  url: ").append(url).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
