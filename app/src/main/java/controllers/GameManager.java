package controllers;

import models.Pacmanable;
import models.Player;


public class GameManager implements ControllerPacmanable{
    private static int id = 0;
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
        player = new Player(PLAYER_START_INDEX[0], PLAYER_START_INDEX[1], idGenerator());
        rival = new Player(RIVAL_START_INDEX[0], RIVAL_START_INDEX[1], idGenerator());
    }

    private int idGenerator() {
        return ++id;
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

    @Override
    public int getLivesStart() {
        return LIVES;
    }

    @Override
    public eDirection getRandomDirection() {
        eDirection[] directionArray = eDirection.values();
        int randomIndex = (int) (Math.random() * (directionArray.length));
        return directionArray[randomIndex];
    }

    public int getRows(){ return ROWS; }

    public int getCols(){ return COLUMNS; }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void updateScore(int amount) {
        if(amount < 0 && amount > score){
            score = 0;
        }else{
            score += amount;
        }
    }

    @Override
    public void reduceLives() throws Exception {
        if(LIVES - lives < 2){
            throw new Exception("Game Over");
        }
        lives--;
    }

    @Override
    public long getDelay() {
        return DELAY;
    }
}
