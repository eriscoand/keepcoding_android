package com.restaurand.erisco.restaurand.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.restaurand.erisco.restaurand.R;
import com.restaurand.erisco.restaurand.activity.DishActivity;
import com.restaurand.erisco.restaurand.adapter.DishRecyclerViewAdapter;
import com.restaurand.erisco.restaurand.model.Dish;
import com.restaurand.erisco.restaurand.model.Dishes;
import com.restaurand.erisco.restaurand.model.Order;
import com.restaurand.erisco.restaurand.model.Orders;

import java.util.LinkedList;

public class OrderFragment extends Fragment {

    private static final String ARG_ORDER = "showOrder";

    private View mRoot;
    private Order mOrder;

    private RecyclerView mList;

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
        mRoot = inflater.inflate(R.layout.fragment_dish, container, false);

        mList = (RecyclerView) mRoot.findViewById(R.id.dish_list);
        mList.setLayoutManager(new GridLayoutManager(getActivity(),1));

        DishRecyclerViewAdapter adapter = new DishRecyclerViewAdapter(mOrder);

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

        mList.setAdapter(adapter);

        return mRoot;
    }

}
