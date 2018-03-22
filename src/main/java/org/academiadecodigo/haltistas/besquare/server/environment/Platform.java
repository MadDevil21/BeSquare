package org.academiadecodigo.haltistas.besquare.server.environment;

import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;

public class Platform extends Block {

    public Platform(int col, int row) {
        super(col, row);
    }

    @Override
    public boolean isColliding(PlayerCharacter playerCharacter) {
        return true;
    }

    @Override
    public void doCollide(PlayerCharacter player) {
        super.doCollide(player);
    }
}
