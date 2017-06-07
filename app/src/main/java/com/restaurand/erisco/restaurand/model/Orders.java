package com.restaurand.erisco.restaurand.model;


import java.util.LinkedList;

public class Orders {

    private static Orders mInstance;

    private LinkedList<Order> mOrders;

    public static Orders getInstance(){
        if(mInstance == null){
            mInstance = new Orders();
        }
        return mInstance;
    }

    public Orders() {
        mOrders = new LinkedList<>();

        LinkedList<Table> tables = Tables.getInstance().getTables();
        for(int i = 0; i < tables.size(); i++){
            Order order = new Order(tables.get(i), null);
            mOrders.add(order);
        }
    }

    public Order getOrder(int index){
        return mOrders.get(index);
    }

    public LinkedList<Order> getOrders(){
        return mOrders;
    }

    public int getCount(){
        return mOrders.size();
    }
}
