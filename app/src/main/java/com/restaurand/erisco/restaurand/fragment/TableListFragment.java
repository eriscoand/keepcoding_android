package com.restaurand.erisco.restaurand.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ViewSwitcher;

import com.restaurand.erisco.restaurand.R;
import com.restaurand.erisco.restaurand.helper.Download;
import com.restaurand.erisco.restaurand.model.Dish;
import com.restaurand.erisco.restaurand.model.Dishes;
import com.restaurand.erisco.restaurand.model.Table;

import org.json.JSONObject;

import java.util.LinkedList;

public class TableListFragment extends Fragment {

    private static final String ARG_TABLES = "showTables";
    private static final int LOADING_VIEW_INDEX = 0;
    private static final int LOADED_VIEW_INDEX = 1;


    protected LinkedList<Table> mTables;

    protected OnTableSelectedListener mOnTableSelectedListener;
    private View mRoot;

    public static TableListFragment newInstance(LinkedList<Table> mTables) {
        TableListFragment fragment = new TableListFragment();
        Bundle arguments = new Bundle();

        arguments.putSerializable(ARG_TABLES,mTables);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTables = (LinkedList<Table>) getArguments().getSerializable(ARG_TABLES);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mRoot = inflater.inflate(R.layout.fragment_table_list, container, false);

        ListView list = (ListView) mRoot.findViewById(R.id.table_list);
        ArrayAdapter<Table> adapter = new ArrayAdapter<Table>(getActivity(), android.R.layout.simple_list_item_1,mTables);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mOnTableSelectedListener != null){
                    Table selectedTable = mTables.get(position);
                    mOnTableSelectedListener.onTableSelected(selectedTable, position);
                }
            }
        });

        updateDishes();

        return mRoot;
    }

    public void updateDishes(){

        final ViewSwitcher viewSwitcher = (ViewSwitcher) mRoot.findViewById(R.id.view_switcher_table);
        viewSwitcher.setInAnimation(getActivity(), android.R.anim.fade_in);
        viewSwitcher.setOutAnimation(getActivity(), android.R.anim.fade_out);

        AsyncTask<Dish, Integer, Dishes> weatherDownloader = new AsyncTask<Dish, Integer, Dishes>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                viewSwitcher.setDisplayedChild(LOADING_VIEW_INDEX);
            }

            @Override
            protected Dishes doInBackground(Dish... params) {
                JSONObject json = Download.Platos();
                Dishes dishes = new Dishes(json);
                return dishes;
            };

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(Dishes dishes) {
                super.onPostExecute(dishes);

                if(dishes != null){
                    Dishes.setInstance(dishes);
                    viewSwitcher.setDisplayedChild(LOADED_VIEW_INDEX);
                }else{
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                    alertDialog.setTitle(R.string.error_title);
                    alertDialog.setMessage(R.string.error_message);
                    alertDialog.setPositiveButton(R.string.error_reload, new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            updateDishes();
                        }
                    });
                    alertDialog.show();
                }


            }
        };
        weatherDownloader.execute();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof OnTableSelectedListener){
            mOnTableSelectedListener = (OnTableSelectedListener) getActivity();
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnTableSelectedListener = null;
    }

    public interface OnTableSelectedListener {
        void onTableSelected(Table table, int position);
    }
}
