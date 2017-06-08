package com.restaurand.erisco.restaurand.model;

import java.io.Serializable;
import java.util.LinkedList;

public class Table implements Serializable {

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
        int n = mNumber + 1;
        return "Table " + n;
    }
}
