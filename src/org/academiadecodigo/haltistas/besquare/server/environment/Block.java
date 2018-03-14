package org.academiadecodigo.haltistas.besquare.server.environment;

public abstract class Block {

    private int col;
    private int row;

    public Block(int col, int row) {
        this.col = col;
        this.row = row;

    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
