package com.example.sqassist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page);

        Button redirectToLogin = findViewById(R.id.toLoginBtn);
        redirectToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_one = new Intent(MainActivity.this, Login.class);
                startActivity(intent_one);
            }
        });

        Button redirectToSignUp = findViewById(R.id.toSignupBtn);
        redirectToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_two = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent_two);
            }
        });
    }
}