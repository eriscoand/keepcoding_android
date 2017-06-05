package com.restaurand.erisco.restaurand.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewSwitcher;

import com.restaurand.erisco.restaurand.R;
import com.restaurand.erisco.restaurand.model.Dish;

public class OrderFragment extends Fragment {

    private static final String ARG_DISH = "showDish";

    private View mRoot;
    private Dish mDish;
    private RecyclerView mList;

    public static OrderFragment newInstance(Dish dish){
        OrderFragment fragment = new OrderFragment();

        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_DISH,dish);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            mDish = (Dish) getArguments().getSerializable(ARG_DISH);
        }

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRoot = inflater.inflate(R.layout.fragment_dish, container, false);

        mList = (RecyclerView) mRoot.findViewById(R.id.dish_list);
        mList.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        return mRoot;
    }


}
