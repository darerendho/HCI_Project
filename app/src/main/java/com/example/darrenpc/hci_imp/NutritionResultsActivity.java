package com.example.darrenpc.hci_imp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class NutritionResultsActivity extends AppCompatActivity {
    private ArrayList<BarEntry> BARENTRY ; // Y-axis values
    private int calories;
    private TextView tv_progress,tv_ws,tv_weeks,tv_lbl_consume,tv_consumed,tv_target;
    private Button btn_homepage;

    private BarChart chart ;
    private ArrayList<String> x_label_week;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle bundle = getIntent().getExtras();
        calories = bundle.getInt("calories") + 1980;

        tv_progress = (TextView)findViewById(R.id.textView2);
        tv_ws = (TextView)findViewById(R.id.textView4);
        tv_weeks = (TextView)findViewById(R.id.textView5);
        tv_lbl_consume = (TextView)findViewById(R.id.textView6);
        tv_consumed = (TextView)findViewById(R.id.tv_consumed);
        tv_target = (TextView)findViewById(R.id.textView8);
        btn_homepage = (Button)findViewById(R.id.button);
        chart = (BarChart) findViewById(R.id.nutri_bar_chart);

        BARENTRY = new ArrayList<>();
        addXAxisValues();
        AddValuesToBARENTRY();
        BarDataSet set = new BarDataSet(BARENTRY,"kCal/Day");
        BarData data = new BarData(set);
        data.setBarWidth(0.9f);
        chart.setData(data);
        chart.getXAxis().setDrawLabels(true);
        chart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return x_label_week.get((int)value);
            }
        });

        set.setColors(ColorTemplate.MATERIAL_COLORS);
        chart.setFitBars(true); // make the x-axis fit exactly all bars
        Description description = new Description();
        description.setText("");
        chart.setDescription(description);
        chart.invalidate(); // refresh

        btn_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NutritionResultsActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //i.putExtra("EXIT",true);
                startActivity(i);
            }
        });

        tv_consumed.setText(String.valueOf(calories));
    }

    public void addXAxisValues(){
        x_label_week = new ArrayList<>();     // X-axis labels
        x_label_week.add("MON");
        x_label_week.add("TUE");
        x_label_week.add("WED");
        x_label_week.add("THU");
        x_label_week.add("FRI");
        x_label_week.add("SAT");
        x_label_week.add("SUN");

    }
    private void AddValuesToBARENTRY(){

        BARENTRY.add(new BarEntry(0,2600));
        BARENTRY.add(new BarEntry(1,2500));
        BARENTRY.add(new BarEntry(2,calories));
        BARENTRY.add(new BarEntry(3,0));
        BARENTRY.add(new BarEntry(4,0));
        BARENTRY.add(new BarEntry(5,0));
        BARENTRY.add(new BarEntry(6,0));



    }
}
