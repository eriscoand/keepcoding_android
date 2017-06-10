package com.restaurand.erisco.restaurand.fragment;


import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.restaurand.erisco.restaurand.R;
import com.restaurand.erisco.restaurand.activity.DishActivity;
import com.restaurand.erisco.restaurand.adapter.DishRecyclerViewAdapter;
import com.restaurand.erisco.restaurand.model.Dish;
import com.restaurand.erisco.restaurand.model.Dishes;
import com.restaurand.erisco.restaurand.model.Order;
import com.restaurand.erisco.restaurand.model.Orders;

import java.util.LinkedList;

public class OrderFragment extends Fragment implements DishRecyclerViewAdapter.OnReloadingView{

    private static final String ARG_ORDER = "showOrder";

    private View mRoot;
    private Order mOrder;

    private RecyclerView mList;
    private FloatingActionButton mFab;
    private TextView mPriceTextView;

    public static OrderFragment newInstance(int position){
        OrderFragment fragment = new OrderFragment();

        Order order = Orders.getInstance().getOrder(position);

        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_ORDER,order);
        fragment.setArguments(arguments);

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            mOrder = (Order) getArguments().getSerializable(ARG_ORDER);
        }

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRoot = inflater.inflate(R.layout.fragment_order, container, false);

        mList = (RecyclerView) mRoot.findViewById(R.id.dish_list);
        mList.setLayoutManager(new GridLayoutManager(getActivity(),1));

        mFab = (FloatingActionButton) mRoot.findViewById(R.id.trash_button);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.sure_message)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mOrder = Orders.getInstance().clearOrder(mOrder);
                                reloadView();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) { }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        mPriceTextView = (TextView) mRoot.findViewById(R.id.price_text);
        mPriceTextView.setText(mOrder.getTotalPrice());

        reloadView();

        return mRoot;
    }

    private void reloadView(){

        mPriceTextView.setText(mOrder.getTotalPrice());

        DishRecyclerViewAdapter adapter = new DishRecyclerViewAdapter(mOrder,this);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mList.getChildAdapterPosition(v);
                LinkedList<Dish> dishes = Dishes.getInstance().dishesInOrder(mOrder);
                Dish dish = dishes.get(position);
                Intent intent = new Intent(getActivity(), DishActivity.class);
                intent.putExtra(DishActivity.EXTRA_DISH, dish);

                startActivity(intent);
            }
        });

        adapter.notifyDataSetChanged();

        for(int i=0; i < mOrder.getDishOrdered().size(); i++){
            adapter.notifyItemChanged(i);
        }

        mList.setAdapter(adapter);
    }

    @Override
    public void onOrderReload(Order order) {
        mPriceTextView.setText(order.getTotalPrice());
    }
}
