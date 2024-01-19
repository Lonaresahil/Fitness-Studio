package com.example.Fitness_Studio.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.Fitness_Studio.R;
import com.example.Fitness_Studio.stepsCounter.ActivityStepCount;
import com.example.Fitness_Studio.water.water_intake;

public class HomeFragment extends Fragment {

    View step_view, water_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        step_view = view.findViewById(R.id.step_view);
        step_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityStepCount.class);
                startActivity(intent);
            }
        });

       water_view = view.findViewById(R.id.water);
       water_view.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getActivity(), water_intake.class);
               startActivity(intent);


           }
       });

        return view;

    }

}