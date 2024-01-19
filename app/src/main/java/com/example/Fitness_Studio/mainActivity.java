package com.example.Fitness_Studio;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.example.Fitness_Studio.fragments.ActivityFragment;
import com.example.Fitness_Studio.fragments.ExploreFragment;
import com.example.Fitness_Studio.fragments.HomeFragment;
import com.example.Fitness_Studio.fragments.TimerFragment;
import com.example.Fitness_Studio.navigation_view.profile;
import com.example.Fitness_Studio.navigation_view.resetPassword;
import com.example.Fitness_Studio.sign_in_process.Login;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class mainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    BottomNavigationView bottom_navigation;
    TextView mtitle;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottom_navigation = findViewById(R.id.bottom_navigation);
        FragmentTransaction homeTrans = getSupportFragmentManager().beginTransaction();
        homeTrans.replace(R.id.frame_content, new HomeFragment());
        homeTrans.commit();


        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.home:
                        FragmentTransaction homeTrans = getSupportFragmentManager().beginTransaction();
                        homeTrans.replace(R.id.frame_content, new HomeFragment());
                       homeTrans.commit();
                        break;

                    case R.id.timer:
                        FragmentTransaction timerTrans = getSupportFragmentManager().beginTransaction();
                        timerTrans.replace(R.id.frame_content, new TimerFragment());
                        timerTrans.commit();
                        break;
                    case R.id.explore:
                        FragmentTransaction exploreTrans = getSupportFragmentManager().beginTransaction();
                        exploreTrans.replace(R.id.frame_content, new ExploreFragment());
                        exploreTrans.commit();
                        break;
                    case R.id.activity:
                        FragmentTransaction activityTrans = getSupportFragmentManager().beginTransaction();
                        activityTrans.replace(R.id.frame_content, new ActivityFragment());
                        activityTrans.commit();
                        break;

                }

                return true;
            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.profile:

                Intent imtent = getIntent();
                String user_name = imtent.getStringExtra("Name");
                String user_email = imtent.getStringExtra("Email");
                String user_gender = imtent.getStringExtra("Gender");
                String user_dob = imtent.getStringExtra("Dob");
                String user_height = imtent.getStringExtra("Height");
                String user_weight = imtent.getStringExtra("Weight");

                Intent i = new Intent(mainActivity.this, profile.class);
                i.putExtra("Email",user_email);
                i.putExtra("Name",user_name);
                i.putExtra("Gender",user_gender);
                i.putExtra("Dob",user_dob);
                i.putExtra("Height",user_height);
                i.putExtra("Weight",user_weight);

                startActivity(i);
                finish();
                break;

            case R.id.reset:
                Intent in = new Intent(mainActivity.this, resetPassword.class);
                startActivity(in);
                break;

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                break;

            case R.id.exit:
                AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity.this);
                builder.setMessage("Do you want to exit");
                builder.setCancelable(true);

                builder.setNegativeButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setPositiveButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alterDialog = builder.create();
                alterDialog.show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

//    private void readData(String user_name) {
//
//        reference = FirebaseDatabase.getInstance().getReference("datauser");
//        reference.child(user_name).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (task.isSuccessful()){
//                    if (task.getResult().exists()){
//                        Toast.makeText(mainActivity.this, "Successfully read", Toast.LENGTH_SHORT).show();
//                        DataSnapshot dataSnapshot = task.getResult();
//                        String name = String.valueOf(dataSnapshot.child("name_data").getValue());
//                        String gender = String.valueOf(dataSnapshot.child("gender_data").getValue());
//                        String dob = String.valueOf(dataSnapshot.child("dob_data").getValue());
//                        String height = String.valueOf(dataSnapshot.child("height_data").getValue());
//                        String weight = String.valueOf(dataSnapshot.child("weight_data").getValue());
//
//                        Intent intent = new Intent(getApplicationContext(), profile.class);
//                        intent.putExtra("name",name);
//                        intent.putExtra("gender",gender);
//                        intent.putExtra("dob",dob);
//                        intent.putExtra("height",height);
//                        intent.putExtra("weight",weight);
//                        startActivity(intent);
//
//                    } else {
//                        Toast.makeText(mainActivity.this, "user does not exists", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(mainActivity.this, "failed to read data", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//    }
}