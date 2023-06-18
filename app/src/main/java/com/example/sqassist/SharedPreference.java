package com.example.sqassist;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {

    Context context;
    SharedPreferences sharedPreference;
    SharedPreferences.Editor editor;

    public SharedPreference (Context context) {
        this.context = context;

        sharedPreference = context.getSharedPreferences("sqassist", 0);
        editor = sharedPreference.edit();
    }

//    public boolean isLogin() {
//        if (sharedPreference.getBoolean("login", false))
//        {
//            return true;
//        }else{
//            return false;
//        }
//    }
//
//    public void setLogin() {
//        editor.putBoolean("login", true);
//        editor.commit();
//    }

    //Setter and getter for Email
    public void setEmail(String email) {
        editor.putString("email", email);
        editor.commit();
    }

    public String getEmail() {
        return sharedPreference.getString("email", "");
    }

    //Setter and getter for Username
    public void checkUsername(String username) {
        editor.putString("username", username);
        editor.commit();
    }

    public String getUsername() {
        return sharedPreference.getString("username", "");
    }

    //Setter and getter for Question Fragment
    public void setQuesFragment(int quesFragment) {
        editor.putInt("quesFragment", quesFragment);
        editor.commit();
    }

    public int getQuesFragment() {
        return sharedPreference.getInt("quesFragment", 0);
    }

    //Setter and getter for Temperature
    public void setTemperature(String temperature) {
        editor.putString("temperature", temperature);
        editor.commit();
    }

    public String getTemperature() {
        return sharedPreference.getString("temperature", "0");
    }

    //Setter and getter for Blood Oxygen
    public void setBloodOxi(String bloodOxi) {
        editor.putString("bloodOxi", bloodOxi);
        editor.commit();
    }

    public String getBloodOxi() {
        return sharedPreference.getString("bloodOxi", "0");
    }

    //Setter and getter for Cough Level
    public void setCoughLvl(String coughLvl) {
        if (coughLvl == "1") {
            editor.putString("coughLvl", "Light");
        }else if (coughLvl == "2") {
            editor.putString("coughLvl", "Light");
        }else if (coughLvl == "3") {
            editor.putString("coughLvl", "Mild");
        }else {
            editor.putString("coughLvl", "Serious");
        }
        editor.commit();
    }

    public String getCoughLvl() {
        return sharedPreference.getString("coughLvl", "0");
    }

    //Setter and getter for Tiredness level
    public void setTiredness(String tiredness) {
        if (tiredness == "1") {
            editor.putString("tiredness", "No");
        }else if (tiredness == "2") {
            editor.putString("tiredness", "Light");
        }else if (tiredness == "3") {
            editor.putString("tiredness", "Mild");
        }else {
            editor.putString("tiredness", "Serious");
        }
        editor.commit();
    }

    public String getTiredness() {
        return sharedPreference.getString("tiredness", "0");
    }

    //Setter and getter for Flu Level
    public void setFlu(String fluLvl) {
        if (fluLvl == "1") {
            editor.putString("fluLvl", "No");
        }else if (fluLvl == "2") {
            editor.putString("fluLvl", "Light");
        }else if (fluLvl == "3") {
            editor.putString("fluLvl", "Mild");
        }else {
            editor.putString("fluLvl", "Serious");
        }
        editor.commit();
    }

    public String getFlu() {
        return sharedPreference.getString("fluLvl", "0");
    }

    //Setter and getter for Loss of Taste Level
    public void setTaste(String tasteLvl) {
        if (tasteLvl == "1") {
            editor.putString("tasteLvl", "No");
        }else if (tasteLvl == "2") {
            editor.putString("tasteLvl", "Light");
        }else if (tasteLvl == "3") {
            editor.putString("tasteLvl", "Mild");
        }else {
            editor.putString("tasteLvl", "Serious");
        }
        editor.commit();
    }

    public String getTaste() {
        return sharedPreference.getString("tasteLvl", "0");
    }

    //Clear all preference's values
    public void clearAll() {
        editor.putString("email", null);
        editor.putString("username", null);
        editor.putInt("quesFragment", 0);
        editor.putString("temperature", null);
        editor.putString("bloodOxi", null);
        editor.putString("coughLvl", null);
        editor.putString("tiredness", null);
        editor.putString("fluLvl", null);
        editor.putString("tasteLvl", null);
        editor.commit();
    }

}
