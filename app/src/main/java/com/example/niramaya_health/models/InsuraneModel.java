package com.example.niramaya_health.models;

public class InsuraneModel {

    private String imageurl;
    private String companyname;
    private String discription;
    private String validity;
    private String amountcovered;

    private String websiteurl;

    InsuraneModel()
    {

    }
    public InsuraneModel(String imageurl, String companyname, String discription, String validity, String amountcovered,String websiteurl) {
        this.imageurl = imageurl;
        this.companyname = companyname;
        this.discription = discription;
        this.validity = validity;
        this.amountcovered = amountcovered;
        this.websiteurl=websiteurl;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getAmountcovered() {
        return amountcovered;
    }

    public void setAmountcovered(String amountcovered) {
        this.amountcovered = amountcovered;
    }

    public String getWebsiteurl() {
        return websiteurl;
    }

    public void setWebsiteurl(String websiteurl) {
        this.websiteurl = websiteurl;
    }
}
