package org.academiadecodigo.haltistas.besquare.server.environment;

import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;

public abstract class Block implements Collides{

    private int col;
    private int row;
    boolean isColliding;

    public Block(int col, int row) {
        this.col = col;
        this.row = row;

    }

    @Override
    public boolean isColliding(Block block, int col, int row) {
        return isColliding;
    }

    @Override
    public void doCollide(PlayerCharacter player) {

    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
