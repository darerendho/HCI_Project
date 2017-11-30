package com.example.darrenpc.hci_imp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.*;


public class NutritionSelectorActivity extends AppCompatActivity {
    private String food_selcected;
    private TextView tv_selecteditem,tv_select,tv_calories;
    private Spinner spinner_food;
    private int calorie_selcected;
    private ArrayList<Integer> list_calories;
    private Button btn_selected,btn_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_selector);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle bundle = getIntent().getExtras();
        food_selcected = bundle.getString("selected_food");
        calorie_selcected = bundle.getInt("selected_calorie");
        tv_selecteditem = (TextView)findViewById(R.id.tv_selected_food);
        tv_select = (TextView)findViewById(R.id.tv_selected);
        tv_calories = (TextView)findViewById(R.id.tv_calories_select);
        spinner_food = (Spinner)findViewById(R.id.spinner_meals);
        btn_selected = (Button)findViewById(R.id.btn_submit);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);

        btn_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(NutritionSelectorActivity.this,NutritionResultsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("calories",(Integer)list_calories.get(calorie_selcected));
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();       //pop current activity to previous one
            }
        });
        setCalories();
        tv_selecteditem.setText(food_selcected);
        tv_calories.setText(String.valueOf(list_calories.get(calorie_selcected)));

    }

    private void setCalories() {
        list_calories = new ArrayList<>();
        list_calories.add(618);
        list_calories.add(475);
        list_calories.add(277);
        list_calories.add(475);
        list_calories.add(419);
    }


}
