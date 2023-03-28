package com.codehemu.loveforgirlfriend;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Locale;


public class FullscreenActivity extends AppCompatActivity {
    static int[] unique(int n){
        int []a = new int[n];
        for (int i = 0;i<n;i++){
            a[i] = i;
        }
        int result[] = new int[n];
        int x = n;
        SecureRandom rd = new SecureRandom();
        for (int i = 0;i<n;i++){
            int num = rd.nextInt(x);
            result[i] = a[num];
            a[num] = a[x-1];
            x--;
        }
        return result;
    }

    private RecyclerView.Adapter adapter;
    private ArrayList<ListItem> listItems;
    private RecyclerView recyclerView;
    private AdView mAdView;

    private void showChangeLanguageDialog(){
        final String[] listItem = {"English", "বাংলা", "हिंदी","日本","Русский", "한국인","中国人"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(FullscreenActivity.this);
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(listItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0){
                    SetLocale("en");
                    recreate();
                }
                else if (i == 1){
                    SetLocale("bn");
                    recreate();
                }
                else if (i == 2){
                    SetLocale("hi");
                    recreate();
                }
                else if (i == 3){
                    SetLocale("ja");
                    recreate();
                }
                else if (i == 4){
                    SetLocale("ru");
                    recreate();
                }
                else if (i == 5){
                    SetLocale("ko");
                    recreate();
                }
                else if (i == 6){
                    SetLocale("zh");
                    recreate();
                }

                dialog.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void SetLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }
    public void loadLocale(){
        SharedPreferences pref = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = pref.getString("My_Lang", "");
        SetLocale(language);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadLocale();
        ImageView imageView = findViewById(R.id.translate);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguageDialog();
            }
        });


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        this.recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.listItems = new ArrayList<ListItem>();
        int []item = unique(50);
        for (int i = 0;i<50;i++){
            int letter[] = {
                    R.string.letter5,
                    R.string.letter2,
                    R.string.letter3,
                    R.string.letter4,
                    R.string.letter1,
                    R.string.letter6,
                    R.string.letter7,
                    R.string.letter8,
                    R.string.letter9,
                    R.string.letter10,
                    R.string.letter11,
                    R.string.letter12,
                    R.string.letter13,
                    R.string.letter14,
                    R.string.letter15,
                    R.string.letter16,
                    R.string.letter17,
                    R.string.letter18,
                    R.string.letter19,
                    R.string.letter20,
                    R.string.letter21,
                    R.string.letter22,
                    R.string.letter23,
                    R.string.letter24,
                    R.string.letter25,
                    R.string.letter26,
                    R.string.letter27,
                    R.string.letter28,
                    R.string.letter29,
                    R.string.letter30,
                    R.string.letter31,
                    R.string.letter32,
                    R.string.letter33,
                    R.string.letter34,
                    R.string.letter35,
                    R.string.letter36,
                    R.string.letter37,
                    R.string.letter38,
                    R.string.letter39,
                    R.string.letter40,
                    R.string.letter41,
                    R.string.letter42,
                    R.string.letter43,
                    R.string.letter44,
                    R.string.letter45,
                    R.string.letter46,
                    R.string.letter47,
                    R.string.letter48,
                    R.string.letter49,
                    R.string.letter50};
            this.listItems.add(new ListItem(getString(letter[item[i]])));
        }
        this.adapter = new Adapts(this.listItems, this);
        this.recyclerView.setAdapter(this.adapter);


    }
}