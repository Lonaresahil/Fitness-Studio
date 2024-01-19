package com.example.Fitness_Studio.workout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Fitness_Studio.R;

public class workout2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout2);
    }

    public void back(View view) {
        Intent intent = new Intent(workout2.this, Workout1.class);
        startActivity(intent);
    }

    public void below18(View view) {
        Intent intent = new Intent(workout2.this, workout_Below18.class);
        startActivity(intent);
    }

    public void above18(View view) {
        Intent intent = new Intent(workout2.this, workout_Above18.class);
        startActivity(intent);
    }

}