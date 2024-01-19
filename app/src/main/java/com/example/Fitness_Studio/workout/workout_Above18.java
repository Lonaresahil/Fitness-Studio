package com.example.Fitness_Studio.workout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Fitness_Studio.R;

public class workout_Above18 extends AppCompatActivity {

    ImageView imagePlay;
    static MediaPlayer mplay;
    int[]newArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_above18);

        newArray = new int[]{
                R.id.jumping_jack, R.id.mountain_climber, R.id.push_up, R.id.crunches, R.id.leg_raise, R.id.toe_touch_crunches, R.id.burpee, R.id.pull_up,
        };

        imagePlay = findViewById(R.id.playmusic);

        mplay = MediaPlayer.create(getApplicationContext(), R.raw.workout);

        imagePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mplay.isPlaying()) {
                    mplay.pause();
                } else {
                    mplay.start();
                }
            }
        });



    }

    public void imagebuttonclick(View view) {

        for (int i= 0; i<newArray.length; i++){

            if (view.getId() ==newArray[i]){
                int value = i+1;
                Log.i("FIRST",String.valueOf(value));
                Intent intent = new Intent(workout_Above18.this,workout_A18next.class);
                intent.putExtra("value",String.valueOf(value));
                startActivity(intent);

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mplay.stop();
        mplay.release();
    }


}