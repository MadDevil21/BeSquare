package org.academiadecodigo.haltistas.besquare.server.environment;

import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;

public class Background extends Block {

    public Background(int col, int row) {
        super(col, row);
    }

    @Override
    public boolean isColliding(PlayerCharacter playerCharacter) {
        return false;
    }

}
