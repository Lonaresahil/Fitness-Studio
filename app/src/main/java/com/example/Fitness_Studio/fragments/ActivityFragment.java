package com.example.Fitness_Studio.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.Fitness_Studio.R;
import com.example.Fitness_Studio.calendar;
import com.example.Fitness_Studio.notes.activities.notes1;


public class ActivityFragment extends Fragment {

    LinearLayout ll1;
    LinearLayout ll2;
    LinearLayout ll3;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activity, container, false);


        ll1= view.findViewById(R.id.ll_calender);
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), calendar.class);
                startActivity(intent);
            }
        });
        ll1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ll1.setBackgroundResource(R.color.light_grey1);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    ll1.setBackgroundColor(Color.WHITE);
                }
                return false;
            }
        });

        ll2= view.findViewById(R.id.ll_notes);
        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), notes1.class);
                startActivity(intent);
            }
        });
        ll2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ll2.setBackgroundResource(R.color.light_grey1);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    ll2.setBackgroundColor(Color.WHITE);
                }
                return false;
            }
        });


        return view;
    }
}