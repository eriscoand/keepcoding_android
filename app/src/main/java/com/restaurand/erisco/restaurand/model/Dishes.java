package com.restaurand.erisco.restaurand.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class Dishes {

    private LinkedList<Dish> mDishes;

    public Dish getDish(int index){
        return mDishes.get(index);
    }

    public LinkedList<Dish> getDishes(){
        return mDishes;
    }

    public int getCount(){
        return mDishes.size();
    }

    public Dishes(JSONObject json){
        try {
            JSONArray list = json.getJSONArray("platos");

            for(int i = 0; i < list.length(); i++){
                JSONObject jsonDish = list.getJSONObject(i);

                String name = jsonDish.getString("nombre");
                Course course = new Course(jsonDish.getInt("tipo"));
                double precio = jsonDish.getDouble("precio");

                JSONArray alergenosJSON = jsonDish.getJSONArray("alergenos");
                LinkedList<Allergen> alergenos = new LinkedList<>();

                for(int j = 0; j < alergenosJSON.length(); j++){
                    Allergen alergeno = new Allergen((int) alergenosJSON.get(j));
                    alergenos.add(alergeno);
                }

                Dish dish = new Dish(name,course,alergenos,precio);
                mDishes.add(dish);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
