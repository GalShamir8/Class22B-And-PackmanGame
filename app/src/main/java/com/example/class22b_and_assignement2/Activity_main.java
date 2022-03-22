package com.example.class22b_and_assignement2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import controllers.ControllerPacmanable;
import controllers.GameManager;

public class Activity_main extends AppCompatActivity {
    public static final int COLUMNS = 3;
    public static final int ROWS = 5;

    private ImageView[][] gameGrid = new ImageView[ROWS][COLUMNS];

    private ControllerPacmanable gameManager = GameManager.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        setViews();
        initGrid();
    }

    private void setViews() {
        setGridView();
    }

    private void setGridView() {
        String gridPrefix = "main_LBL_grid";
        for (int rowIndex = 0; rowIndex < ROWS; rowIndex++) {
            for (int colIndex = 0; colIndex < COLUMNS; colIndex++) {
                String imgResourceName = gridPrefix + rowIndex + colIndex;
                // TODO: 22/03/2022 Add resolve img by name 
//                gameGrid[rowIndex][colIndex] = (ImageView) findViewById(R.drawable)
            }
        }
    }


    private void initGrid() {
        for (int rowIndex = 0; rowIndex < ROWS; rowIndex++) {
            for (int colIndex = 0; colIndex < COLUMNS; colIndex++) {
                if(rowIndex == PLAYER_START_INDEX[0] && colIndex == PLAYER_START_INDEX[1]){
                    // TODO: 21/03/2022 Add player img
                    gameGrid[rowIndex][colIndex].setImageResource(R.drawable.game_background);
                }else if(rowIndex == RIVAL_START_INDEX[0] && colIndex == RIVAL_START_INDEX[1]){
                    // TODO: 21/03/2022 Add rival img
                    gameGrid[rowIndex][colIndex].setImageResource(R.drawable.game_background);
                }else {
                    gameGrid[rowIndex][colIndex].setImageResource(R.drawable.game_background);
                }
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}