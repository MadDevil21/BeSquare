package org.academiadecodigo.haltistas.besquare.client;

public enum Action {
    MOVE_RIGHT(1, 0),
    MOVE_LEFT(-1, 0),
    JUMP_RIGHT(1, 1),
    JUMP_LEFT(-1, 1);

    private int colChange;
    private int rowChange;

    Action(int colChange, int rowChange) {
        this.colChange = colChange;
        this.rowChange = rowChange;
    }

    public int getColChange() {
        return colChange;
    }

    public int getRowChange() {
        return rowChange;
    }
}
