package com.om.datacollection.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.om.datacollection.model.UserData;

public class DataHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Collection";
    public static final String TABLE_USER = "User";
    public static final String col_ID = "id";
    public static final String col_Name = "name";
    public static final String col_salary = "salary";
    public static final String col_age = "age";
    private static final String Create_User_Table = "create table " + TABLE_USER + "(" + col_ID + " INTEGER PRIMARY KEY,"
            + col_Name + " TEXT ," + col_salary + " INTEGER ," + col_age + " INTEGER " + ");";
    SQLiteDatabase db;


    public DataHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_User_Table);
        this.db = db;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean adduser(UserData ud) {
        db = this.getWritableDatabase();
        String querya = "Select * from " + TABLE_USER + ";";
        Cursor cursor = db.rawQuery(querya, null);
        int count = cursor.getCount() + 1;
        try {
            String query = "insert into User (id,name,salary,age) values ('" + count + "','" + ud.getName() + "','" + ud.getSalary() + "','" + ud.getAge() + "')";
            Log.d(null, query);
            db.execSQL(query);
            return true;
        } catch (Exception e) {
            Log.d("error", e.toString());
            return false;
        }

    }

    public Cursor GetEmployess() {
        db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_USER, null);
        return res;

    }

    public Cursor searchEmployess(int id) {
        db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_USER + " where id =" + id, null);
        return res;
    }

    public boolean Update(UserData ud) {
        db = this.getWritableDatabase();
        String query = "Update User set name = '" + ud.getName() + "'," +
                " salary = '" + ud.getSalary() + "'," + " age = '" + ud.getAge() +
                "' where id = '"+ud.getId()+"';";
        try {
            Log.d("error", query);
            db.execSQL(query);
            return true;
        } catch (Exception e) {
            Log.d("error", e.toString());
            return false;
        }
    }
    public boolean Delete(int id) {
        db = this.getWritableDatabase();
        String query = "Delete from User where id ='" +id+"';";
        try {
            Log.d("error", query);
            db.execSQL(query);
            return true;
        } catch (Exception e) {
            Log.d("error", e.toString());
            return false;
        }
    }
}
