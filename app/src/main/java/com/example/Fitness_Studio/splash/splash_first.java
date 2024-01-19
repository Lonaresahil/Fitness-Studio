package com.example.Fitness_Studio.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.Fitness_Studio.R;

public class splash_first extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_first);
    }

    public void splash1(View view) {
        Intent intent = new Intent(splash_first.this, Splash.class);
        startActivity(intent);
    }
}