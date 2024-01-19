package com.example.Fitness_Studio.navigation_view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Fitness_Studio.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class resetPassword extends AppCompatActivity {

    TextView resetPasswordTV;
    EditText newuserpassword, confirmuserpassword;
    Button savepassword;
    String password_new,password_con;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        newuserpassword = findViewById(R.id.newPassword);
        confirmuserpassword = findViewById(R.id.confirmresetPassword);

        user = FirebaseAuth.getInstance().getCurrentUser();

        savepassword = (Button) findViewById(R.id.save);

        savepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password_new = newuserpassword.getText().toString();
                password_con = confirmuserpassword.getText().toString();

                if (password_new.isEmpty()){
                    newuserpassword.setError("Required Field.");
                    return;
                }

                if (password_con.isEmpty()){
                    confirmuserpassword.setError("Required Field.");
                    return;
                }

                if (!password_new.equals(password_con)){
                    confirmuserpassword.setError("Password does not Match");
                    return;
                }

                user.updatePassword(password_new).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(resetPassword.this, "Password Updated", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(resetPassword.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}