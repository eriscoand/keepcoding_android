package com.restaurand.erisco.restaurand.model;

import android.content.res.Resources;

import com.restaurand.erisco.restaurand.R;

import java.io.Serializable;

public class Allergen implements Serializable {

    private String mName;
    private int mIcon;

    public Allergen(int id){
        switch(id) {
            case 1:
                this.mName = Resources.getSystem().getString(R.string.gluten_text);
                this.mIcon = R.drawable.gluten;
            case 2:
                this.mName = Resources.getSystem().getString(R.string.eggs_text);
                this.mIcon = R.drawable.huevos;
            case 3:
                this.mName = Resources.getSystem().getString(R.string.celery_text);
                this.mIcon = R.drawable.apio;
            case 4:
                this.mName = Resources.getSystem().getString(R.string.peanuts_text);
                this.mIcon = R.drawable.cacahuetes;
            case 5:
                this.mName = Resources.getSystem().getString(R.string.nuts_text);
                this.mIcon = R.drawable.cascara;
            case 6:
                this.mName = Resources.getSystem().getString(R.string.milk_text);
                this.mIcon = R.drawable.lacteos;
            default:
                this.mName = Resources.getSystem().getString(R.string.zombie_text);
                this.mIcon = R.drawable.zombie;
        }
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setIcon(int icon) {
        mIcon = icon;
    }
}
