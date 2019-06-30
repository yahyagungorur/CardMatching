package com.example.yahyagungorur.newapp;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class Main3Activity extends AppCompatActivity {

    private AdView mAdView4;
    SharedPreferences preferences;
    int hamle_sayisi=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        hamle_sayisi=preferences.getInt("hamlesayisi",hamle_sayisi);

        final Intent intent4 = getIntent();
        hamle_sayisi = intent4.getIntExtra("Hamle",0);
        final String isim = intent4.getStringExtra("isim");

        mAdView4 = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("47D9AB919421513C986C124DD3FB8943").build();
        mAdView4.loadAd(adRequest);




       ((TextView)findViewById(R.id.hamle2)).setText(getResources().getString(R.string.cong)+
               "\n"+getResources().getString(R.string.move)+"\n"+hamle_sayisi);


        ((Button)findViewById(R.id.newgame)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5=new Intent(Main3Activity.this,MainActivity.class);
                startActivity(intent5);
                finish();
            }
        });

        ((Button)findViewById(R.id.skors)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent score = new Intent(Main3Activity.this,Scores.class);
                score.putExtra("kullanici_isim",isim);
                score.putExtra("kullanici_skor",hamle_sayisi);
                score.putExtra("k",2);
                startActivity(score);
                finish();
            }
        });

        ((Button)findViewById(R.id.exit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("hamlesayisi",hamle_sayisi);
        editor.commit();
        super.onPause();
    }

    @Override
    protected void onStop() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("hamlesayisi",hamle_sayisi);
        editor.commit();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("hamlesayisi",hamle_sayisi);
        editor.commit();
        super.onDestroy();
    }
}
