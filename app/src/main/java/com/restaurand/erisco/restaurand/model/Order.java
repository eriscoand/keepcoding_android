package com.restaurand.erisco.restaurand.model;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Order implements Serializable,Comparable<Order> {

    private Table mTable;
    private Map<Dish, Integer> mDishOrdered;

    public Order(Table table){
        mTable = table;

        LinkedList<Dish> dishes = Dishes.getInstance().getDishes();
        mDishOrdered = new HashMap<Dish, Integer>();

        for(int i = 0; i < dishes.size(); i++){
            mDishOrdered.put(dishes.get(i), 0);
        }

    }

    public Table getTable() {
        return mTable;
    }

    public void setTable(Table table) {
        mTable = table;
    }

    public Map<Dish, Integer> getDishOrdered() {
        return mDishOrdered;
    }

    public void setDishOrdered(Map<Dish, Integer> dishOrdered) {
        mDishOrdered = dishOrdered;
    }

    public int modifyOrderedNumber(Dish dish, int number){
        int ordered = mDishOrdered.get(dish);
        ordered += number;

        ordered = (ordered >= 0) ? ordered: 0;

        mDishOrdered.put(dish, ordered);
        Orders.getInstance().modifyOrder(this);

        return ordered;
    }

    public String getTotalPrice(){
        LinkedList<Dish> dishes = Dishes.getInstance().getDishes();
        double totalPrice = 0;
        for(int i = 0; i < dishes.size(); i++){

            Dish dish = dishes.get(i);
            int count = mDishOrdered.get(dish);

            totalPrice += dish.getPrice() * count;
        }
        return String.valueOf(String.format("%.2f", totalPrice)) + " €";
    }

    @Override
    public int compareTo(@NonNull Order o) {
        return this.getTable().getNumber() - o.getTable().getNumber();
    }

}
