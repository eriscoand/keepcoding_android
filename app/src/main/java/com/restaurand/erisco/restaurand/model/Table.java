package com.restaurand.erisco.restaurand.model;

import java.util.LinkedList;

public class Table {

    private int mNumber;

    public Table(int number) {
        mNumber = number;
    }

    public int getNumber() {
        return mNumber;
    }

    public void setNumber(int number) {
        mNumber = number;
    }

    @Override
    public String toString() {
        return "Table " + mNumber;
    }
}
