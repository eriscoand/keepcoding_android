package com.restaurand.erisco.restaurand.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.LinkedList;

public class Dish implements Serializable {

    private String mName;
    private String mDescription;
    private String mImage;
    private Course mCourse;
    private LinkedList<Allergen> mAllergens;
    private double mPrice;

    public Dish(String name, Course course, LinkedList<Allergen> allergens, double price) {
        this.mName = name;
        this.mCourse = course;
        this.mAllergens = allergens;
        this.mPrice = price;
    }

    public Dish(JSONObject jsonDish){
        try{
            this.mName = jsonDish.getString("nombre");
            this.mDescription = jsonDish.getString("descripcion");
            this.mImage = jsonDish.getString("image");
            this.mCourse = new Course(jsonDish.getInt("tipo"));
            this.mPrice = jsonDish.getDouble("precio");

            JSONArray alergenosJSON = jsonDish.getJSONArray("alergenos");
            LinkedList<Allergen> alergenos = new LinkedList<>();

            for(int i = 0; i < alergenosJSON.length(); i++){
                Allergen alergeno = new Allergen((int) alergenosJSON.get(i));
                alergenos.add(alergeno);
            }
            this.mAllergens = alergenos;

        }catch (Exception ex){
            ex.printStackTrace();
        }
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

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }
}
