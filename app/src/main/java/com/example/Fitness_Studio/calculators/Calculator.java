package com.example.Fitness_Studio.calculators;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Fitness_Studio.R;

public class Calculator extends AppCompatActivity {

    LinearLayout ll1;
    LinearLayout ll2;
    LinearLayout ll3;
    LinearLayout ll4;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);




        ll1= findViewById(R.id.bmi_cal);
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Calculator.this, BMI_Calculator.class);
                startActivity(intent);
            }
        });
        ll1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ll1.setBackgroundResource(R.color.light_grey1);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    ll1.setBackgroundColor(Color.WHITE);
                }
                return false;
            }
        });




        ll2= findViewById(R.id.calorie_cal);
        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Calculator.this, calorie_calculator.class);
                startActivity(intent);
            }
        });
        ll2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    ll2.setBackgroundResource(R.color.light_grey1);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    ll2.setBackgroundColor(Color.WHITE);
                }
                return false;
            }
        });




        ll3= findViewById(R.id.ideal_weight_cal);
        ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Calculator.this, Ideal_Weight_calculator.class);
                startActivity(intent);
            }
        });
        ll3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    ll3.setBackgroundResource(R.color.light_grey1);
                }else if (event.getAction() == MotionEvent.ACTION_UP) {
                    ll3.setBackgroundColor(Color.WHITE);
                }
                return false;
            }
        });



        ll4= findViewById(R.id.Age_cal);
        ll4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Calculator.this, Age_Calculator.class);
                startActivity(intent);
            }
        });
        ll4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    ll4.setBackgroundResource(R.color.light_grey1);
                }else if (event.getAction() == MotionEvent.ACTION_UP) {
                    ll4.setBackgroundColor(Color.WHITE);
                }
                return false;
            }
        });

    }

}