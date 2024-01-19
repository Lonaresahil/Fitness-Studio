package com.example.Fitness_Studio.sign_in_process;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Fitness_Studio.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;


public class forgotPassword extends AppCompatActivity {

    TextInputLayout request_email;
    Button request_btn;
    String email;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        firebaseAuth = FirebaseAuth.getInstance();


        request_email = findViewById(R.id.registered_email);
        request_btn = findViewById(R.id.request_btn);

        request_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = request_email.getEditText().getText().toString();

                if (email.isEmpty()) {
                    request_email.setError("Required Field");
                } else {
                    firebaseAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(forgotPassword.this, "Email containing Reset Password link sent", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(forgotPassword.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                 }
            }
        });


    }
}