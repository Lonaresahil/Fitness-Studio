package com.example.Fitness_Studio.navigation_view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Fitness_Studio.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profile extends AppCompatActivity {

    TextInputLayout email, name, gender, dob, height, weight;
    TextView text_name;
    Button update_btn;
    String user_name,user_email,user_gender,user_dob,user_height,user_weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        text_name = findViewById(R.id.name_tv);
        name = findViewById(R.id.name);
        email = findViewById(R.id.emailId);
        gender = findViewById(R.id.gender);
        dob = findViewById(R.id.dateofBirth);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);


        Intent i = getIntent();
        user_name = i.getStringExtra("Name");
        user_email = i.getStringExtra("Email");
        user_gender = i.getStringExtra("Gender");
        user_dob = i.getStringExtra("Dob");
        user_height = i.getStringExtra("Height");
        user_weight = i.getStringExtra("Weight");

        text_name.setText(user_name);

        name.getEditText().setText(user_name);
        email.getEditText().setText(user_email);
        gender.getEditText().setText(user_gender);
        dob.getEditText().setText(user_dob);
        height.getEditText().setText(user_height);
        weight.getEditText().setText(user_weight);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}