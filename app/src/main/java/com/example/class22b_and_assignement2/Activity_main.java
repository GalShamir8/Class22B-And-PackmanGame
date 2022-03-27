package com.example.class22b_and_assignement2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textview.MaterialTextView;

import java.util.Timer;
import java.util.TimerTask;

import controllers.ControllerPacmanable;
import controllers.GameManager;
import models.Pacmanable;
import controllers.eDirection;
import controllers.eTimerStatus;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Activity_main extends AppCompatActivity {
    /* lives display */
    private  ImageView[] hearts;
    /* background display */
    private ImageView[][] gameGrid;
    private final ControllerPacmanable gameManager = GameManager.getInstance();
    private MaterialTextView main_LBL_countDown;
    private MaterialTextView main_LBL_clock;
    private int clockCounter = 0;

    private Timer timer;
    eTimerStatus timerStatus = eTimerStatus.OFF;
    /* implements inside the time task for timer each "tick" */
    private final Runnable tickTask = this::tick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        setViews();
        renderGrid();
    }

    private void setViews() {
        setTopView();
        setGridView();
        setControls();
    }

    private void setTopView() {
        setHearts();
        main_LBL_clock = findViewById(R.id.main_LBL_clock);
        main_LBL_clock.setText(R.string.default_timer);
        main_LBL_countDown = findViewById(R.id.main_LBL_countDown);
        main_LBL_countDown.setVisibility(View.INVISIBLE);
    }

    private void setHearts() {
        String prefix = "main_IMG_lives";
        Resources resources = this.getResources();
        hearts = new ImageView[gameManager.getLivesStart()];
        for(int i = 0; i < hearts.length; i++){
            /* hearts img id string pattern:
            prefix + live index ( starts in 1 ) */
            String imgResourceName = prefix + (i + 1);
            int imageId = resources.getIdentifier(imgResourceName, "id",
                    this.getPackageName());
            hearts[i] = findViewById(imageId);
            hearts[i].setImageResource(R.drawable.heart);
            hearts[i].setVisibility(View.VISIBLE);
        }
    }

    private void setControls() {
        ImageView main_ING_up_arrow = findViewById(R.id.main_ING_up_arrow);
        ImageView main_ING_down_arrow = findViewById(R.id.main_ING_down_arrow);
        ImageView main_ING_right_arrow = findViewById(R.id.main_ING_right_arrow);
        ImageView main_ING_left_arrow = findViewById(R.id.main_ING_left_arrow);

        main_ING_left_arrow.setImageResource(R.drawable.left_arrow);
        main_ING_down_arrow.setImageResource(R.drawable.down_arrow);
        main_ING_right_arrow.setImageResource(R.drawable.right_arrow);
        main_ING_up_arrow.setImageResource(R.drawable.up_arrow);

        main_ING_up_arrow.setOnClickListener(e -> changeDirection(gameManager.getPlayer(), eDirection.UP));
        main_ING_down_arrow.setOnClickListener(e -> changeDirection(gameManager.getPlayer(), eDirection.DOWN));
        main_ING_right_arrow.setOnClickListener(e -> changeDirection(gameManager.getPlayer(), eDirection.RIGHT));
        main_ING_left_arrow.setOnClickListener(e -> changeDirection(gameManager.getPlayer(), eDirection.LEFT));
    }

    private void changeDirection(Pacmanable playEntity, eDirection direction) {
        playEntity.setDirection(direction);
        renderGrid();
    }

    private void setGridView() {
        int rows = gameManager.getRows();
        int cols = gameManager.getCols();
        gameGrid = new ImageView[rows][cols];
        String gridPrefix = "main_IMG_grid";
        Resources resources = getResources();
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            for (int colIndex = 0; colIndex < cols; colIndex++) {
                /* Img view (of grid) id fit the string pattern:
                 prefix + img matrix indexes positions suffix ( main_LBL_gridrowIndexcolIndex ) */
                String imgResourceName = gridPrefix + rowIndex + colIndex;
                int imageId = resources.getIdentifier(imgResourceName, "id",
                        this.getPackageName());
                gameGrid[rowIndex][colIndex] = findViewById(imageId);
            }
        }
    }

    private void renderGrid() {
        int rows = gameManager.getRows();
        int cols = gameManager.getCols();
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            for (int colIndex = 0; colIndex < cols; colIndex++) {
                if(checkPlayerPos(gameManager.getPlayer(), rowIndex, colIndex)){
                    setPlayerEntityView(gameManager.getPlayer());
                }else if(checkPlayerPos(gameManager.getRival(),  rowIndex, colIndex)){
                    setPlayerEntityView(gameManager.getRival());
                }else {
                    gameGrid[rowIndex][colIndex].setImageResource(R.drawable.game_background);
                }
            }
        }
    }

    private boolean checkPlayerPos(Pacmanable player, int rowIndex, int colIndex) {
        int[] playerPos = player.getPosition();
        return playerPos[0] == rowIndex && playerPos[1] == colIndex;
    }

    private int getImageResourceByDirection(String prefix, eDirection direction) {
        String name = prefix + direction.name().toLowerCase();
        Resources resources = getResources();
        return resources.getIdentifier(name, "drawable", this.getPackageName());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void tick() {
        if(timerStatus == eTimerStatus.RUNNING) {
            ++clockCounter;
            String clockAsStr = "" + clockCounter;
            main_LBL_clock.setText(clockAsStr);
            renderUI();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void renderUI() {
        gameManager.executeMove();
        if(gameManager.isCollision()){
            handleCollision();
        }else {
            renderGrid();
        }
        setLivesView();
        setScoreView();
    }

    private void setPlayerEntityView(Pacmanable playerEntity) {
        int[] pos = playerEntity.getPosition();
        gameGrid[pos[0]][pos[1]].setImageResource(
                getImageResourceByDirection(
                        playerEntity.getName(),
                        playerEntity.getDirection()
                )
        );
    }

    private void setScoreView() {
    }

    private void setLivesView() {
        for(int i = gameManager.getLivesStart() ; i > gameManager.getLives(); i--){
            hearts[i - 1].setVisibility(View.INVISIBLE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void handleCollision() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        try{
            gameManager.handleCollision();
            renderGrid();
            main_LBL_countDown.setVisibility(View.VISIBLE);
            timerStatus = eTimerStatus.PAUSE;
            getCountDownTimer().start();
        }catch (Exception e){
            finishGame(e.getMessage());
        }
    }

    private CountDownTimer getCountDownTimer() {
        return new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String message = "Start in: " + millisUntilFinished / 1000 + " seconds";
                main_LBL_countDown.setText(message);
            }

            @Override
            public void onFinish() {
                main_LBL_countDown.setVisibility(View.INVISIBLE);
                timerStatus = eTimerStatus.RUNNING;
            }
        };
    }

    private void finishGame(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        gameManager.finishGame();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startTimer();
        timerStatus = eTimerStatus.RUNNING;
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
        timerStatus = eTimerStatus.STOP;
    }

    private void stopTimer() {
        switch (timerStatus){
            case RUNNING:
                timer.cancel();
                break;
            case OFF:
            case PAUSE:
            case STOP:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
        timerStatus = eTimerStatus.RUNNING;
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopTimer();
        timerStatus = eTimerStatus.PAUSE;
    }
}