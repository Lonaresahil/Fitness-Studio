package com.example.Fitness_Studio.sign_up_process;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Fitness_Studio.R;
import com.example.Fitness_Studio.store_data.storing_data;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Started extends AppCompatActivity {

    TextInputLayout username_var, email_var, password_var;
    Button btn_nxt;
    String username, password_in, email_in,username_data,email_data,password_data;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started);

        username_var = findViewById(R.id.username);
        email_var = findViewById(R.id.emailId);
        password_var = findViewById(R.id.password);
        btn_nxt = findViewById(R.id.nxt_button);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = username_var.getEditText().getText().toString();
                email_in = email_var.getEditText().getText().toString();
                password_in = password_var.getEditText().getText().toString();

                if (!username.isEmpty()) {
                    username_var.setError(null);
                    username_var.setErrorEnabled(false);
                    if (!email_in.isEmpty()) {
                        email_var.setError(null);
                        email_var.setErrorEnabled(false);
                        if (!password_in.isEmpty()) {
                            password_var.setError(null);
                            password_var.setErrorEnabled(false);
                            if (Patterns.EMAIL_ADDRESS.matcher(email_in).matches()) {
                                Toast.makeText(Started.this, "please wait processing", Toast.LENGTH_SHORT).show();

        // Email Authenticatiopn
                                firebaseAuth.createUserWithEmailAndPassword(email_in, password_in).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        startActivity(new Intent(getApplicationContext(), Verify_Email.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Started.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

        //Realtime Database
                                firebaseDatabase = FirebaseDatabase.getInstance();
                                reference = firebaseDatabase.getReference("datauser");

                                 username_data = username_var.getEditText().getText().toString();
                                 email_data = email_var.getEditText().getText().toString();
                                 password_data = password_var.getEditText().getText().toString();

                                storing_data storing_datass = new storing_data(username_data,email_data,password_data);

                                reference.child(username_data).setValue(storing_datass);

                                Toast.makeText(Started.this, "Saved to data base", Toast.LENGTH_SHORT).show();


                            } else {
                                email_var.setError("Invalid Email");
                            }
                        } else {
                            password_var.setError("Please enter valid password");
                        }
                    } else {
                        email_var.setError("Please enter Email");
                    }
                } else {
                    username_var.setError("Please enter Email");
                }


            }
        });


    }


}