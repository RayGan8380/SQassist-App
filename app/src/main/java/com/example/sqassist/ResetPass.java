package com.example.sqassist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResetPass extends AppCompatActivity {

    EditText emailForReset, passForReset;
    Button resetPassBtn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_pass_page);

        //If successfully reset password, then redirect to login page
        Button resetBtn = findViewById(R.id.resetPassBtn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_one = new Intent(ResetPass.this, Login.class);
                startActivity(intent_one);
            }
        });

        emailForReset = findViewById(R.id.emailForReset);
        passForReset = findViewById(R.id.newPassword);
        resetPassBtn = findViewById(R.id.resetPassBtn);
        DB = new DBHelper(this);

        //login to app by matching inputs with data in database after clicking login button
        resetPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailForReset.getText().toString();
                String pass = passForReset.getText().toString();

                //Check if the fields are empty or having invalid credentials else just login
                if(email.equals("")||pass.equals(""))
                    Toast.makeText(ResetPass.this, "Please enter all the fields!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkedResetPass = DB.changePassword(email, pass);
                    if(checkedResetPass==true){
                        Toast.makeText(ResetPass.this, "Reset password successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(ResetPass.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


}