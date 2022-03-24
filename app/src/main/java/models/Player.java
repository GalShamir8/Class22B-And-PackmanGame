package models;

import controllers.eDirection;

public class Player implements Pacmanable {
    private eDirection currentDirection = eDirection.UP;

    @Override
    public void setDirection(eDirection direction) {
        if (direction != currentDirection) {
            currentDirection = direction;
        }
    }
}
