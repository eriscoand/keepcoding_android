package com.restaurand.erisco.restaurand.activity;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.restaurand.erisco.restaurand.R;
import com.restaurand.erisco.restaurand.fragment.TableListFragment;
import com.restaurand.erisco.restaurand.model.Table;
import com.restaurand.erisco.restaurand.model.Tables;

public class TablesActivity extends AppCompatActivity implements TableListFragment.OnTableSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        FragmentManager fm = getFragmentManager();

        if (findViewById(R.id.table_list_fragment) != null){
            if(fm.findFragmentById(R.id.table_list_fragment) == null){
                Tables tables = Tables.getInstance();
                TableListFragment fragment = TableListFragment.newInstance(tables.getTables());

                fm.beginTransaction().add(R.id.table_list_fragment, fragment).commit();
            }
        }

    }

    @Override
    public void onTableSelected(Table table, int position) {

    }

}
