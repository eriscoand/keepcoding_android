package com.restaurand.erisco.restaurand.fragment;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.restaurand.erisco.restaurand.R;
import com.restaurand.erisco.restaurand.activity.OrderPagerActivity;
import com.restaurand.erisco.restaurand.adapter.OrderPagerAdapter;
import com.restaurand.erisco.restaurand.model.Order;
import com.restaurand.erisco.restaurand.model.Orders;
import com.restaurand.erisco.restaurand.model.Table;
import com.restaurand.erisco.restaurand.model.Tables;

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

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                updateOrderInfo(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mPager.setAdapter(adapter);

        moveToOrder(mInitialOrder);
        updateOrderInfo(mInitialOrder);

        FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.trash_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.sure_message)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) { }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

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


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_pager, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superReturn = super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.back){
            moveToOrder(mPager.getCurrentItem() - 1);
            return true;
        }else if (item.getItemId() == R.id.next){
            moveToOrder(mPager.getCurrentItem() + 1);
            return true;
        }
        return superReturn;

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        MenuItem menuPrev = menu.findItem(R.id.back);
        MenuItem menuNext = menu.findItem(R.id.next);

        menuPrev.setEnabled(mPager.getCurrentItem() > 0);
        menuNext.setEnabled(mPager.getCurrentItem() < mOrders.getCount() - 1);

    }


}
