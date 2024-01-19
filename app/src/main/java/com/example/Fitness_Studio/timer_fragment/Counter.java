package com.example.Fitness_Studio.timer_fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Fitness_Studio.R;


public class Counter extends AppCompatActivity {

    TextView count;
    Button plus_count, minus_count, reset_count;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.counter));
        }
        count = (TextView) findViewById(R.id.count);
        plus_count = (Button) findViewById(R.id.plus_count);
        minus_count = (Button) findViewById(R.id.minus_count);
        reset_count = (Button) findViewById(R.id.reset_count);

        plus_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                count.setText(Integer.toString(counter));
            }
        });

        minus_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                count.setText(Integer.toString(counter));
            }
        });

        reset_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = 0;
                count.setText(Integer.toString(counter));
            }
        });
    }
}