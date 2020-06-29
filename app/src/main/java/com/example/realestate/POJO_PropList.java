package com.example.realestate;

public class POJO_PropList {

    String imgUrl;
    String price;
    String bedroom;
    String propName;
    String address;
    String status;
    String callTitle;
    String callContact;

    public POJO_PropList(String imgUrl, String price, String bedroom, String propName, String address, String status, String callTitle, String callContact) {
        this.imgUrl = imgUrl;
        this.price = price;
        this.bedroom = bedroom;
        this.propName = propName;
        this.address = address;
        this.status = status;
        this.callTitle = callTitle;
        this.callContact = callContact;
    }

    public String getBedroom() {
        return bedroom;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getPrice() {
        return price;
    }

    public String getPropName() {
        return propName;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public String getCallTitle() {
        return callTitle;
    }

    public String getCallContact() {
        return callContact;
    }
}


































