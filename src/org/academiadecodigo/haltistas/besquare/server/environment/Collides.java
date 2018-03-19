package org.academiadecodigo.haltistas.besquare.server.environment;

import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;

public interface Collides {

    boolean isColliding(Block block, int col, int row);

    void doCollide(PlayerCharacter player);
}
