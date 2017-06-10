package com.restaurand.erisco.restaurand.adapter;

import android.app.Fragment;
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
import com.restaurand.erisco.restaurand.model.Order;
import com.restaurand.erisco.restaurand.model.Orders;

import org.w3c.dom.Text;

import java.util.LinkedList;

public class DishRecyclerViewAdapter extends RecyclerView.Adapter<DishRecyclerViewAdapter.DishViewHolder>{

    private Order mOrder;
    private LinkedList<Dish> mDishes;
    private View.OnClickListener mOnClickListener;
    private OnReloadingView mOnReloadListener;
    private Fragment mParentView;

    public DishRecyclerViewAdapter(Order order, Fragment parentView){
        super();
        mOrder = order;
        mDishes = Dishes.getInstance().dishesInOrder(mOrder);
        mParentView = parentView; //NOT SURE ABOUT THAT :_D
    }

    @Override
    public DishViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_dish,parent,false);

        view.setOnClickListener(mOnClickListener);
        mOrder = Orders.getInstance().getOrders().get(mOrder.getTable().getNumber());
        return new DishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DishViewHolder holder, int position) {

        Dish dish = mDishes.get(position);
        LinkedList<Order> orders = Orders.getInstance().getOrders();
        Order order = orders.get(mOrder.getTable().getNumber());
        int ordered = order.getDishOrdered().get(dish).intValue();

        holder.bindOrderDish(dish, ordered);

    }

    @Override
    public int getItemCount() {
        return mDishes.size();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface OnReloadingView {
        void onOrderReload(Order order);
    }

    protected class DishViewHolder extends RecyclerView.ViewHolder {

        private View mRoot;
        private TextView mDish_title;
        private Button mAdd_button;
        private Button mDel_button;
        private TextView mDish_number;
        private Dish selectedDish;

        public DishViewHolder(View itemView) {
            super(itemView);
            mRoot = itemView;

            mDish_title = (TextView) itemView.findViewById(R.id.dish_title);
            mDish_number = (TextView) itemView.findViewById(R.id.dish_number);
            mAdd_button = (Button) itemView.findViewById(R.id.add_button);
            mDel_button = (Button) itemView.findViewById(R.id.del_button);

        }

        public void reloadInterface(int number){
            //No need to refresh all interface to just add or substract one number!
            mDish_number.setText(String.valueOf(number));
        }

        public void bindOrderDish(Dish dish, int ordered){

            mOnReloadListener = (OnReloadingView) mParentView;

            mDish_title.setText(dish.getName());
            reloadInterface(ordered);
            selectedDish = dish;

            mRoot.setBackgroundColor(dish.getCourse().getColor());

            mAdd_button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int ordered = mOrder.modifyOrderedNumber(selectedDish,1);
                    reloadInterface(ordered);
                    mOnReloadListener.onOrderReload(mOrder);
                }

            });

            mDel_button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int ordered = mOrder.modifyOrderedNumber(selectedDish,-1);
                    reloadInterface(ordered);
                    mOnReloadListener.onOrderReload(mOrder);
                }

            });

        }


    }

}

