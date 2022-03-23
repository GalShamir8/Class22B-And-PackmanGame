package com.example.class22b_and_assignement2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import controllers.ControllerPacmanable;
import controllers.GameManager;
import controllers.eTimerStatus;

public class Activity_main extends AppCompatActivity {

    private ImageView[][] gameGrid;

    private ControllerPacmanable gameManager = GameManager.getInstance();

    private ImageView main_ING_up_arrow;
    private ImageView main_ING_down_arrow;
    private ImageView main_ING_right_arrow;
    private ImageView main_ING_left_arrow;

    private Timer timer;
    eTimerStatus timerStatus = eTimerStatus.OFF;
    private final Runnable tickTask = new Runnable() {
        @Override
        public void run() {
            tick();
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
        setControls();
    }

    private void setControls() {
        main_ING_up_arrow = findViewById(R.id.main_ING_up_arrow);
        main_ING_down_arrow = findViewById(R.id.main_ING_down_arrow);
        main_ING_right_arrow = findViewById(R.id.main_ING_right_arrow);
        main_ING_left_arrow = findViewById(R.id.main_ING_left_arrow);

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

    private void tick() {
    }

    @Override
    protected void onStart() {
        super.onStart();
        startTimer();
    }

    private void startTimer() {
        switch (timerStatus) {
            case OFF:
            case STOP:
            case PAUSE:
                timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(tickTask);
                    }
                }, 0, gameManager.getDelay());
                break;
            case RUNNING:
                break;
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        stopTimer();
    }

    private void stopTimer() {
        switch (timerStatus){
            case RUNNING:
                timer.cancel();
                break;
            case OFF:
                break;
            case PAUSE:
                break;
            case STOP:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopTimer();
    }
}