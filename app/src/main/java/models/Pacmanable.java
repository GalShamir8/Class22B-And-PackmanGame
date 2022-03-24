package models;

import controllers.eDirection;

public interface Pacmanable {

    void setDirection(eDirection direction);
    void setPosition(int row, int col);
    boolean equals(Pacmanable other);
}
