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

public class BMI_Calculator extends AppCompatActivity {

    Button btn;
    EditText height, weight, age;
    LinearLayout male_ll, female_ll;
    TextView result,result2;
    ImageView maleimg, femaleimg;

    double h=0, w=0, a=0, bmi=0;
    String user = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        btn=findViewById(R.id.calulate);
        height=findViewById(R.id.height_input);
        weight=findViewById(R.id.weight_input);
        age=findViewById(R.id.age_input);
        male_ll= findViewById(R.id.male_ll);
        female_ll= findViewById(R.id.female_ll);
        result= findViewById(R.id.result_line);
        result2=findViewById(R.id.result_line2);
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
                String str2= weight.getText().toString();
                String str3= age.getText().toString();
                if (user.equals("0")){
                    Toast.makeText(BMI_Calculator.this, "Select your Gender", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(str1)){
                    height.setError("select Height");
                    height.requestFocus();
                    return;
                }
                else if (TextUtils.isEmpty(str2)){
                    weight.setError("select Weight");
                    weight.requestFocus();
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
        w=Double.parseDouble(weight.getText().toString());
        a=Double.parseDouble(age.getText().toString());

        if (user.equals("Male")){
            bmi=(w/(h*h)*10000);
            result.setText(Double.toString(bmi));
            if (bmi<18.5){
                result2.setText("you are Underweight");
            } else if (bmi>18.5 &&bmi<24.9){
                result2.setText("you are Healthy Weight");
            } else if (bmi>25 &&bmi<29.9){
                result2.setText("you are OverWeight");
            } else {
                result2.setText("Obesity");
            }
        }
        if (user.equals("Female")){
            bmi=(w/(h*h)*10000);
            result.setText(Double.toString(bmi));
            if (bmi<18.5){
                result2.setText("you are Underweight");
            } else if (bmi>18.5 &&bmi<24.9){
                result2.setText("you are Healthy Weight");
            } else if (bmi>25 &&bmi<29.9){
                result2.setText("you are OverWeight");
            } else {
                result2.setText("Obesity");
            }
        }

    }
}
