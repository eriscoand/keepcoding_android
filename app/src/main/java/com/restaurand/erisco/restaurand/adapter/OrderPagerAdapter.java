package com.restaurand.erisco.restaurand.adapter;

import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.restaurand.erisco.restaurand.fragment.OrderFragment;
import com.restaurand.erisco.restaurand.model.Orders;
import com.restaurand.erisco.restaurand.model.Table;


public class OrderPagerAdapter extends FragmentPagerAdapter {
    private Orders mOrders;

    public OrderPagerAdapter(FragmentManager fm, Orders orders) {
        super(fm);
        mOrders = orders;
    }

    @Override
    public android.app.Fragment getItem(int position) {
        OrderFragment fragment = OrderFragment.newInstance(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return mOrders.getCount();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
        Table table = mOrders.getOrder(position).getTable();
        return table.toString();
    }

}
