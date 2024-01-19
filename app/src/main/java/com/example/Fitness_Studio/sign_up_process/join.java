package com.example.Fitness_Studio.sign_up_process;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Fitness_Studio.R;
import com.example.Fitness_Studio.mainActivity;
import com.example.Fitness_Studio.store_data.storing_data;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


public class join extends AppCompatActivity {

    TextInputLayout email, username, gender, dob, height, weight;
    TextView email_not_verified;
    Button verifyBtn, save_btn;
    String email_in, name_in, gender_in, dob_in, height_in, weight_in;
    TextInputEditText mDateFormat;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        email_not_verified = findViewById(R.id.not_verified_tv);
        verifyBtn = findViewById(R.id.verify_now_btn);

        email = findViewById(R.id.emailId);
        username = findViewById(R.id.name);
        gender = findViewById(R.id.gender);
        dob = findViewById(R.id.dateofBirth);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        save_btn = findViewById(R.id.save_data);

        final Calendar calendar= Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        mDateFormat = findViewById(R.id.date_format);

        mDateFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(join.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, onDateSetListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = dayOfMonth+"/"+month+"/"+year;
                mDateFormat.setText(date);
            }
        };


        ///// Email Verification
        if (!user.isEmailVerified()){
            email_not_verified.setVisibility(View.VISIBLE);
            verifyBtn.setVisibility(View.VISIBLE);

            verifyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            email_not_verified.setVisibility(View.GONE);
                            verifyBtn.setVisibility(View.GONE);
                            Toast.makeText(join.this, "Verification of Email has been done", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("tag","onFailure: Email not sent" + e.getMessage());
                        }
                    });
                }
            });
        }

        ///// Save Details
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email_in = email.getEditText().getText().toString();
                name_in =username.getEditText().getText().toString();
                gender_in =gender.getEditText().getText().toString();
                dob_in =dob.getEditText().getText().toString();
                height_in =height.getEditText().getText().toString();
                weight_in =weight.getEditText().getText().toString();

                if (!email_in.isEmpty()) {
                    email.setError(null);
                    email.setErrorEnabled(false);
                    if (!name_in.isEmpty()) {
                        username.setError(null);
                        username.setErrorEnabled(false);
                        if (!gender_in.isEmpty()) {
                            gender.setError(null);
                            gender.setErrorEnabled(false);
                            if (!dob_in.isEmpty()) {
                                dob.setError(null);
                                dob.setErrorEnabled(false);
                                if (!height_in.isEmpty()) {
                                    height.setError(null);
                                    height.setErrorEnabled(false);
                                    if (!weight_in.isEmpty()) {
                                        weight.setError(null);
                                        weight.setErrorEnabled(false);

                //// Database
                                        firebaseDatabase = FirebaseDatabase.getInstance();
                                        reference = firebaseDatabase.getReference("datauser");

                                        String email_data = email.getEditText().getText().toString();
                                        String name_data =username.getEditText().getText().toString();
                                        String gender_data =gender.getEditText().getText().toString();
                                        String dob_data =dob.getEditText().getText().toString();
                                        String height_data =height.getEditText().getText().toString();
                                        String weight_data =weight.getEditText().getText().toString();

                                        storing_data storing_datass = new storing_data(email_data,name_data,gender_data,dob_data,height_data,weight_data);

                                        reference.child(name_data).setValue(storing_datass);


                                        Toast.makeText(join.this, "Saved to data base", Toast.LENGTH_SHORT).show();


                                        Intent imtent = new Intent(join.this, mainActivity.class);

                                        imtent.putExtra("Email",email_data);
                                        imtent.putExtra("Name",name_data);
                                        imtent.putExtra("Gender",gender_data);
                                        imtent.putExtra("Dob",dob_data);
                                        imtent.putExtra("Height",height_data);
                                        imtent.putExtra("Weight",weight_data);

                                        startActivity(imtent);

                                    } else {
                                        weight.setError("Please enter Weight");
                                    }
                                } else {
                                    height.setError("Please enter Height");
                                }
                            } else {
                                dob.setError("Please enter date of Birth");
                            }
                        } else  {
                            gender.setError("Please enter Gender");
                        }
                    } else {
                        username.setError("Please enter Name");
                    }
                } else {
                    email.setError("Please enter Username");
                }

            }
        });
    }


}