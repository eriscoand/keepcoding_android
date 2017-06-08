package com.restaurand.erisco.restaurand.activity;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.restaurand.erisco.restaurand.R;
import com.restaurand.erisco.restaurand.fragment.OrderPagerFragment;
import com.restaurand.erisco.restaurand.fragment.TableListFragment;
import com.restaurand.erisco.restaurand.model.Order;
import com.restaurand.erisco.restaurand.model.Orders;
import com.restaurand.erisco.restaurand.model.Table;
import com.restaurand.erisco.restaurand.model.Tables;

public class TablesActivity extends AppCompatActivity implements TableListFragment.OnTableSelectedListener {

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        context = getApplicationContext();

        FragmentManager fm = getFragmentManager();

        if (findViewById(R.id.table_list_fragment) != null){
            if(fm.findFragmentById(R.id.table_list_fragment) == null){
                Tables tables = Tables.getInstance();
                TableListFragment fragment = TableListFragment.newInstance(tables.getTables());

                fm.beginTransaction().add(R.id.table_list_fragment, fragment).commit();
            }
        }

    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onTableSelected(int position) {

        FragmentManager fm = getFragmentManager();
        OrderPagerFragment orderFragment = (OrderPagerFragment) fm.findFragmentById(R.id.view_pager_fragment);

        if(orderFragment != null){
            orderFragment.moveToOrder(position);
        }else{
            Intent intent = new Intent(this, OrderPagerActivity.class);
            intent.putExtra(OrderPagerActivity.EXTRA_ORDER_INDEX,position);
            startActivity(intent);
        }

    }

}