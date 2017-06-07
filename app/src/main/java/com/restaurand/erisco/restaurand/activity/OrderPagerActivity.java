package com.restaurand.erisco.restaurand.activity;


import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.restaurand.erisco.restaurand.R;
import com.restaurand.erisco.restaurand.fragment.OrderPagerFragment;

public class OrderPagerActivity extends AppCompatActivity {

    public static final String EXTRA_ORDER_INDEX = "EXTRA_ORDER_INDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pager);

        int orderIndex = getIntent().getIntExtra(EXTRA_ORDER_INDEX, 0);

        FragmentManager fm = getFragmentManager();
        if(fm.findFragmentById(R.id.view_pager_fragment) == null){
            OrderPagerFragment fragment = OrderPagerFragment.newInstance(orderIndex);
            fm.beginTransaction().add(R.id.view_pager_fragment, fragment).commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superValue = super.onOptionsItemSelected(item);

        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }

        return superValue;
    }

}
