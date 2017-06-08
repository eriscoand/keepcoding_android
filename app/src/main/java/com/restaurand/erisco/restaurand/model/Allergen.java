package com.restaurand.erisco.restaurand.model;

import android.content.res.Resources;

import com.restaurand.erisco.restaurand.R;
import com.restaurand.erisco.restaurand.activity.TablesActivity;

import java.io.Serializable;

public class Allergen implements Serializable {

    private String mName;
    private int mIcon;

    public Allergen(int id){
        switch(id) {
            case 1:
                this.mName = TablesActivity.getContext().getString(R.string.gluten_text);
                this.mIcon = R.drawable.gluten;
                break;
            case 2:
                this.mName = TablesActivity.getContext().getString(R.string.eggs_text);
                this.mIcon = R.drawable.huevos;
                break;
            case 3:
                this.mName = TablesActivity.getContext().getString(R.string.celery_text);
                this.mIcon = R.drawable.apio;
                break;
            case 4:
                this.mName = TablesActivity.getContext().getString(R.string.peanuts_text);
                this.mIcon = R.drawable.cacahuetes;
                break;
            case 5:
                this.mName = TablesActivity.getContext().getString(R.string.nuts_text);
                this.mIcon = R.drawable.cascara;
                break;
            case 6:
                this.mName = TablesActivity.getContext().getString(R.string.milk_text);
                this.mIcon = R.drawable.lacteos;
                break;
            default:
                this.mName = TablesActivity.getContext().getString(R.string.zombie_text);
                this.mIcon = R.drawable.zombie;
                break;
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
