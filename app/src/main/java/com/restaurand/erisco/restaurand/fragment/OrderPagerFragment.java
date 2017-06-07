package com.restaurand.erisco.restaurand.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.restaurand.erisco.restaurand.R;
import com.restaurand.erisco.restaurand.adapter.OrderPagerAdapter;
import com.restaurand.erisco.restaurand.model.Order;
import com.restaurand.erisco.restaurand.model.Orders;
import com.restaurand.erisco.restaurand.model.Table;

public class OrderPagerFragment extends Fragment {

    private static final String ARG_INITIAL_ORDER_INDEX = "ARG_INITIAL_ORDER_INDEX";

    private ViewPager mPager;
    private Orders mOrders;
    private int mInitialOrder;


    public static OrderPagerFragment newInstance(int position){
        OrderPagerFragment fragment = new OrderPagerFragment();

        Order order = Orders.getInstance().getOrder(position);

        Bundle arguments = new Bundle();
        arguments.putInt(ARG_INITIAL_ORDER_INDEX,order.getTable().getNumber());
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        if(getArguments() != null){
            mInitialOrder = getArguments().getInt(ARG_INITIAL_ORDER_INDEX,0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_order_pager, container, false);

        mPager = (ViewPager) root.findViewById(R.id.view_pager);
        mOrders = Orders.getInstance();
        OrderPagerAdapter adapter = new OrderPagerAdapter(getFragmentManager(),mOrders);

        mPager.setAdapter(adapter);

        moveToOrder(mInitialOrder);
        updateOrderInfo(mInitialOrder);

        return root;
    }

    public void moveToOrder(int tableIndex){
        mPager.setCurrentItem(tableIndex);
    }

    private void updateOrderInfo(int position){
        Table table = mOrders.getOrder(position).getTable();
        if(getActivity() instanceof AppCompatActivity){
            AppCompatActivity parentActivity = (AppCompatActivity) getActivity();
            ActionBar toolbar = parentActivity.getSupportActionBar();
            if(toolbar!= null){
                toolbar.setTitle(table.toString());
            }
        }
    }

}
