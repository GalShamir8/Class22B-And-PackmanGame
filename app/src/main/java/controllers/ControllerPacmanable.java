package controllers;

import models.Pacmanable;

public interface ControllerPacmanable {

    int getRows();
    int getCols();
    int getScore();
    void updateScore(int amount);
    void reduceLives() throws Exception;
    long getDelay();
    boolean checkPlayerStartIndex(int row, int col);
    boolean checkRivalStartIndex(int row, int col);
    Pacmanable getPlayer();
    int getLivesStart();
    eDirection getRandomDirection();
}

