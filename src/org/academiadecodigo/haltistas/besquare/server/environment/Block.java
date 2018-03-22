package org.academiadecodigo.haltistas.besquare.server.environment;

import org.academiadecodigo.haltistas.besquare.client.Action;
import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;

public abstract class Block implements Collidable {

    private int col;
    private int row;
    private boolean isColliding;

    public Block(int col, int row) {
        this.col = col;
        this.row = row;

    }

    @Override
    public boolean isColliding(PlayerCharacter playerCharacter) {
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
