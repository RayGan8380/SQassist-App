package com.example.sqassist;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeFragment extends Fragment {
    FragmentManager fragmentManager;
    Dialog dialog;
    SharedPreference sharedPreference;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, container, false);    //to use findViewById in fragment

        //this will show the menu list we created
        MenuBuilder menuBuilder = new MenuBuilder(getActivity());
        MenuInflater menuInflater = new MenuInflater(getActivity());
        menuInflater.inflate(R.menu.setting_popup, menuBuilder);

        //Change username in home page
        TextView textUsername = v.findViewById(R.id.textOfUsername);
        sharedPreference = new SharedPreference(getActivity());
        String logUsername = sharedPreference.getUsername();
        textUsername.setText("Welcome, "+ logUsername );

        //Click profile button to show setting popout
        Button profileBtn;
        profileBtn = v.findViewById(R.id.profileBtn);
        profileBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                MenuPopupHelper optionMenu = new MenuPopupHelper(getActivity(), menuBuilder, view);
                optionMenu.setForceShowIcon(true);

                //Redirect to setting page or just log out
                menuBuilder.setCallback(new MenuBuilder.Callback() {
                    @Override
                    public boolean onMenuItemSelected(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.setting_icon:
                                Intent intent_one = new Intent(getActivity(), Setting.class);
                                startActivity(intent_one);

                            case R.id.logout_icon:
                                openLogoutDialog();

                            default:
                                return false;
                        }
                    }

                    @Override
                    public void onMenuModeChange(@NonNull MenuBuilder menu) {

                    }
                });

                optionMenu.show();
            }
        });

        //Choose which question's fragment to appear at home page
        int currentQuesFragment = sharedPreference.getQuesFragment();

        if (currentQuesFragment == 1) {
            getParentFragmentManager().beginTransaction().replace(R.id.question_container, new QuesTwo()).commit();
        }else if (currentQuesFragment == 2) {
            getParentFragmentManager().beginTransaction().replace(R.id.question_container, new QuesThree()).commit();
        }else if (currentQuesFragment == 3) {
            getParentFragmentManager().beginTransaction().replace(R.id.question_container, new QuesFour()).commit();
        }else if (currentQuesFragment == 4) {
            getParentFragmentManager().beginTransaction().replace(R.id.question_container, new QuesFive()).commit();
        }else if (currentQuesFragment == 5) {
            getParentFragmentManager().beginTransaction().replace(R.id.question_container, new Statictics()).commit();
        }else {
            getParentFragmentManager().beginTransaction().replace(R.id.question_container, new QuesOne()).commit();
        }

        return v;
    }

    //Click logout button to log out of app
    private void openLogoutDialog() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.logout_popout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnCancel = dialog.findViewById(R.id.cancelLogoutButton); //cancel button
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Button btnLogout = dialog.findViewById(R.id.logoutButton); //reset button
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_one = new Intent(getActivity(), MainActivity.class);
                startActivity(intent_one);

            }
        });

        dialog.show();
    }


}
