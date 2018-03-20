package org.academiadecodigo.haltistas.besquare.server;

public class PlayerCharacter {

    private final int id;
    private int col;
    private int row;

    public PlayerCharacter(int col, int row, int id) {
        this.col = col;
        this.row = row;
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setPosition(int col, int row) {
        this.col = col;
        this.row = row;
    }
}
