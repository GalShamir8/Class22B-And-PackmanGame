package controllers;

public interface ControllerPacmanable {
    /**
     *
     * @param entity - player or rival to operate function on
     * @param edirection - desire direction to update
     */
    void changeDirection(Pacmanable entity, eDirection edirection);

    boolean checkPlayerStartIndex(int row, int col);

    boolean checkRivalStartIndex(int row, int col);
}

