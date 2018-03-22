package org.academiadecodigo.haltistas.besquare.server.environment;

import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;

public class Token extends Background {

    public Token(int col, int row) {
        super(col, row);
    }

    @Override
    public boolean isColliding(PlayerCharacter playerCharacter) {
        return playerCharacter.getCol() == super.getCol() &&
                playerCharacter.getRow() == super.getRow();

    }
}
