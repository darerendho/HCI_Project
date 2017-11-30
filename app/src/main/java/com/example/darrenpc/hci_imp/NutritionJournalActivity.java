package com.example.darrenpc.hci_imp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class NutritionJournalActivity extends AppCompatActivity {
    private TextView tv_select,tv_left,tv_calories;
    private Spinner foodSpinner;
    private Button btn_select_food;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_journal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv_select = (TextView)findViewById(R.id.tv_select);
        foodSpinner = (Spinner) findViewById(R.id.spinnerFood);
        btn_select_food = (Button)findViewById(R.id.btn_select_food);
        tv_left = (TextView) findViewById(R.id.tv_calories_left);
        tv_calories = (TextView)findViewById(R.id.tv_calories);

//        String[] foodArray = getResources().getStringArray(R.array.food_array);
//        Integer[] intArray = new Integer[foodArray.length];
//
//        for(int i = 1;i<foodArray.length;i++){
//            intArray[i] = Integer.parseInt(foodArray[i]);
////        }
//        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,R.layout.support_simple_spinner_dropdown_item,intArray);
//        foodSpinner.setAdapter(adapter);

        //int position = foodSpinner.getSelectedItemPosition();
        btn_select_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NutritionJournalActivity.this, NutritionSelectorActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("selected_food",foodSpinner.getSelectedItem().toString()); // Send counter value to another page
                bundle.putInt("selected_calorie",(Integer)foodSpinner.getSelectedItemPosition());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

}
