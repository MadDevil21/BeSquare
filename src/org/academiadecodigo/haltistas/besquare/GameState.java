package org.academiadecodigo.haltistas.besquare;

public enum GameState {

    MENU("M"),
    GAME("G"),
    NEW_LEVEL("N"),
    QUIT("Q");

    private String state;

    GameState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
