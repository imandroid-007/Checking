package com.example.realestate.RealEstate;

public class POJO_PropType {

    String propType;
    String imgUrl;
    boolean selected;
    String propSpecType;

    public POJO_PropType(String propType, String imgUrl, boolean selected, String propSpecType) {
        this.propType = propType;
        this.imgUrl = imgUrl;
        this.selected = selected;
        this.propSpecType = propSpecType;
    }

    public String getPropSpecType() {
        return propSpecType;
    }

    public void setPropSpecType(String propSpecType) {
        this.propSpecType = propSpecType;
    }

    public String getPropType() {
        return propType;
    }

    public void setPropType(String propType) {
        this.propType = propType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


}
