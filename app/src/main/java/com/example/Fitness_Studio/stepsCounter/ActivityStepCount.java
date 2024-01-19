package com.example.Fitness_Studio.stepsCounter;

import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Fitness_Studio.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.BreakIterator;
import java.util.ArrayList;

public class ActivityStepCount extends AppCompatActivity implements SensorEventListener, StepListener {

    private ClassDetector simpleClassDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "";
    private int numSteps;
    private TextView TvSteps;
    static MediaPlayer mplay;
    ImageView imagePlay;


    private DatabaseReference mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_stepcount);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleClassDetector = new ClassDetector();
        simpleClassDetector.registerListener(this);

        TvSteps = (TextView) findViewById(R.id.tv_steps);
        Button btnStart = (Button) findViewById(R.id.btn_start);
        Button btnload = (Button) findViewById(R.id.btn_stop);

        mdatabase = FirebaseDatabase.getInstance().getReference();


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sensorManager.registerListener(ActivityStepCount.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
            }
        });

        btnload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                mdatabase.child("step").setValue(numSteps);              ////
                sensorManager.unregisterListener(ActivityStepCount.this);
            }
        });

        imagePlay = findViewById(R.id.playmusic);

        mplay = MediaPlayer.create(getApplicationContext(), R.raw.walk);

        imagePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mplay.isPlaying()) {
                    mplay.pause();
                } else {
                    mplay.start();
                }
            }
        });


    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleClassDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        TvSteps.setText(TEXT_NUM_STEPS + numSteps);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mplay.stop();
        mplay.release();
    }
}