package org.academiadecodigo.haltistas.besquare.server.environment;

import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;

public interface Collidable {

    boolean isColliding(PlayerCharacter playerCharacter);

    void doCollide(PlayerCharacter player);
}
