package com.example.Fitness_Studio.timer_fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Fitness_Studio.R;


public class Stopwatch extends AppCompatActivity {

    private Chronometer chronometer;
    private long pauseOffset;
    private boolean start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.stopwatch));
        }

        chronometer = findViewById(R.id.time);
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometerChanged) {
                chronometer = chronometerChanged;
            }
        });

    }

    public void startStopChronometer(View view) {
        if (start) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            start = false;
            ((Button) view).setText("Start");
        } else {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            start = true;
            ((Button) view).setText("Stop");
        }
    }

    public void reset_sw(View view) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }

}