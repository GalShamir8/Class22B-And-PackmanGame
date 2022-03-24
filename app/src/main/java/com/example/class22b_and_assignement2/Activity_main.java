package com.example.class22b_and_assignement2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import controllers.ControllerPacmanable;
import controllers.GameManager;
import models.Pacmanable;
import controllers.eDirection;
import controllers.eTimerStatus;

public class Activity_main extends AppCompatActivity {
    private  ImageView[] hearts;
    private ImageView[][] gameGrid;
    private ControllerPacmanable gameManager = GameManager.getInstance();
    private MaterialTextView main_LBL_clock;
    private int clockCounter = 0;

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
        setTopView();
        setGridView();
        setControls();
    }

    private void setTopView() {
        setHearts();
        main_LBL_clock = findViewById(R.id.main_LBL_clock);
        main_LBL_clock.setText(R.string.default_timer);
    }

    private void setHearts() {
        String prefix = "main_IMG_lives";
        Resources resources = this.getResources();
        hearts = new ImageView[gameManager.getLivesStart()];
        for(int i = 0; i < hearts.length; i++){
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

        main_ING_up_arrow.setOnClickListener(e -> changeDirection(gameManager.getPlayer(), eDirection.UP));
        main_ING_down_arrow.setOnClickListener(e -> changeDirection(gameManager.getPlayer(), eDirection.DOWN));
        main_ING_right_arrow.setOnClickListener(e -> changeDirection(gameManager.getPlayer(), eDirection.RIGHT));
        main_ING_left_arrow.setOnClickListener(e -> changeDirection(gameManager.getPlayer(), eDirection.LEFT));
    }

    private void changeDirection(Pacmanable playEntity, eDirection direction) {
        playEntity.setDirection(direction);
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
                    String prefix = "batman_";
                    eDirection direction = gameManager.getPlayer().getDirection();
                    int resId = getImageResourceByDirection(prefix, direction);
                    gameGrid[rowIndex][colIndex].setImageResource(resId);
                }else if(gameManager.checkRivalStartIndex(rowIndex, colIndex)){
                    // TODO: 21/03/2022 Add rival img
                    String prefix = "joker_";
                    eDirection direction = gameManager.getRival().getDirection();
                    int resId = getImageResourceByDirection(prefix, direction);
                    gameGrid[rowIndex][colIndex].setImageResource(resId);
                }else {
                    gameGrid[rowIndex][colIndex].setImageResource(R.drawable.game_background);
                }
            }
        }
    }

    private int getImageResourceByDirection(String prefix, eDirection direction) {
        String name = prefix + direction.name().toLowerCase();
        Resources resources = getResources();
        return resources.getIdentifier(name, "drawable", this.getPackageName());
    }

    private void tick() {
        ++clockCounter;
        String clockAsStr = "" + clockCounter;
        main_LBL_clock.setText(clockAsStr);
        renderUI();
    }

    private void renderUI() {
        changeDirection(gameManager.getRival(), gameManager.getRandomDirection());
        if(gameManager.isCollision()){
            handleCollision();
        }
        setBackgroundInViews(gameManager.getAllPlayers());
        gameManager.executeMove();
        setLivesView();
        setScoreView();
        setPlayerEntityView(gameManager.getPlayer());
        setPlayerEntityView(gameManager.getRival());
    }

    private void setBackgroundInViews(ArrayList<Pacmanable> allPlayers) {
        for (Pacmanable player: allPlayers){
            int[] pos = player.getPosition();
            gameGrid[pos[0]][pos[1]].setImageResource(R.drawable.game_background);
        }
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


    private void handleCollision() {
        try{
            //gameManager.reduceLives();
            gameManager.updateScore(ControllerPacmanable.SCORE_NEGATIVE_FACTOR);
        }catch (Exception e){
            finishGame(e.getMessage());
        }
    }

    private void finishGame(String message) {
        // TODO: 24/03/2022 add toast message
        finish();
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