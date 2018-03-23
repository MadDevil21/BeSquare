package org.academiadecodigo.haltistas.besquare.client;

public enum Action {

    MOVE_RIGHT(1, 0, "M R"),
    MOVE_LEFT(-1, 0, "M L"),
    JUMP_RIGHT(2, -1, "J R"),
    JUMP_LEFT(-2, -1, "J L"),
    FALLING(0, 1, "F D"),
    RESET_LEVEL(0, 0, "R L");

    private int colChange;
    private int rowChange;
    private String actionText;

    Action(int colChange, int rowChange, String actionText) {
        this.actionText = actionText;
        this.colChange = colChange;
        this.rowChange = rowChange;
    }

    public int getColChange() {
        return colChange;
    }

    public int getRowChange() {
        return rowChange;
    }

    public String getActionText() {
        return actionText;
    }
}
