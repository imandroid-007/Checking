package com.example.realestate.RealEstate;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.pchmn.materialchips.model.ChipInterface;

public class LocationChip implements ChipInterface {

    private Object id;
    private String label;
    private String info;

    public LocationChip(Object id, String label, String info) {
        this.id = id;
        this.label = label;
        this.info = info;
    }

    @Override
    public Object getId() {
        return id;
    }

    @Override
    public Uri getAvatarUri() {
        return null;
    }

    @Override
    public Drawable getAvatarDrawable() {
        return null;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getInfo() {
        return info;
    }


}
