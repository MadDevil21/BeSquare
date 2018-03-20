package org.academiadecodigo.haltistas.besquare.server.environment;

import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;

public interface Collidable {

    boolean isColliding();

    void doCollide(PlayerCharacter player);
}
