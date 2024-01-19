package com.example.Fitness_Studio.sign_up_process;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Fitness_Studio.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class Verify_Email extends AppCompatActivity {

    TextView verifyMsg,instruction;
    Button verifyEmailbtn, verifiedbtn;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);

        auth = FirebaseAuth.getInstance();

        verifyEmailbtn = findViewById(R.id.verify_btn);
        verifiedbtn = findViewById(R.id.verified);
        verifyMsg = findViewById(R.id.verify_tv);
        instruction = findViewById(R.id.instruct_tv);

        if(auth.getCurrentUser().isEmailVerified()){
            startActivity(new Intent(getApplicationContext(), join.class));
            finish();

        } else {
            verifyEmailbtn.setVisibility(View.VISIBLE);
            verifyMsg.setVisibility(View.VISIBLE);

            verifyEmailbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    auth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Verify_Email.this, "Verification Email is sent to given Email Address", Toast.LENGTH_SHORT).show();
                            verifyEmailbtn.setVisibility(View.GONE);
                            verifyMsg.setVisibility(View.GONE);
                            instruction.setVisibility(View.VISIBLE);
                            verifiedbtn.setVisibility(View.VISIBLE);

//                            startActivity(new Intent(getApplicationContext(), join.class));
//                            finish();
//                            verifiedbtn.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    if(auth.getCurrentUser().isEmailVerified()) {
//                                        startActivity(new Intent(getApplicationContext(), join.class));
//                                        finish();
//                                    }
//                                }
//                            });

                        }
                    });
                }
            });
        }

        verifiedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(auth.getCurrentUser().isEmailVerified()) {
                    startActivity(new Intent(getApplicationContext(), join.class));
                    finish();
                } else{
                    startActivity(new Intent(getApplicationContext(), join.class));
                    finish();
                }
            }
        });

//        verifyEmailbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                auth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(Verify_Email.this, "Verification Email is sent to given Email Address", Toast.LENGTH_SHORT).show();
//                        verifyEmailbtn.setVisibility(View.GONE);
//                        verifyMsg.setVisibility(View.GONE);
//                    }
//                });
//            }
//        });
    }


}