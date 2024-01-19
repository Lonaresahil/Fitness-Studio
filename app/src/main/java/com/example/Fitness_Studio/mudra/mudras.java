package com.example.Fitness_Studio.mudra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.Fitness_Studio.R;

public class mudras extends AppCompatActivity {

    LinearLayout ll1;
    LinearLayout ll2;
    LinearLayout ll3;
    LinearLayout ll4;
    LinearLayout ll5;
    LinearLayout ll6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mudras);

        ll1 = findViewById(R.id.Gyana);
        ll2 = findViewById(R.id.Vayu);
        ll3 = findViewById(R.id.Prithvi);
        ll4 = findViewById(R.id.Surya);
        ll5 = findViewById(R.id.Varun);
        ll6 = findViewById(R.id.Prana);

        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mudras.this, gyana_mudra.class);
                startActivity(intent);
            }
        });

        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mudras.this, mudra_vayu.class);
                startActivity(intent);
            }
        });

        ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mudras.this, mudra_prithvi.class);
                startActivity(intent);
            }
        });

        ll4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mudras.this, mudra_surya.class);
                startActivity(intent);
            }
        });

        ll5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mudras.this, mudra_varun.class);
                startActivity(intent);
            }
        });

        ll6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mudras.this, mudra_prana.class);
                startActivity(intent);
            }
        });



    }

}