package com.example.Fitness_Studio.workout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Fitness_Studio.R;

public class Workout1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout1);

    }

    public void EnterWorkout(View view) {
        Intent intent = new Intent(Workout1.this, workout2.class);
        startActivity(intent);
    }

}