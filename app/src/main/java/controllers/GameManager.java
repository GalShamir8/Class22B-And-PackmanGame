package controllers;

import models.Pacmanable;
import models.Player;
import models.Rival;


public class GameManager implements ControllerPacmanable{
    private static final int PLAYERS_AMOUNT = 1;
    private static final int RIVALS_AMOUNT = 1;
    private static final long DELAY = 1000;
    public static final int COLUMNS = 3;
    public static final int ROWS = 5;
    private static final int LIVES = 3;
    public static final int[] PLAYER_START_INDEX = {ROWS, (int)COLUMNS/2};
    public static final int[] RIVAL_START_INDEX = {0, ROWS};

    private int score = 0;
    private int lives = LIVES;

    private Pacmanable player;
    private Pacmanable rival;

    private static GameManager gameManagerInstance = null;

    private GameManager() {
        player = new Player();
        rival = new Rival();
    }

    public static ControllerPacmanable getInstance() {
        if (gameManagerInstance == null){
            gameManagerInstance = new GameManager();
        }
        return gameManagerInstance;
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
    public Pacmanable getPlayer() {
        return player;
    }

    public int getRows(){ return ROWS; }

    public int getCols(){ return COLUMNS; }

    @Override
    public long getDelay() {
        return DELAY;
    }
}
