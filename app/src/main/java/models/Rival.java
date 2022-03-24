package models;

import controllers.eDirection;

public class Rival implements Pacmanable {
    private eDirection currentDirection = eDirection.UP;

    @Override
    public void setDirection(eDirection direction) {
        if (currentDirection != direction) {
            currentDirection = direction;
        }
    }
}
