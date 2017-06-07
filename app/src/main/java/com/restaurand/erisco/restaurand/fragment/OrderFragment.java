package com.restaurand.erisco.restaurand.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.restaurand.erisco.restaurand.R;
import com.restaurand.erisco.restaurand.model.Order;
import com.restaurand.erisco.restaurand.model.Orders;

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

        return mRoot;
    }


}
