package com.example.sqassist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
//        context.deleteDatabase(DBNAME);
    }

    //Create database table
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, email TEXT, password TEXT)");

    }

    //Check if the users database already exist
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    //Insert sign up user's data to database "login.db"
    public Boolean insertData(String username, String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    //Check if user already exist
    public Boolean checkEmail(String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ?", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    //Check if the user input email and password correct or wrong
    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ? and password = ?", new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    //Change user's password
    public Boolean changePassword(String email, String password)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        Cursor cursor = DB.rawQuery("Select * from users where email = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            long result = DB.update("users", contentValues, "email=?", new String[]{email});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    //Delete User
    public Boolean deleteUser (String email)
    {

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from users where email = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            long result = DB.delete("users", "email=?", new String[]{email});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

}