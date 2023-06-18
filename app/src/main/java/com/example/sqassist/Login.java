package com.example.sqassist;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class Login extends AppCompatActivity{

    EditText emailForLogin, passForLogin;
    Button loginBtn;
    DBHelper DB;
    SharedPreference sharedPreference;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        TextView SignUpLink = findViewById(R.id.signUpLink);
        SignUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_one = new Intent(Login.this, SignUp.class);
                startActivity(intent_one);
            }
        });


        emailForLogin = findViewById(R.id.emailForLogin);
        passForLogin = findViewById(R.id.passwordForLogin);
        loginBtn = findViewById(R.id.loginButton);
        DB = new DBHelper(this);
        sharedPreference = new SharedPreference(Login.this);

        //login to app by matching inputs with data in database after clicking login button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailForLogin.getText().toString();
                String pass = passForLogin.getText().toString();

                //Check if the fields are empty or having invalid credentials else just login
                if(email.equals("")||pass.equals(""))
                    Toast.makeText(Login.this, "Please enter all the fields!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkEmailPassword(email, pass);
                    if(checkuserpass==true){
                        Toast.makeText(Login.this, "Sign in successfully!", Toast.LENGTH_SHORT).show();
                        sharedPreference.setEmail(email);
                        sharedPreference.checkUsername(email);
                        String logUsername = sharedPreference.getUsername();
                        Intent intent  = new Intent(getApplicationContext(), NavFragment.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(Login.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}