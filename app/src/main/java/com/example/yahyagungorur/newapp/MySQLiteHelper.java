package com.example.yahyagungorur.newapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sadievrenseker on 3/29/17.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    String SQL_Create2 = "create table kullanicilar ('skor' integer ,'name' text not null);";

    public MySQLiteHelper(Context context) {
        super(context, "kullanicilar", null , 1);
        // database name is myusers

    }
    public void onCreate (SQLiteDatabase database){
        database.execSQL(SQL_Create2);
    }
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists users");
        onCreate(db);
    }

}
