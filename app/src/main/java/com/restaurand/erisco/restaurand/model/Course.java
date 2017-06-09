package com.restaurand.erisco.restaurand.model;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.restaurand.erisco.restaurand.R;
import com.restaurand.erisco.restaurand.activity.TablesActivity;

import java.io.Serializable;


public class Course implements Serializable {

    private int mCode;
    private String mName;
    private int mColor;

    public Course(int code) {
        this.mCode = code;
        switch(code) {
            case 1:
                this.mName = TablesActivity.getContext().getString(R.string.starter_text);
                this.mColor = ContextCompat.getColorStateList(TablesActivity.getContext(),R.color.courseOne).getDefaultColor();
                break;
            case 2:
                this.mName = TablesActivity.getContext().getString(R.string.main_text);
                this.mColor = ContextCompat.getColorStateList(TablesActivity.getContext(),R.color.courseTwo).getDefaultColor();
                break;
            case 3:
                this.mName = TablesActivity.getContext().getString(R.string.dessert_text);
                this.mColor = ContextCompat.getColorStateList(TablesActivity.getContext(),R.color.courseThree).getDefaultColor();
                break;
            default:
                this.mName = TablesActivity.getContext().getString(R.string.notdefined_text);
                this.mColor = ContextCompat.getColorStateList(TablesActivity.getContext(),R.color.courseDefault).getDefaultColor();
                break;
        }
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        mCode = code;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }
}
