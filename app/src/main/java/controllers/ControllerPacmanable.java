package controllers;

import models.Pacmanable;

public interface ControllerPacmanable {

    int getRows();

    int getCols();


    long getDelay();

    boolean checkPlayerStartIndex(int row, int col);

    boolean checkRivalStartIndex(int row, int col);

    Pacmanable getPlayer();
}

