package org.academiadecodigo.haltistas.besquare.server.environment;

import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;

public class Killer extends Platform{

    public Killer(int col, int row) {
        super(col, row);
    }

    @Override
    public boolean isColliding() {
        return super.isColliding();
    }

    @Override
    public void doCollide(PlayerCharacter player) {
        super.doCollide(player);
    }
}
