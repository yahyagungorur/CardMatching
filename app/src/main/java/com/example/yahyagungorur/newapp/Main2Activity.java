package com.example.yahyagungorur.newapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class Main2Activity extends AppCompatActivity {

    private  InterstitialAd mInterstitialAd;
    private AdView mAdView;
    int lastcard = 0;
    int skor = 0;
    int hamle = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.reklam_id_gecis));
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("47D9AB919421513C986C124DD3FB8943").build());

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("47D9AB919421513C986C124DD3FB8943").build();
        mAdView.loadAd(adRequest);

        Intent intent2=getIntent();
        final String name2 = intent2.getStringExtra("name");
        int sutun = intent2.getIntExtra("sutun",0);
        int satir = intent2.getIntExtra("satir",0);

        TextView textname= (TextView)findViewById(R.id.nametext);
        textname.setText(name2);

        GridLayout gr = (GridLayout)findViewById(R.id.gr);
        gr.setColumnCount(satir);
        gr.setRowCount(sutun);

        final int kart_sayisi = satir*sutun;

        card cards[]= new card[kart_sayisi];

        ((TextView)findViewById(R.id.hamle)).setText(getResources().getString(R.string.move) +hamle);
        for(int i =0;i<kart_sayisi;i++) {
            cards[i]= new card(this, i+1,kart_sayisi);
            cards[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   final card k = (card)view;
                    k.cevir();
                    if(lastcard >0){
                        hamle++;
                        final card k2= (card)findViewById(lastcard);
                        if(k2.onplanID == k.onplanID && k2.getId() !=k.getId() ){
                            //eşleştiler
                            k2.cevrilebilir=false;
                            k.cevrilebilir=false;
                            skor++;
                            lastcard=0;
                            if(skor == (kart_sayisi/2)){
                                //oyun bitti
                                mInterstitialAd.show();
                                mInterstitialAd.setAdListener(new AdListener() {
                                    @Override
                                    public void onAdClosed() {

                                        Intent intent3= new Intent(Main2Activity.this,Main3Activity.class);
                                        intent3.putExtra("Hamle",hamle);
                                        intent3.putExtra("isim",name2);
                                        startActivity(intent3);
                                        finish();
                                        MainActivity.Main.finish();
                                        super.onAdClosed();
                                    }
                                });


                            }
                        }
                        else{
                            //eşleşmediler geri çevir 2 kartı

                            Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    k2.cevir();
                                    k.cevir();
                                    lastcard=0;
                                }
                            },500);


                        }
                    }
                    else{
                        lastcard = k.getId();
                    }
                    ((TextView)findViewById(R.id.hamle)).setText(getResources().getString(R.string.move) +hamle);
                }
            });
        }
        //Karıştır
        for(int i=0;i<kart_sayisi;i++)
        {
            int random = (int)(Math.random()*kart_sayisi);
            card c = cards[random];
            cards[random] = cards[i];
            cards[i]= c ;
        }


        for (int i =0;i<kart_sayisi;i++)
            gr.addView(cards[i]);

    }

}
