package org.academiadecodigo.haltistas.besquare.server.environment;

import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;

public class Exit extends Background {

    public Exit(int col, int row) {
        super(col, row);
    }

    private boolean active = false;
    private int numberOfPlayers;

    @Override
    public boolean isColliding(PlayerCharacter playerCharacter) {
        return playerCharacter.getRow() == getRow() && playerCharacter.getCol() == getCol();
    }

    public boolean isColliding(PlayerCharacter p1, PlayerCharacter p2) {

        if(!active) {
            return false;
        }

        return p1.getCol() == getCol() &&
                p2.getCol() == getCol() &&
                p1.getRow() == getRow() &&
                p2.getRow() == getRow();
    }

    public void setActive() {
        active = true;
    }

    public boolean isActive() {
        return active;
    }
}
