package models;

import common.eDirection;

public class Player implements Pacmanable {
    private int id;
    private String name;
    private eDirection currentDirection = eDirection.UP;
    private int[] currentPosition;

    /**
     * default constructor
     */
    public Player() { }

    public Player(int row, int col, int id, String name) {
        this.id = id;
        currentPosition = new int[2];
        setPosition(row, col);
        this.name = name;
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

    @Override
    public String getName() {
        return name;
    }
}
