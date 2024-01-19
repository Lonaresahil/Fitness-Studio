package com.example.Fitness_Studio;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class Tips_Diet extends AppCompatActivity {

    ListView listview;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_diet);



        back=(ImageView) findViewById(R.id.go_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        String[] dstring = getResources().getStringArray(R.array.detail_story);

        listview = findViewById(R.id.list);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,R.layout.activity_tips_diet_row,R.id.text1, dstring);

        listview.setAdapter(adapter1);   //////// problem    sirf 1 hi display ho raha h

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}