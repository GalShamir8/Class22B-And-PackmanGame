package com.example.class22b_and_assignement2.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.class22b_and_assignement2.R;
import com.example.class22b_and_assignement2.common.Keys;
import com.example.class22b_and_assignement2.utils.MySharedPrefs;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import com.example.class22b_and_assignement2.common.eGameState;
import com.example.class22b_and_assignement2.common.eGameType;
import com.example.class22b_and_assignement2.models.User;


public class WelcomePage_Activity extends AppCompatActivity {

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
        MySharedPrefs.init(this);
        if (data == null){
            data = new Bundle();
        }
        setViews();
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
            User user = MySharedPrefs.getInstance().getUserByName(name);
            if (user == null){
                user = new User();
                user.setName(name);
            }

            data.putString(Keys.USER_KEY, user.userToJson());
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
            data.putInt(Keys.GAME_TYPE_KEY, gameType.ordinal());
            newIntent = new Intent(this, Game_Activity.class);

        }else{
            newIntent = new Intent(this, TopTen_Activity.class);
        }
        newIntent.putExtras(data);
        startActivity(newIntent);
    }

}