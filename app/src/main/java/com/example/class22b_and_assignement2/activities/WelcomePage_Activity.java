package com.example.class22b_and_assignement2.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.class22b_and_assignement2.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import common.eGameState;
import common.eGameType;
import controllers.GameManager;
import models.User;

public class WelcomePage_Activity extends AppCompatActivity {
    private static final String MY_PREFS_NAME = "GameDateFile";
    private SharedPreferences sharedPrefs;

    private HashMap<String, User> users;

    private EditText main_EDT_name;
    private MaterialButton main_BTN_send;
    private MaterialTextView main_LBL_status;

    private MaterialButton main_BTN_controlsGame;
    private MaterialButton main_BTN_sensorsGame;

    private eGameState gameState = eGameState.LOGGED_OFF;
    private Bundle data = null;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        initPage();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initPage() {
        if (data == null){
            data = new Bundle();
        }
        setData();
        setViews();
    }

    private void setData() {
        sharedPrefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        if (users == null) {
            users = new HashMap<>();
            if (sharedPrefs != null){
                Set<String> usersData = sharedPrefs.getStringSet("usersData", null);
                if (usersData != null) {
                    for (String userJson : usersData) {
                        User user = User.fromJsonToUser(userJson);
                        users.put(user.getName(), user);
                    }
                    updateUserData();
                }
            }
        }
    }

    private void updateUserData() {
        Set<String> usersData = new HashSet<>();
        for (User u: users.values()){
            usersData.add(u.userToJson());
        }
        data.putStringArrayList("usersData", new ArrayList<>(usersData));
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setViews() {
        setForm();
        setGameOpt();
        setState();
    }

    private void setState() {
        switch (gameState){
            case LOGGED_OFF:
                main_BTN_controlsGame.setEnabled(false);
                main_BTN_sensorsGame.setEnabled(false);
                main_BTN_send.setEnabled(true);
                main_EDT_name.setEnabled(true);
                setStatus(View.INVISIBLE, "", 0);
                break;
            case LOGGED_IN:
                main_BTN_controlsGame.setEnabled(true);
                main_BTN_sensorsGame.setEnabled(true);
                main_BTN_send.setEnabled(false);
                main_EDT_name.setEnabled(false);
                break;
        }
    }

    private void setForm() {
        main_BTN_send = findViewById(R.id.main_BTN_send);
        main_EDT_name = findViewById(R.id.main_EDT_name);
        main_LBL_status = findViewById(R.id.main_LBL_status);

        main_LBL_status.setVisibility(View.INVISIBLE);

        main_BTN_send.setOnClickListener(e -> setUserData());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setGameOpt() {
        main_BTN_controlsGame = findViewById(R.id.main_BTN_controlsGame);
        main_BTN_sensorsGame = findViewById(R.id.main_BTN_sensorsGame);
        MaterialButton main_BTN_topTen = findViewById(R.id.main_BTN_topTen);

        main_BTN_controlsGame.setOnClickListener(e -> openActivity(eGameType.CONTROLS));
        main_BTN_sensorsGame.setOnClickListener(e -> openActivity(eGameType.SENSORS));
        main_BTN_topTen.setOnClickListener(e -> openActivity(eGameType.TOP_TEN));
    }

    private void setUserData() {
        String name = main_EDT_name.getText().toString();
        if (name.isEmpty()){
            setStatus(View.VISIBLE, "Name is required", Color.RED);
        }else{
            User user = users.get(name);
            if (user == null){
                user = new User();
                user.setName(name);
                users.put(name, user);
            }
            data.putString("user", user.userToJson());
            setStatus(View.VISIBLE, "logged in successfully", Color.GREEN);
            gameState = eGameState.LOGGED_IN;
            setState();
        }
    }


    private void setStatus(int visibility, String message, int color) {
        main_LBL_status.setVisibility(visibility);
        main_LBL_status.setText(message);
        main_LBL_status.setTextColor(color);
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

    @Override
    protected void onResume() {
        GameManager gameManager = (GameManager) GameManager.getInstance();
        if (gameManager.getUser() != null) {
            data.putString("user", gameManager.finishGame().userToJson());
            updateData();
        }
        super.onResume();
    }

    private void updateData() {
        String userJson = data.getString("user", "");
        if (!userJson.isEmpty()) {
            User user = User.fromJsonToUser(userJson);
            // update current user details
            users.put(user.getName(), user);
            updateUserData();
        }
    }

    @Override
    protected void onPause() {
        saveGame();
        super.onPause();
    }

    private void saveGame() {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Set<String> usersData = new HashSet<>();
        for (User user: users.values()){
            usersData.add(user.userToJson());
        }
        editor.putStringSet("usersData", usersData);
        editor.apply();
    }
}