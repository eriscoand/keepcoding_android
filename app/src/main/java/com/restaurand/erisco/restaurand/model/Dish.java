package com.restaurand.erisco.restaurand.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;

public class Dish {

    private String mName;
    private Course mCourse;
    private LinkedList<Allergen> mAllergens;
    private double mPrice;

    public Dish(String name, Course course, LinkedList<Allergen> allergens, double price) {
        this.mName = name;
        this.mCourse = course;
        this.mAllergens = allergens;
        this.mPrice = price;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Course getCourse() {
        return mCourse;
    }

    public void setCourse(Course course) {
        mCourse = course;
    }

    public LinkedList<Allergen> getAllergens() {
        return mAllergens;
    }

    public void setAllergens(LinkedList<Allergen> allergens) {
        mAllergens = allergens;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }
}
