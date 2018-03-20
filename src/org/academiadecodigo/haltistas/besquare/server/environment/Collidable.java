package org.academiadecodigo.haltistas.besquare.server.environment;

import org.academiadecodigo.haltistas.besquare.client.Action;
import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;

public interface Collidable {

    boolean isColliding(PlayerCharacter playerCharacter, Action action);

    void doCollide(PlayerCharacter player);
}
