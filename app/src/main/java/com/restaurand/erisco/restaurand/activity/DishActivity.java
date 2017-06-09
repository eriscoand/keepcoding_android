package com.restaurand.erisco.restaurand.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.restaurand.erisco.restaurand.R;
import com.restaurand.erisco.restaurand.model.Allergen;
import com.restaurand.erisco.restaurand.model.Dish;

import java.util.LinkedList;


public class DishActivity extends AppCompatActivity {

    public static final String EXTRA_DISH = "EXTRA_DISH";
    private ImageView mDish_image;
    private ImageView mIntolerance_1;
    private ImageView mIntolerance_2;
    private ImageView mIntolerance_3;
    private ImageView mIntolerance_4;
    private ImageView mIntolerance_5;
    private TextView mDish_name;
    private TextView mDish_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        mDish_image = (ImageView) findViewById(R.id.dish_image);
        mIntolerance_1 = (ImageView) findViewById(R.id.intolerance_1);
        mIntolerance_2 = (ImageView) findViewById(R.id.intolerance_2);
        mIntolerance_3 = (ImageView) findViewById(R.id.intolerance_3);
        mIntolerance_4 = (ImageView) findViewById(R.id.intolerance_4);
        mIntolerance_5 = (ImageView) findViewById(R.id.intolerance_5);

        mDish_name = (TextView) findViewById(R.id.dish_name);
        mDish_description = (TextView) findViewById(R.id.dish_description);

        clearIntolerances();

        Dish dish = (Dish) getIntent().getSerializableExtra(EXTRA_DISH);

        mDish_name.setText(dish.getName());
        mDish_description.setText(dish.getDescription());
        setIntolerances(dish.getAllergens());

    }

    protected void setIntolerances(LinkedList<Allergen> allergens){

        int allergenSet = 1;
        for(int i = 0; i < allergens.size(); i++){
            switch (allergenSet){
                case 1:
                    mIntolerance_1.setVisibility(View.VISIBLE);
                    mIntolerance_1.setImageResource(allergens.get(i).getIcon());
                    allergenSet += 1;
                    break;
                case 2:
                    mIntolerance_2.setVisibility(View.VISIBLE);
                    mIntolerance_2.setImageResource(allergens.get(i).getIcon());
                    allergenSet += 1;
                    break;
                case 3:
                    mIntolerance_3.setVisibility(View.VISIBLE);
                    mIntolerance_3.setImageResource(allergens.get(i).getIcon());
                    allergenSet += 1;
                    break;
                case 4:
                    mIntolerance_4.setVisibility(View.VISIBLE);
                    mIntolerance_4.setImageResource(allergens.get(i).getIcon());
                    allergenSet += 1;
                    break;
                case 5:
                    mIntolerance_5.setVisibility(View.VISIBLE);
                    mIntolerance_5.setImageResource(allergens.get(i).getIcon());
                    allergenSet += 1;
                    break;
                default:
                    break;
            }
        }
    }

    protected  void clearIntolerances(){
        mIntolerance_1.setVisibility(View.INVISIBLE);
        mIntolerance_2.setVisibility(View.INVISIBLE);
        mIntolerance_3.setVisibility(View.INVISIBLE);
        mIntolerance_4.setVisibility(View.INVISIBLE);
        mIntolerance_5.setVisibility(View.INVISIBLE);
    }
}
