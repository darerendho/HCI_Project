package com.example.darrenpc.hci_imp;

import android.content.Intent;
import android.os.Bundle;
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

public class PhyResultsActivity extends AppCompatActivity {

    private ArrayList<BarEntry> BARENTRY ; // Y-axis values
    private BarChart chart ;
    private ArrayList<String> x_label_week;
    private int counter_value;
    private Button btn_return_home;
    private TextView tv_exp,tv_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phy_results);
        Bundle bundle = getIntent().getExtras();
        counter_value = bundle.getInt("count");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv_exp = (TextView)findViewById(R.id.tv_exp_physical);
        tv_progress = (TextView)findViewById(R.id.tv_progress);
        chart = (BarChart) findViewById(R.id.bar_chart);
        btn_return_home = (Button)findViewById(R.id.btn_homepage);


        BARENTRY = new ArrayList<>();
        addLabelsToXAxis();
        AddValuesToBARENTRY();
        BarDataSet set = new BarDataSet(BARENTRY,"Push Ups/Day");
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

        btn_return_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PhyResultsActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //i.putExtra("EXIT",true);
                startActivity(i);
            }
        });

    }

    private void addLabelsToXAxis() {
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

        BARENTRY.add(new BarEntry(0,44));
        BARENTRY.add(new BarEntry(1,50));
        BARENTRY.add(new BarEntry(2,counter_value));
        BARENTRY.add(new BarEntry(3,0));
        BARENTRY.add(new BarEntry(4,0));
        BARENTRY.add(new BarEntry(5,0));
        BARENTRY.add(new BarEntry(6,0));



    }
}
