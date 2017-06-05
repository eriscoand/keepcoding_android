package com.restaurand.erisco.restaurand.model;


public class Order {

    private Table mTable;
    private Dishes mDishes;

    public Order(Table table, Dishes dishes){
        mTable = table;
        mDishes = dishes;
    }

    public Table getTable() {
        return mTable;
    }

    public void setTable(Table table) {
        mTable = table;
    }

    public Dishes getDishes() {
        return mDishes;
    }

    public void setDishes(Dishes dishes) {
        mDishes = dishes;
    }
}
