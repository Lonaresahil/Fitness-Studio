package com.example.Fitness_Studio.calculators;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Fitness_Studio.R;

public class Ideal_Weight_calculator extends AppCompatActivity {
    Button btn;
    EditText height, age;
    LinearLayout male_ll, female_ll;
    TextView result;
    ImageView maleimg, femaleimg;

    double h=0, w=0, a=0, idlWei=0;
    String user = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideal_weight_calculator);

        btn=findViewById(R.id.calulate);
        height=findViewById(R.id.height_input);
        age=findViewById(R.id.age_input);
        male_ll= findViewById(R.id.male_ll);
        female_ll= findViewById(R.id.female_ll);
        result= findViewById(R.id.result_line);
        maleimg=findViewById(R.id.male_img);
        femaleimg=findViewById(R.id.female_img);

        male_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maleimg.setColorFilter(getResources().getColor(R.color.blue));
                femaleimg.setColorFilter(getResources().getColor(R.color.grey));
                user="Male";
            }
        });
        female_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                femaleimg.setColorFilter(getResources().getColor(R.color.purple));
                maleimg.setColorFilter(getResources().getColor(R.color.grey));
                user="Female";
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1= height.getText().toString();
                String str3= age.getText().toString();
                if (user.equals("0")){
                    Toast.makeText(Ideal_Weight_calculator.this, "Select your Gender", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(str1)){
                    height.setError("select Height");
                    height.requestFocus();
                    return;
                }
                else if (TextUtils.isEmpty(str3)){
                    age.setError("select Age");
                    age.requestFocus();
                    return;
                }
                else {
                    calculate();
                }
            }
        });
    }
    private void calculate(){
        h=Double.parseDouble(height.getText().toString());
        a=Double.parseDouble(age.getText().toString());

        if (user.equals("Male")){
            idlWei=(50+(0.91*(h-152.4)));
            result.setText(Double.toString(idlWei));
        }
        if (user.equals("Female")){
            idlWei=(45.5+(0.91*(h-152.4)));
            result.setText(Double.toString(idlWei));

        }

    }
}
