package com.example.darrenpc.hci_imp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class PhysicalActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sProximity;
    private Button btn_start,btn_end;
    private TextView tv_counter,tv_max_pushup;
    private boolean start_stop;
    private int counter = 0;
    private Uri alert;
    private Ringtone ringtone;

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        ringtone = RingtoneManager.getRingtone(getApplicationContext(),alert);


        tv_max_pushup = (TextView) findViewById(R.id.max_pushup);
        tv_counter = (TextView) findViewById(R.id.tv_counter);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_end = (Button) findViewById(R.id.btn_end);

        btn_end.setVisibility(View.INVISIBLE);

        buttonLogic();
    }//end on create


    public void buttonLogic() {
        btn_start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                btn_start.setText("STARTED!!!");
                btn_start.setClickable(false);
                start_stop= true;
                btn_end.setVisibility(View.VISIBLE);


            }

        });
        btn_end.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhysicalActivity.this, PhyResultsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("count",counter); // Send counter value to another page
                intent.putExtras(bundle);
                startActivity(intent);


            }

        });
    }

    @Override //Proximity Sensor
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(start_stop){
            if(sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY){
                if(sensorEvent.values[0] >= -4 && sensorEvent.values[0] <= 4){
                    tv_counter.setText(String.valueOf(counter++));
                    ringtone.play();
                    TimerTask task = new TimerTask(){
                        @Override
                        public void run() {
                            ringtone.stop();
                        }
                    };
                    Timer timer = new Timer();
                    timer.schedule(task,250);

                if(counter > 10){
                    tv_max_pushup.setText(String.valueOf(counter-1));
                }

                }
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void endWorkout(){
        Intent intent = new Intent(this, PhyResultsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("count",counter); // Send counter value to another page
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
