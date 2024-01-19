package com.example.Fitness_Studio.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Fitness_Studio.R;
import com.example.Fitness_Studio.sign_up_process.Started;
import com.example.Fitness_Studio.sign_in_process.Login;


public class Splash extends AppCompatActivity {

    Button splash_Btn1, splash_Btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        splash_Btn1 = (Button) findViewById(R.id.splash_Button1);
        splash_Btn2 = (Button) findViewById(R.id.splash_button2);


      splash_Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Splash.this, Started.class);
                startActivity(intent);

            }
        });

        splash_Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Splash.this, Login.class);
                startActivity(intent);

            }
        });

}


}