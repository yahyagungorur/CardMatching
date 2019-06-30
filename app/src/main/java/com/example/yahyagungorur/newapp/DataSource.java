package com.example.yahyagungorur.newapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sadievrenseker on 3/29/17.
 */

public class DataSource {
    SQLiteDatabase database;
    MySQLiteHelper mysqlhelper ;
    String[] allColumns = {"skor","name"};
    public DataSource (Context c){
        mysqlhelper = new MySQLiteHelper(c);
    }
    public void open (){
        database = mysqlhelper.getWritableDatabase();
    }
    public void close() {mysqlhelper.close();}
    public User createUser(String name,int skor){
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("skor",skor);
        database.insert("kullanicilar",null,values);
        Cursor cursor = database.query("kullanicilar",allColumns,
                "skor"+"="+skor,null,null,null,null);
        cursor.moveToFirst();
        User newUser = cursorToUser (cursor);
        cursor.close();
        return newUser;
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<User>();
        Cursor c = database.query("kullanicilar",allColumns,null,null,null,null,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            User u = cursorToUser(c);
            users.add(u);
            c.moveToNext();
        }
        return users;
    }
    private User cursorToUser (Cursor cursor){
        User u = new User();
        u.setSkor((int)cursor.getLong(0));
        u.setName(cursor.getString(1));
        return u;
    }
}
