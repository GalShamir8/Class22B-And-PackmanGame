package controllers;

import android.widget.ImageView;

import com.example.class22b_and_assignement2.R;


public class GameManager implements ControllerPacmanable{
    private static final long DELAY = 1000;

    public static final int COLUMNS = 3;
    public static final int ROWS = 5;

    public static final int[] PLAYER_START_INDEX = {ROWS, (int)COLUMNS/2};
    public static final int[] RIVAL_START_INDEX = {0, ROWS};

    private static final int LIVES = 3;


    private int score = 0;
    private int lives = LIVES;

    private static GameManager gameManagerInstance = null;

    /**
     * default constructor
     */
    private GameManager() {
    }

    public static ControllerPacmanable getInstance() {
        if (gameManagerInstance == null){
            gameManagerInstance = new GameManager();
        }
        return gameManagerInstance;
    }


    @Override
    public void changeDirection(Pacmanable entity, eDirection edirection) {

    }

    @Override
    public boolean checkPlayerStartIndex(int row, int col) {
        return row == PLAYER_START_INDEX[0] && col == PLAYER_START_INDEX[1];
    }

    @Override
    public boolean checkRivalStartIndex(int row, int col) {
        return row == RIVAL_START_INDEX[0] && col == RIVAL_START_INDEX[1];
    }

    @Override
    public void tick() {

    }

    public int getRows(){ return ROWS; }

    public int getCols(){ return COLUMNS; }

    @Override
    public long getDelay() {
        return DELAY;
    }
}
