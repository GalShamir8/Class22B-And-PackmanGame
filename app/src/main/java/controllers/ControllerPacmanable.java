package controllers;

public interface ControllerPacmanable {
    /**
     *
     * @param entity - player or rival to operate function on
     * @param direction - desire direction to update
     */
    void changeDirection(Pacmanable entity, eDirection direction);

    int getRows();

    int getCols();


    long getDelay();

    boolean checkPlayerStartIndex(int row, int col);

    boolean checkRivalStartIndex(int row, int col);

    void tick();
}

