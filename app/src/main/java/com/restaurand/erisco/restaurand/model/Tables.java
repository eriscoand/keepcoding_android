package com.restaurand.erisco.restaurand.model;

import java.util.LinkedList;

public class Tables {

    private static Tables mInstance;

    private LinkedList<Table> mTables;

    public static Tables getInstance(){
        if(mInstance == null){
            mInstance = new Tables();
        }
        return mInstance;
    }

    public Tables() {
        mTables = new LinkedList<>();
        mTables.add(new Table(1));
        mTables.add(new Table(2));
        mTables.add(new Table(3));
        mTables.add(new Table(4));
        mTables.add(new Table(5));
        mTables.add(new Table(6));
        mTables.add(new Table(7));
        mTables.add(new Table(8));
        mTables.add(new Table(9));
        mTables.add(new Table(10));
    }

    public Table getTable(int index){
        return mTables.get(index);
    }

    public LinkedList<Table> getTables(){
        return mTables;
    }

    public int getCount(){
        return mTables.size();
    }

}
