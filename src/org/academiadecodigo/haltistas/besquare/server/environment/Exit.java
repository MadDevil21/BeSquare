package org.academiadecodigo.haltistas.besquare.server.environment;

import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;

public class Exit extends Background {

    public Exit(int col, int row) {
        super(col, row);
    }

    private boolean active;

    @Override
    public boolean isColliding() {
        return super.isColliding();
    }

    @Override
    public void doCollide(PlayerCharacter player) {
        super.doCollide(player);
    }
}
