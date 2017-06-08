package com.restaurand.erisco.restaurand.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.restaurand.erisco.restaurand.R;
import com.restaurand.erisco.restaurand.model.Dish;
import com.restaurand.erisco.restaurand.model.Dishes;

import org.w3c.dom.Text;

import java.util.LinkedList;

public class DishRecyclerViewAdapter extends RecyclerView.Adapter<DishRecyclerViewAdapter.DishViewHolder>{

    private LinkedList<Dish> mDishes;
    private View.OnClickListener mOnClickListener;

    public DishRecyclerViewAdapter(){
        super();
        mDishes = Dishes.getInstance().getDishes();
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
        holder.bindOrderDish(mDishes.get(position));
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
        private TextView mDish_title;
        private Button mAdd_button;
        private Button mDel_button;
        private TextView mDish_number;

        public DishViewHolder(View itemView) {
            super(itemView);
            mRoot = itemView;

            mDish_title = (TextView) itemView.findViewById(R.id.dish_title);
            mDish_number = (TextView) itemView.findViewById(R.id.dish_number);
            mAdd_button = (Button) itemView.findViewById(R.id.add_button);
            mDel_button = (Button) itemView.findViewById(R.id.del_button);
        }

        public void bindOrderDish(Dish dish){

            mDish_title.setText(dish.getName());
            mDish_number.setText("0");

            mAdd_button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                }

            });

            mDel_button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                }

            });


        }

    }

}

