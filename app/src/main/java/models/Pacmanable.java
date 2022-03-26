package models;

import controllers.eDirection;

public interface Pacmanable {

    void setDirection(eDirection direction);
    void setPosition(int row, int col);
    boolean equals(Pacmanable other);
    eDirection getDirection();

    /**
     *
     * @return position in an array contains 2 values [0] - row, [1] - column
     */
    int[] getPosition();
    String getName();
}
