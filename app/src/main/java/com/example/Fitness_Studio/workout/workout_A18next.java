package com.example.Fitness_Studio.workout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Fitness_Studio.R;

public class workout_A18next extends AppCompatActivity {

    String buttonvalue;
    Button startBtn;
    private CountDownTimer countDownTimer;
    TextView mtextView;
    MediaPlayer mp;

    private boolean mTimeRunning;
    private long mTimeLeftinMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_a18next);


        Intent intent = getIntent();
        buttonvalue = intent.getStringExtra("value");

        int intvalue = Integer.valueOf(buttonvalue);

        switch (intvalue){

            case 1:
                setContentView(R.layout.activity_work_jumpingjacks);
                break;
            case 2:
                setContentView(R.layout.activity_work_mountainclimber);
                break;
            case 3:
                setContentView(R.layout.activity_work_pushups);
                break;
            case 4:
                setContentView(R.layout.activity_work_crunches);
                break;
            case 5:
                setContentView(R.layout.activity_work_legraise);
                break;
            case 6:
                setContentView(R.layout.activity_work_toetouchcrunches);
                break;
            case 7:
                setContentView(R.layout.activity_work_burpees);
                break;
            case 8:
                setContentView(R.layout.activity_work_pullups);
                break;


        }




        startBtn = findViewById(R.id.button_start_pause);
        mtextView = findViewById(R.id.timer);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimeRunning) {
                    stoptimer();
                } else {
                    startTimer();
                }
            }
        });

    }


    private void stoptimer(){
        countDownTimer.cancel();
        mTimeRunning=false;
        startBtn.setText("START");

    }

    private void startTimer(){
        final CharSequence value1 = mtextView.getText();
        String num1 = value1.toString();
        String num2 = num1.substring(0,2);
        String num3 = num1.substring(3,5);

        final int number = Integer.valueOf(num2)* 60+ Integer.valueOf(num3);
        mTimeLeftinMillis = number*1000;

        countDownTimer = new CountDownTimer(mTimeLeftinMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftinMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                int newValue = Integer.valueOf(buttonvalue)+1;
                if (newValue<=7){
                    Intent intent = new Intent(workout_A18next.this, workout_A18next.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value",String.valueOf(newValue));
                    startActivity(intent);
                }
                else {
                    newValue = 1;
                    Intent intent = new Intent(workout_A18next.this, workout_A18next.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value",String.valueOf(newValue));
                    startActivity(intent);
                }
            }
        }.start();
        startBtn.setText("pause");
        mTimeRunning = true;
    }

    private void updateTimer(){
        mp = MediaPlayer.create(this, R.raw.countdown);  ///////sound

        int minutes = (int) mTimeLeftinMillis/60000;
        int seconds = (int) mTimeLeftinMillis%60000 /1000;

        String timeleftText="";
        if (minutes<10)
            timeleftText = "0";
        timeleftText = timeleftText+minutes+":";
        if (seconds<10)
            timeleftText+= "0";
        timeleftText+= seconds;
        mtextView.setText(timeleftText);
        if (seconds==3)
            mp.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
        mp.release();
    }
}