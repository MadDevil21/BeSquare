package org.academiadecodigo.haltistas.besquare.server.environment;

import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;

public class Platform extends Block {

    public Platform(int col, int row) {
        super(col, row);
    }

    @Override
    public boolean isColliding(Block block, int col, int row) {
        return super.isColliding(block, col, row);
    }

    @Override
    public void doCollide(PlayerCharacter player) {
        super.doCollide(player);
         // player.stop(); TODO : implement player.stop();
    }
}
