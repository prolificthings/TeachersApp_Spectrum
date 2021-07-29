package com.example.teachers_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    Button rgstrBtn;
    TextInputEditText password, email;
    FirebaseAuth mAuth;
    TextView rgstr2login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rgstrBtn = findViewById(R.id.rgstr_btn);
        password = findViewById(R.id.passwordTxt);
        email = findViewById(R.id.emailTxt);
        rgstr2login = findViewById(R.id.rgstr2loginTxt);
        mAuth = FirebaseAuth.getInstance();

        rgstrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               createUser();
            }
        });

        rgstr2login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rgstr2login.setPaintFlags(rgstr2login.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void createUser() {
        String Email = email.getText().toString();
        String Password = password.getText().toString();

        if (TextUtils.isEmpty(Email)){
            email.setError("Please enter your email");
            email.requestFocus();
        } else if (TextUtils.isEmpty(Password)){
            password.setError("Please enter your password");
            password.requestFocus();
        } else{
            mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "User registered successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        finish();
                    } else{
                        Toast.makeText(RegisterActivity.this, "Registration unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}