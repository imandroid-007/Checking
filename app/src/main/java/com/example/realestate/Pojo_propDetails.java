package com.example.realestate;

public class Pojo_propDetails {

    private String propDetailTitle;
    private String propDetailInfo;

    public Pojo_propDetails(String propDetailTitle, String propDetailInfo) {
        this.propDetailTitle = propDetailTitle;
        this.propDetailInfo = propDetailInfo;
    }

    public String getPropDetailTitle() {
        return propDetailTitle;
    }

    public String getPropDetailInfo() {
        return propDetailInfo;
    }
}
