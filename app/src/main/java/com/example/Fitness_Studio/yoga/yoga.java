package com.example.Fitness_Studio.yoga;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Fitness_Studio.R;

public class yoga extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga);
    }

    public void EnterYoga(View view) {
        Intent intent = new Intent(yoga.this, yoga2.class);
        startActivity(intent);
    }
}