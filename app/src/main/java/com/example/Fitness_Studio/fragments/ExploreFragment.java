package com.example.Fitness_Studio.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.Fitness_Studio.calculators.Calculator;
import com.example.Fitness_Studio.menstrual.Menstrual1;
import com.example.Fitness_Studio.R;
import com.example.Fitness_Studio.Tips_Diet;
import com.example.Fitness_Studio.weight.Weight;
import com.example.Fitness_Studio.workout.Workout1;
import com.example.Fitness_Studio.yoga.yoga;


public class ExploreFragment extends Fragment {

    CardView CV1;
    CardView CV2;
    CardView CV3;
    CardView CV4;
    CardView CV5;
    CardView CV6;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        CV1= view.findViewById(R.id.workout);
        CV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Workout1.class);
                startActivity(intent);
            }
        });

        CV2= view.findViewById(R.id.yoga);
        CV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), yoga.class);
                startActivity(intent);
            }
        });

        CV3= view.findViewById(R.id.weight);
        CV3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Weight.class);
                startActivity(intent);
            }
        });

        CV4= view.findViewById(R.id.menstrual);
        CV4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Menstrual1.class);
                startActivity(intent);
            }
        });

        CV5= view.findViewById(R.id.calculator);
        CV5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Calculator.class);
                startActivity(intent);
            }
        });

        CV6= view.findViewById(R.id.diet);
        CV6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Tips_Diet.class);
                startActivity(intent);
            }
        });
        return view;
    }


}