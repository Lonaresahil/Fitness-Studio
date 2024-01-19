package com.example.Fitness_Studio.water;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.Fitness_Studio.R;

public class water_intake extends AppCompatActivity {

    int CurrentProgress = 0;
    double waterLimit = 0, w = 0;
    ProgressBar progressBar;
    TextView text0, limit;
    Button btnreset, calculate;
    EditText weightValue;
    CardView cardview200, cardview300, cardview500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_intake);

        progressBar = findViewById(R.id.water_bar);
        cardview200 = findViewById(R.id.water200);
        cardview300 = findViewById(R.id.water300);
        cardview500 = findViewById(R.id.water500);
        text0 = findViewById(R.id.tv0);
        limit = findViewById(R.id.limit);
        weightValue = findViewById(R.id.weightValue);


        calculate = findViewById(R.id.calculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weiVal = weightValue.getText().toString();
                if (weiVal.isEmpty()){
                    weightValue.setError("Please enter Weight");
                } else {

                w = Double.parseDouble(weightValue.getText().toString());
//
                waterLimit = w * 33;
                limit.setText(Double.toString(waterLimit));
                progressBar.setMax((int) waterLimit);
                weightValue.setVisibility(View.GONE);
                calculate.setVisibility(View.GONE);


                ////notification
                String message = "Time to drink water and get hydrated";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(water_intake.this).setSmallIcon(R.drawable.ic_water_drop)
                        .setContentTitle("New Water Notification")
                        .setContentText(message)
                        .setAutoCancel(true);

                Intent intent = new Intent(water_intake.this, notification.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message", message);

                PendingIntent pendingIntent = PendingIntent.getActivities(water_intake.this, 0, new Intent[]{intent}, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(0, builder.build());


            }
        }

        });


        btnreset = findViewById(R.id.resetProg);

        cardview200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentProgress += 200;
                updateProgressbar();
            }
        });
        cardview300.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentProgress += 300;
                updateProgressbar();
            }
        });
        cardview500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentProgress += 500;
                updateProgressbar();
            }
        });

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentProgress = 0;
                updateProgressbar();
            }
        });

        updateProgressbar();

    }

    private void updateProgressbar() {
        progressBar.setProgress(CurrentProgress);
        text0.setText(String.valueOf(CurrentProgress));
    }


}