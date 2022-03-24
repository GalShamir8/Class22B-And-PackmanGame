package controllers;

import models.Pacmanable;

public interface ControllerPacmanable {
    int SCORE_NEGATIVE_FACTOR = -5;
    int SCORE_POSITIVE_FACTOR = 10;
    int getRows();
    int getCols();
    int getScore();
    void updateScore(int amount);
    void reduceLives() throws Exception;
    long getDelay();
    boolean checkPlayerStartIndex(int row, int col);
    boolean checkRivalStartIndex(int row, int col);
    Pacmanable getPlayer();
    Pacmanable getRival();
    int getLivesStart();
    eDirection getRandomDirection();
    boolean isCollision();
    void executeMove();
    int getLives();
}

