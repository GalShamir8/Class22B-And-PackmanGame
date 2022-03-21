package controllers;

import android.widget.ImageView;

import com.example.class22b_and_assignement2.R;

public class GameManager {
    public static final int COLUMNS = 3;
    public static final int ROWS = 5;

    private ImageView[][] gameGrid = new ImageView[COLUMNS][ROWS];

    /**
     * default constructor
     */
    public GameManager() {
        initGrid();
    }

    private void initGrid() {
        for (int colIndex = 0; colIndex < COLUMNS; colIndex++) {
            for (int rowIndex = 0; rowIndex < ROWS; rowIndex++) {
                gameGrid[rowIndex][colIndex].setImageResource(R.drawable.game_background);
            }
        }
    }
}
