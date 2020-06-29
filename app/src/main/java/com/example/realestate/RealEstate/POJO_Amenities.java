package com.example.realestate.RealEstate;

public class POJO_Amenities {

    String amenitiesType;
    String imgUrl;
    boolean selected;

    public POJO_Amenities(String amenitiesType, String imgUrl, boolean selected) {
        this.amenitiesType = amenitiesType;
        this.imgUrl = imgUrl;
        this.selected = selected;
    }

    public String getAmenitiesType() {
        return amenitiesType;
    }

    public void setAmenitiesType(String amenitiesType) {
        this.amenitiesType = amenitiesType;
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
