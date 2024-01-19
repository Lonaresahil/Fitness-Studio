package com.example.Fitness_Studio.calculators;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Fitness_Studio.R;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Age_Calculator extends AppCompatActivity {

    private Button findDob_btn,calculate_btn;
    private TextView today_txt,dob_txt,age_text;
    String mbirthday,mtoday;
    DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_calculator);

        today_txt = findViewById(R.id.dateText);
        dob_txt = findViewById(R.id.DOB);
        age_text = findViewById(R.id.AGE);

        findDob_btn = findViewById(R.id.dob_btn);
        calculate_btn = findViewById(R.id.cal_btn);

        Calendar calendar = Calendar.getInstance();


        final int Year = calendar.get(Calendar.YEAR);
        final int Month = calendar.get(Calendar.MONTH);
        final int Days = calendar.get(Calendar.DAY_OF_MONTH);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");



        mtoday = simpleDateFormat.format(Calendar.getInstance().getTime());

        today_txt.setText("Today :" + mtoday);

        findDob_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), dateSetListener, Year, Month, Days);
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                datePickerDialog.show();
            } //Cleared
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                mbirthday = dayOfMonth + "/" + month + "/" + year;
                dob_txt.setText("Birthdate: " + mbirthday);

            }
        };
        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mbirthday == null) {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Date of Birth",
                            Toast.LENGTH_LONG).show();


                } else {
                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date date1 = simpleDateFormat1.parse(mbirthday);
                        Date date2 = simpleDateFormat1.parse(mtoday);
                        long startDate = date1.getTime();
                        long endDate = date2.getTime();
                        Period period = new Period(startDate, endDate, PeriodType.yearMonthDay());


                        int Years = period.getYears();
                        int Months = period.getMonths();
                        int Days = period.getDays();
                        age_text.setText(+ Years + "Years/" + Months + "Months/" + Days + "Days");


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


}