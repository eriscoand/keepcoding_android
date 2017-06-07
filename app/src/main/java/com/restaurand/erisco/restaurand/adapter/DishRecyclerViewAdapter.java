package com.restaurand.erisco.restaurand.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.restaurand.erisco.restaurand.R;
import com.restaurand.erisco.restaurand.model.Dish;

import java.util.LinkedList;

public class DishRecyclerViewAdapter extends RecyclerView.Adapter<DishRecyclerViewAdapter.DishViewHolder>{

    private LinkedList<Dish> mDishes;
    private View.OnClickListener mOnClickListener;

    public DishRecyclerViewAdapter(LinkedList<Dish> dishes){
        super();
        mDishes = dishes;
    }

    @Override
    public DishViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_dish,parent,false);

        view.setOnClickListener(mOnClickListener);

        return new DishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DishViewHolder holder, int position) {
        holder.bindForecast(mDishes.get(position));
    }

    @Override
    public int getItemCount() {
        return mDishes.size();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    protected class DishViewHolder extends RecyclerView.ViewHolder {

        private View mRoot;

        public DishViewHolder(View itemView) {
            super(itemView);

            mRoot = itemView;

        }

        public void bindForecast(Dish dish){


        }

    }

}

