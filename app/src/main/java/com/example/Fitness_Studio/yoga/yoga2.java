package com.example.Fitness_Studio.yoga;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Fitness_Studio.R;
import com.example.Fitness_Studio.mudra.mudras;

public class yoga2 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga2);




    }

    public void back(View view) {
        Intent intent = new Intent(yoga2.this, yoga.class);
        startActivity(intent);
    }

    public void mudras(View view) {
        Intent intent = new Intent(yoga2.this, mudras.class);
        startActivity(intent);

    }

    public void Yoga(View view) {
        Intent intent = new Intent(yoga2.this, yogas.class);
        startActivity(intent);
    }


}