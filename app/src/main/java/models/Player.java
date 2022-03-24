package models;

import controllers.eDirection;

public class Player implements Pacmanable {
    private int id;
    private eDirection currentDirection = eDirection.UP;
    private int[] currentPosition;

    /**
     * default constructor
     */
    public Player() { }

    public Player(int row, int col, int id) {
        this.id = id;
        currentPosition = new int[2];
        setPosition(row, col);
    }

    @Override
    public void setDirection(eDirection direction) {
        if (direction != currentDirection) {
            currentDirection = direction;
        }
    }

    @Override
    public void setPosition(int row, int col) {
        currentPosition[0] = row;
        currentPosition[1] = col;
    }

    @Override
    public boolean equals(Pacmanable other) {
        if(other instanceof Player) {
            return id == ((Player) other).id;
        }
        return false;
    }

    @Override
    public eDirection getDirection() {
        return currentDirection;
    }

    @Override
    public int[] getPosition() {
        return currentPosition;
    }
}
