package org.academiadecodigo.haltistas.besquare.server.environment;

import org.academiadecodigo.haltistas.besquare.client.Action;
import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;

public class Killer extends Platform{

    public Killer(int col, int row) {
        super(col, row);
    }


    @Override
    public boolean isColliding(PlayerCharacter playerCharacter) {
        return super.isColliding(playerCharacter);
    }

    @Override
    public void doCollide(PlayerCharacter player) {
        super.doCollide(player);
    }
}
