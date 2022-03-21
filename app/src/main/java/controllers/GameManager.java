package controllers;

import android.widget.ImageView;

import com.example.class22b_and_assignement2.R;

public class GameManager {
    public static final int COLUMNS = 3;
    public static final int ROWS = 5;

    public static final int[] PLAYER_START_INDEX = {ROWS, (int)COLUMNS/2};
    public static final int[] RIVAL_START_INDEX = {0, ROWS};

    private static final int LIVES = 3;

    private ImageView[][] gameGrid = new ImageView[ROWS][COLUMNS];

    private int score = 0;
    private int lives = LIVES;


    /**
     * default constructor
     */
    public GameManager() {
        initGrid();
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
}
