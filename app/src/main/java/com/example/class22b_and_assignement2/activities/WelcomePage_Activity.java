package com.example.class22b_and_assignement2.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.class22b_and_assignement2.R;
import com.google.android.material.button.MaterialButton;

import common.eGameType;

public class WelcomePage_Activity extends AppCompatActivity {

    private MaterialButton main_BTN_controlsGame;
    private MaterialButton main_BTN_sensorsGame;
    private MaterialButton main_BTN_topTen;

    private Bundle data = null;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        setDate();
        setView();
    }

    private void setDate() {
        if (data == null){
            data = new Bundle();
        }else{
            updateData();
        }
    }

    private void updateData() {
        // TODO: 19/04/2022 update data upon assignment requirements 
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setView() {
        main_BTN_controlsGame = findViewById(R.id.main_BTN_controlsGame);
        main_BTN_sensorsGame = findViewById(R.id.main_BTN_sensorsGame);
        main_BTN_topTen = findViewById(R.id.main_BTN_topTen);

        main_BTN_controlsGame.setOnClickListener(e -> openActivity(eGameType.CONTROLS));
        main_BTN_sensorsGame.setOnClickListener(e -> openActivity(eGameType.SENSORS));
        main_BTN_topTen.setOnClickListener(e -> openActivity(eGameType.TOP_TEN));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void openActivity(eGameType gameType) {
        Intent newIntent;
        if (gameType != eGameType.TOP_TEN){
            data.putInt("gameType", gameType.ordinal());
            newIntent = new Intent(this, Game_Activity.class);

        }else{
            newIntent = new Intent(this, TopTen_Activity.class);
        }
        newIntent.putExtras(data);
        startActivity(newIntent);
    }
}