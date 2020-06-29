package com.example.realestate.RealEstate;

public class POJO_bedrooms {

    String bedroom;
    boolean selected;

    public POJO_bedrooms(String bedroom, boolean selected) {
        this.bedroom = bedroom;
        this.selected = selected;
    }

    public String getBedroom() {
        return bedroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
