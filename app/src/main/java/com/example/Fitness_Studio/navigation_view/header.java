package com.example.Fitness_Studio.navigation_view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Fitness_Studio.R;
import com.google.android.material.textfield.TextInputLayout;

public class header extends AppCompatActivity {

    TextView user_name;
    TextInputLayout user_var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header);

        user_name = findViewById(R.id.username);
        user_var = findViewById(R.id.name);

        Intent name_intent = getIntent();
        String user_name = name_intent.getStringExtra("Name");

//        user_name.setText(user_name);
        user_var.getEditText().setText(user_name);


    }
}
