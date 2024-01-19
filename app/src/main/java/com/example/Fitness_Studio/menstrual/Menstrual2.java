package com.example.Fitness_Studio.menstrual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.Fitness_Studio.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Menstrual2 extends AppCompatActivity {

    CalendarView calendar;
    TextView date_view,add_date,add_date2,add_date3,add_date4;
    NumberPicker numberPicker;
    int n, remain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menstrual2);
        calendar = (CalendarView) findViewById(R.id.calendar);
        date_view = (TextView) findViewById(R.id.date_view);
        add_date = (TextView) findViewById(R.id.next_period);
        add_date2 = (TextView) findViewById(R.id.ovulation_day);
        add_date3 = (TextView) findViewById(R.id.fertilityWindow_start);
        add_date4 = (TextView) findViewById(R.id.fertilityWindow_end);
        numberPicker = findViewById(R.id.numberpicker);
        numberPicker.setMaxValue(35);
        numberPicker.setMinValue(23);


        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override

            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                String Date = dayOfMonth + "-" + (month + 1) + "-" + year;
                date_view.setText(Date);

                String dt = Date;  // Start date
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(sdf.parse(dt));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                c.add(Calendar.DATE, numberPicker.getValue());
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
                String output = sdf1.format(c.getTime());
                add_date.setText("Your Next Period will be mostly on "+output);

//                ///for Ovulation day
                String dt1 = Date;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Calendar ovu = Calendar.getInstance();
                try {
                    ovu.setTime(simpleDateFormat.parse(dt1));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ovu.add(Calendar.DATE, remain);
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
                String ovulation = simpleDateFormat1.format(ovu.getTime());
                add_date2.setText("Your Ovulation day will be mostly on "+ovulation);

                String dt2 = ovulation;
                SimpleDateFormat fer_sdf = new SimpleDateFormat("dd-MM-yyyy");
                Calendar sta_fer = Calendar.getInstance();
                try {
                    sta_fer.setTime(fer_sdf.parse(dt2));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                sta_fer.add(Calendar.DATE, -7);
                SimpleDateFormat fer_sdf1 = new SimpleDateFormat("dd-MM-yyyy");
                String fertility_sta = fer_sdf1.format(sta_fer.getTime());
                add_date3.setText("Start date of your Fertility Window is from "+fertility_sta);

                String dt3 = ovulation;
                SimpleDateFormat fer_sdf2 = new SimpleDateFormat("dd-MM-yyyy");
                Calendar end_fer = Calendar.getInstance();
                try {
                    end_fer.setTime(fer_sdf2.parse(dt3));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                end_fer.add(Calendar.DATE, 1);
                SimpleDateFormat fer_sdf3 = new SimpleDateFormat("dd-MM-yyyy");
                String fertility_end = fer_sdf3.format(end_fer.getTime());
                add_date4.setText("End date of your Fertility Window is till "+fertility_end);

            }
        });

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                n = newVal;
                remain = (n-14);
            }
        });
    }

}


