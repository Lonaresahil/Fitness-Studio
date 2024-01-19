package com.example.Fitness_Studio.menstrual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.Fitness_Studio.R;

public class Menstrual1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menstrual1);
    }

    public void EnterMenstrual(View view) {

        Intent intent = new Intent(Menstrual1.this, Menstrual2.class);
        startActivity(intent);
    }
}