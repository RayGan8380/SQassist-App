package com.example.sqassist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Setting extends AppCompatActivity {
    String[] languagesList;
    String languageSelected;
    Dialog dialog;
    Switch allowNottification;
    DBHelper DB;
    SharedPreference sharedPreference;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_page);

        dialog = new Dialog(this);
        DB = new DBHelper(getApplicationContext());

        //Click to pop out language menu selector
        TextView languageInput = (TextView) findViewById(R.id.languageInput);
        languageInput.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Create list of languages
                languagesList = new String[] {"English", "Chinese", "Malay"};
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Setting.this);
                mBuilder.setTitle("Language");

                //Click to choose language
                mBuilder.setSingleChoiceItems(languagesList, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        languageInput.setText(languagesList[i]);
                        languageSelected = languagesList[i];
                        dialogInterface.dismiss();
                        Toast.makeText(Setting.this, languageSelected + " selected", Toast.LENGTH_SHORT).show();
                    }
                });
                //show language dialog
                AlertDialog languageDialog = mBuilder.create();
                languageDialog.show();


            }
        });

        //Click to show Reset Password popout
        TextView  resetText = (TextView) findViewById(R.id.resetText);
        resetText.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                   openResetDialog();

                }
        });

        //Click to show Delete Account popout
        TextView  deleteText = (TextView) findViewById(R.id.deleteText);
        deleteText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openDeleteDialog();

            }
        });

        //Click to check or unchecked the notification
        allowNottification = findViewById(R.id.allowNoti);

        allowNottification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (allowNottification.isChecked()) {
                    Toast.makeText(Setting.this, "Notification is turned on.", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(Setting.this, "Notification is turned off.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Click to go back to home page
        TextView  backToHome = (TextView) findViewById(R.id.BackToHome);
        backToHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NavFragment.class);
                startActivity(intent);

            }
        });

    }

    private void openResetDialog() {
        dialog.setContentView(R.layout.reset_pass_popout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //cancel button
        Button btnCancel = dialog.findViewById(R.id.cancelResetButton);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        //reset button
        Button btnReset = dialog.findViewById(R.id.resetButton);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_one = new Intent(Setting.this, ResetPass.class);
                startActivity(intent_one);

            }
        });

        dialog.show();

    }

    private void openDeleteDialog() {
        dialog.setContentView(R.layout.delete_acc_popout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        sharedPreference = new SharedPreference(Setting.this);

        //cancel button
        Button btnCancel = dialog.findViewById(R.id.cancelDeleteButton);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        //delete button
        Button deleteBtn = dialog.findViewById(R.id.deleteButton);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //Retrieve the email of user
                String emailSaved = sharedPreference.getEmail();

                //Check if the fields are empty or already registered in database else then register
                Boolean deleteUser = DB.deleteUser(emailSaved);
//
                if (deleteUser == true) {
                    Toast.makeText(Setting.this, "Deleted successfully!", Toast.LENGTH_SHORT).show();

                    sharedPreference.clearAll();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(Setting.this, "Delete failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }


}