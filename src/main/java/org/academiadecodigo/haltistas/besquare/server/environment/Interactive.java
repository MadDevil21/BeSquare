package org.academiadecodigo.haltistas.besquare.server.environment;

import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;

public class Interactive extends Platform {

    public Interactive(int col, int row) {
        super(col, row);
    }

    private boolean active;

    @Override
    public boolean isColliding(PlayerCharacter playerCharacter) {
        return super.isColliding(playerCharacter);
    }

    @Override
    public void doCollide(PlayerCharacter player) {
        super.doCollide(player);
    }
}
