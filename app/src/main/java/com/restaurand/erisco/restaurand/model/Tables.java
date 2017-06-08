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
        for(int i = 0; i < 10; i++){
            mTables.add(new Table(i));
        }
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
