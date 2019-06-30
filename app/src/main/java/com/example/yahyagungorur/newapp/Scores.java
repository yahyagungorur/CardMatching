package com.example.yahyagungorur.newapp;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

public class Scores extends ListActivity {

    int k;
    private AdView mAdView2;
    DataSource ds ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);



        mAdView2 = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("47D9AB919421513C986C124DD3FB8943").build();
        mAdView2.loadAd(adRequest);
        Intent getScore = getIntent();
        String isim2 = getScore.getStringExtra("kullanici_isim");
        int hamle_sayisi2= getScore.getIntExtra("kullanici_skor",0);
        k = getScore.getIntExtra("k",0);

        ((Button)findViewById(R.id.back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(k == 1){
                    Intent back = new Intent(Scores.this,MainActivity.class);
                    startActivity(back);
                    finish();
                }
                if(k == 2){
                    Intent back = new Intent(Scores.this,Main3Activity.class);
                    startActivity(back);
                    finish();
                }

            }
        });


        ds = new DataSource(this);
        ds.open();
        List<User> values = ds.getAllUsers();
        ArrayAdapter<User> adapter= new ArrayAdapter<User>(this,
                android.R.layout.simple_list_item_1,values);
        setListAdapter(adapter);
        User u = null;
        u=ds.createUser(isim2,hamle_sayisi2);
        adapter.add(u);
        adapter.notifyDataSetChanged();

    }
}
