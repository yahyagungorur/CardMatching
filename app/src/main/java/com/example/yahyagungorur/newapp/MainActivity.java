package com.example.yahyagungorur.newapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    private AdView mAdView3;
    public static Activity Main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Main = this;
        mAdView3 = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("47D9AB919421513C986C124DD3FB8943").build();
        mAdView3.loadAd(adRequest);

        ((Button)findViewById(R.id.scr)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scr = new Intent(MainActivity.this,Scores.class);
                scr.putExtra("k",1);
                startActivity(scr);
                finish();
            }
        });


    }

    public void clicked(View view) {
        EditText name = (EditText) findViewById(R.id.name);
        int satir=0;
        int sutun = 0;
        if (!TextUtils.isEmpty(name.getText().toString()))
        {
            if (view.getId() == R.id.ikiiki) {
                satir = 2;
                sutun = 2;
            }
            if (view.getId() == R.id.ikiuc) {
                satir = 2;
                sutun = 3;
            }
            if (view.getId() == R.id.ikidort) {
                satir = 2;
                sutun = 4;
            }
            if (view.getId() == R.id.dortuc) {
                satir = 4;
                sutun = 3;
            }
            if (view.getId() == R.id.dortdort) {
                satir = 4;
                sutun = 4;
            }
            if (view.getId() == R.id.dortbes) {
                satir = 4;
                sutun = 5;
            }
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        intent.putExtra("name", name.getText().toString());
        intent.putExtra("satir",satir);
        intent.putExtra("sutun",sutun);
        startActivity(intent);
        }

    else
        name.setError(getResources().getString(R.string.warning));

    }
}
