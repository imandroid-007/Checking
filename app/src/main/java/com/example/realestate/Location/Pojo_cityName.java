package com.example.realestate.Location;

public class Pojo_cityName {

    private String id;
    private String cityName;
    private String stateNameLabel;
    private String colorCode;
    private boolean isSelected;

    public Pojo_cityName(String id, String cityName, String stateNameLabel, String colorCode, boolean isSelected) {
        this.id = id;
        this.cityName = cityName;
        this.stateNameLabel = stateNameLabel;
        this.colorCode = colorCode;
        this.isSelected = isSelected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateNameLabel() {
        return stateNameLabel;
    }

    public void setStateNameLabel(String stateNameLabel) {
        this.stateNameLabel = stateNameLabel;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }




}

































