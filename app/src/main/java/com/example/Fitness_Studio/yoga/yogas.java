package com.example.Fitness_Studio.yoga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.Fitness_Studio.R;

public class yogas extends AppCompatActivity {
    int[]newArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yogas);

        newArray = new int[]{
                R.id.ustrasana, R.id.yoga_squat_pose, R.id.triagle_pose_down, R.id.triangle_pose_up, R.id.child_pose, R.id.bend_toe_touch, R.id.virabhadrasana, R.id.vrikshasana,
        };
    }

    public void imagebuttonclick(View view) {

        for (int i= 0; i<newArray.length; i++){

            if (view.getId() ==newArray[i]){
                int value = i+1;
                Log.i("FIRST",String.valueOf(value));
                Intent intent = new Intent(yogas.this,yogasnext.class);
                intent.putExtra("value",String.valueOf(value));
                startActivity(intent);

            }
        }
    }
}