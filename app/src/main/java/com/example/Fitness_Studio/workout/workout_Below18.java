package com.example.Fitness_Studio.workout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.Fitness_Studio.R;

public class workout_Below18 extends AppCompatActivity {

    int[]newArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_below18);

        newArray = new int[]{
                R.id.jumping_jack, R.id.toe_touch, R.id.sideways_hop, R.id.front_back_hops, R.id.lunges, R.id.arm_rotation, R.id.high_knees, R.id.dance_step,
        };
    }

    public void imagebuttonclick(View view) {

        for (int i= 0; i<newArray.length; i++){

            if (view.getId() ==newArray[i]){
                int value = i+1;
                Log.i("FIRST",String.valueOf(value));
                Intent intent = new Intent(workout_Below18.this,workout_B18next.class);
                intent.putExtra("value",String.valueOf(value));
                startActivity(intent);

            }
        }
    }
}