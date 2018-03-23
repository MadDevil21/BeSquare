package org.academiadecodigo.haltistas.besquare;

public enum GameState {

    MAIN_MENU("M"),
    CONNECT_MENU("E"),
    GAME("G"),
    NEW_LEVEL("N"),
    QUIT("Q"),
    TOKEN("T");

    private String state;

    GameState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
