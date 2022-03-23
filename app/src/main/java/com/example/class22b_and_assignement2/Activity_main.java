package com.example.class22b_and_assignement2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import controllers.ControllerPacmanable;
import controllers.GameManager;

public class Activity_main extends AppCompatActivity {

    private ImageView[][] gameGrid;

    private ControllerPacmanable gameManager = GameManager.getInstance();

    private Timer timer;
    private final Runnable tickTask = new Runnable() {
        @Override
        public void run() {
            gameManager.tick();
        }
    };


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
        int rows = gameManager.getRows();
        int cols = gameManager.getCols();
        gameGrid = new ImageView[rows][cols];
        String gridPrefix = "main_LBL_grid";
        Resources resources = getResources();
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            for (int colIndex = 0; colIndex < cols; colIndex++) {
                /* Img view (of grid) id fit the string pattern:
                 the above prefix + img matrix indexes positions suffix ( main_LBL_gridij ) */
                String imgResourceName = gridPrefix + rowIndex + colIndex;
                int imageId = resources.getIdentifier(imgResourceName, "id",
                        this.getPackageName());
                gameGrid[rowIndex][colIndex] = findViewById(imageId);
            }
        }
    }

    private void initGrid() {
        int rows = gameManager.getRows();
        int cols = gameManager.getCols();
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            for (int colIndex = 0; colIndex < cols; colIndex++) {
                if(gameManager.checkPlayerStartIndex(rowIndex, colIndex)){
                    // TODO: 21/03/2022 Add player img
                    gameGrid[rowIndex][colIndex].setImageResource(R.drawable.game_background);
                }else if(gameManager.checkRivalStartIndex(rowIndex, colIndex)){
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
        startTimer();
    }

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(tickTask);
            }
        }, 0, gameManager.getDelay());
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopTimer();
    }

    private void stopTimer() {
        timer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        startTimer();
    }
}