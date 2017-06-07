package com.restaurand.erisco.restaurand.model;

import android.content.res.Resources;

import com.restaurand.erisco.restaurand.R;
import com.restaurand.erisco.restaurand.activity.TablesActivity;

import java.io.Serializable;


public class Course implements Serializable {

    private String mName;

    public Course(int code) {
        switch(code) {
            case 1:
                this.mName = TablesActivity.getContext().getString(R.string.starter_text);
            case 2:
                this.mName = TablesActivity.getContext().getString(R.string.main_text);
            case 3:
                this.mName = TablesActivity.getContext().getString(R.string.dessert_text);
            default:
                this.mName = TablesActivity.getContext().getString(R.string.notdefined_text);
        }
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
