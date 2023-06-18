package com.example.sqassist;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class SignUp extends AppCompatActivity{

    EditText usernameForReg, emailForReg, passForReg;
    Button signupBtn;
    DBHelper DB;
    @Override
    protected  void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page);

        //Redirect to login page
        TextView LoginLink = findViewById(R.id.loginLink);
        LoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_one = new Intent(SignUp.this, Login.class);
                startActivity(intent_one);
            }
        });

        usernameForReg = findViewById(R.id.userNameForRegister);
        emailForReg = findViewById(R.id.emailForRegister);
        passForReg = findViewById(R.id.passwordForRegister);
        signupBtn = findViewById(R.id.signUpBtn);
        DB = new DBHelper(this);

        //Register user's data to database after clicking sign up button
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = usernameForReg.getText().toString();
                String email = emailForReg.getText().toString();
                String pass = passForReg.getText().toString();

                //Check if the fields are empty or already registered in database else then register
                if(user.equals("")||pass.equals("")||email.equals(""))
                    Toast.makeText(SignUp.this, "Please enter all the fields!", Toast.LENGTH_SHORT).show();

                else{
                    Boolean checkuser = DB.checkEmail(email);
                    if(checkuser == false){
                        Boolean insert = DB.insertData(user, email, pass);

                        if(insert == true){
                            Toast.makeText(SignUp.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),Login.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(SignUp.this, "Registration failed!", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else{
                        Toast.makeText(SignUp.this, "User already exists! please sign in!", Toast.LENGTH_SHORT).show();
                    }

                } }
        });

    }
}
