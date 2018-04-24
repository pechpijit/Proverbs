package com.picha.proverbs.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProverbsModel {

    @SerializedName("idproverbs")
    @Expose
    private String idproverbs;
    @SerializedName("proverbs")
    @Expose
    private String proverbs;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("mean")
    @Expose
    private String mean;

    public String getIdproverbs() {
        return idproverbs;
    }

    public void setIdproverbs(String idproverbs) {
        this.idproverbs = idproverbs;
    }

    public String getProverbs() {
        return proverbs;
    }

    public void setProverbs(String proverbs) {
        this.proverbs = proverbs;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

}
