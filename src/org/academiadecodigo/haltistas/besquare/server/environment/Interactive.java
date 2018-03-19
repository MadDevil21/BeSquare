package org.academiadecodigo.haltistas.besquare.server.environment;

import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;

public class Interactive extends Platform {

    public Interactive(int col, int row) {
        super(col, row);
    }

    private boolean active;

    @Override
    public boolean isColliding(Block block, int col, int row) {
        return super.isColliding(block, col, row);
    }

    @Override
    public void doCollide(PlayerCharacter player) {
        super.doCollide(player);
    }
}
