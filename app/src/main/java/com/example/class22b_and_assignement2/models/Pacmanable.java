package com.example.class22b_and_assignement2.models;

import com.example.class22b_and_assignement2.common.eDirection;

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
