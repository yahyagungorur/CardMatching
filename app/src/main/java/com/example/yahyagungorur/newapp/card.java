package com.example.yahyagungorur.newapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.widget.Button;
import android.support.v7.widget.AppCompatButton;

/**
 * Created by YAHYA on 26.07.2017.
 */

public class card extends AppCompatButton {

    boolean open = false;
    int arkaplanID;
    int onplanID;
    boolean cevrilebilir = true;
    Drawable arka,on;
    @SuppressLint("RestrictedApi")
    public card(Context context, int id,int kartsayisi) {
        super(context);
        int ss = kartsayisi/2;
        setId(id);
        arkaplanID=R.drawable.back;
        if(id % ss == 1)
            onplanID=R.drawable.c1;
        if(id % ss == 2)
            onplanID=R.drawable.c2;
        if(id % ss == 3)
            onplanID=R.drawable.c3;
        if(id % ss == 4)
            onplanID=R.drawable.c4;
        if(id % ss == 5)
            onplanID=R.drawable.c5;
        if(id % ss == 6)
            onplanID=R.drawable.c6;
        if(id % ss == 7)
            onplanID=R.drawable.c7;
        if(id % ss == 8)
            onplanID=R.drawable.c8;
        if(id % ss == 9)
            onplanID=R.drawable.c9;
        if(id % ss == 0)
            onplanID=R.drawable.c10;

        arka= AppCompatDrawableManager.get().getDrawable(context,arkaplanID);
        on= AppCompatDrawableManager.get().getDrawable(context,onplanID);
        setBackground(arka);

    }
    public void cevir() {
        if (cevrilebilir) {
            if (!open) {
                //arkası çevriliyse
                setBackground(on);
                open = true;
            } else {
                setBackground(arka);
                open = false;
            }
        }
    }
}
