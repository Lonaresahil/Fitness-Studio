package com.example.Fitness_Studio.weight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Fitness_Studio.R;

public class Weight extends AppCompatActivity {

    Button bt1;
    Button bt2;
    Button bt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        bt1 = (Button) findViewById(R.id.gain);
        bt2 = (Button) findViewById(R.id.maintain);
        bt3 = (Button) findViewById(R.id.lose);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Weight.this,weight_gain.class);
                startActivity(intent);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Weight.this,Weight_maintain.class);
                startActivity(intent);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Weight.this,Weight_lose.class);
                startActivity(intent);
            }
        });
    }


}