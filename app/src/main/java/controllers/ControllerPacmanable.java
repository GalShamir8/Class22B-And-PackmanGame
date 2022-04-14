package controllers;

import common.eDirection;
import models.Pacmanable;

public interface ControllerPacmanable {
    int SCORE_NEGATIVE_FACTOR = -5;
    int SCORE_POSITIVE_FACTOR = 10;
    float RIGHT_SENSOR_BORDER = (float) -2.5;
    float LEFT_SENSOR_BORDER = 3;
    float UP_SENSOR_BORDER = (float) 8.3;
    float DOWN_SENSOR_BORDER = (float) 9.5;

    int getRows();
    int getCols();
    int getLives();

    /**
     * will be implemented in rc2 for the assignment
     * @return current score balance
     */
    int getScore();
    void updateScore(int amount);

    /**
     *
     * @throws Exception when the game's over ( less then 1 live remaining )
     */
    void reduceLives() throws Exception;
    long getDelay();
    Pacmanable getPlayer();
    Pacmanable getRival();

    /**
     *
     * @return amount of lives in game initiate ( start )
     */
    int getLivesStart();

    /**
     *
     * @return random direction from the eDirection enum directions
     */
    eDirection getRandomDirection();

    /**
     * checks if the player and rival next step ( move ) will cause collision ( both in same 'kube' )
     * @return True / False corresponding the condition
     */
    boolean isCollision();
    void handleCollision() throws Exception;
    void executeMove();

    void finishGame();

    eDirection handleSensors(float[] sensorValues);
}

