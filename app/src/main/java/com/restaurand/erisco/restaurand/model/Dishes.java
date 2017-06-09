package com.restaurand.erisco.restaurand.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Dishes {

    private static Dishes mInstance;

    public static Dishes getInstance(){
        return mInstance;
    }

    public static void setInstance(Dishes mInstance) {
        Dishes.mInstance = mInstance;
    }

    private LinkedList<Dish> mDishes;

    public Dishes(JSONObject json){
        try {
            JSONArray list = json.getJSONArray("platos");

            mDishes = new LinkedList<>();

            for(int i = 0; i < list.length(); i++){
                JSONObject jsonDish = list.getJSONObject(i);
                Dish parsedDish = new Dish(jsonDish);
                mDishes.add(parsedDish);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Dish getDish(int index){
        return mDishes.get(index);
    }

    public LinkedList<Dish> getDishes(){
        return mDishes;
    }

    public int getCount(){
        return mDishes.size();
    }

    public LinkedList<Dish> dishesInOrder(Order order){
        LinkedList<Dish> dishes = new LinkedList<Dish>(order.getDishOrdered().keySet());
        Collections.sort(dishes);
        return dishes;
    }

}
