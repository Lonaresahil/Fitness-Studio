package com.example.Fitness_Studio.sign_in_process;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.Fitness_Studio.R;
import com.example.Fitness_Studio.mainActivity;
import com.example.Fitness_Studio.water.notification;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity {

    Button btn_login;
    TextInputLayout email_var, user_var, password_var;
    String email_in, password_in;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.login);
        email_var = findViewById(R.id.emailId);
        user_var = findViewById(R.id.username);
        password_var = findViewById(R.id.password);

        firebaseAuth = FirebaseAuth.getInstance();


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = "Time to drink water and get hydrated";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(Login.this).setSmallIcon(R.drawable.ic_water_drop)
                        .setContentTitle("New Water Notification")
                        .setContentText(message)
                        .setAutoCancel(true);

                Intent intent = new Intent(Login.this, notification.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message", message);

                PendingIntent pendingIntent = PendingIntent.getActivities(Login.this, 0, new Intent[]{intent}, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(0, builder.build());


                email_in = email_var.getEditText().getText().toString();
                password_in = password_var.getEditText().getText().toString();

                if (!email_in.isEmpty()) {
                    email_var.setError(null);
                    email_var.setErrorEnabled(false);
                    if (!password_in.isEmpty()) {
                        password_var.setError(null);
                        password_var.setErrorEnabled(false);

                        /////authentication
                        if (Patterns.EMAIL_ADDRESS.matcher(email_in).matches()) {
                            Toast.makeText(Login.this, "processing please wait", Toast.LENGTH_SHORT).show();
                            firebaseAuth.signInWithEmailAndPassword(email_in, password_in).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {


                                    final String userEnteredUsername = user_var.getEditText().getText().toString();

                                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                    DatabaseReference databaseReference = firebaseDatabase.getReference("datauser");

                                    Query check_username = databaseReference.orderByChild("name_data").equalTo(userEnteredUsername);

                                    check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()) {
                                                user_var.setError(null);
                                                user_var.setErrorEnabled(false);
                                                String email = snapshot.child(userEnteredUsername).child("email_data").getValue(String.class);
                                                String name = snapshot.child(userEnteredUsername).child("name_data").getValue(String.class);
                                                String gender = snapshot.child(userEnteredUsername).child("gender_data").getValue(String.class);
                                                String dob = snapshot.child(userEnteredUsername).child("dob_data").getValue(String.class);
                                                String height = snapshot.child(userEnteredUsername).child("height_data").getValue(String.class);
                                                String weight = snapshot.child(userEnteredUsername).child("weight_data").getValue(String.class);

                                                Intent imtent = new Intent(getApplicationContext(), mainActivity.class);

                                                imtent.putExtra("Email", email);
                                                imtent.putExtra("Name", name);
                                                imtent.putExtra("Gender", gender);
                                                imtent.putExtra("Dob", dob);
                                                imtent.putExtra("Height", height);
                                                imtent.putExtra("Weight", weight);

                                                startActivity(imtent);

                                            } else {
                                                user_var.setError("user does not exists");
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        } else {
                            email_var.setError("Invalid Email");
                        }
                    } else {
                        password_var.setError("Please enter valid password");
                    }
                } else {
                    email_var.setError("Please enter Email");
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), mainActivity.class));
            finish();
        }
    }


    public void forgetPassword(View view) {
        Intent intent = new Intent(Login.this, forgotPassword.class);
        startActivity(intent);
    }


}