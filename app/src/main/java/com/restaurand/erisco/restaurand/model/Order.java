package com.restaurand.erisco.restaurand.model;

import java.io.Serializable;
import java.util.LinkedList;

public class Order implements Serializable {

    private Table mTable;
    private LinkedList<Dish> mDishes;

    public Order(Table table, LinkedList<Dish> dishes){
        mTable = table;
        mDishes = dishes;
    }

    public Table getTable() {
        return mTable;
    }

    public void setTable(Table table) {
        mTable = table;
    }

    public LinkedList<Dish> getDishes() {
        return mDishes;
    }

    public void setDishes(LinkedList<Dish> dishes) {
        mDishes = dishes;
    }
}
