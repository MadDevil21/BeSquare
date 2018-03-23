package org.academiadecodigo.haltistas.besquare.server.environment;

import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;

public class Interactive extends Platform {

    private KeyColor color;

    public Interactive(int col, int row, KeyColor color) {
        super(col, row);
        this.color = color;

    }

    private boolean active;

    @Override
    public boolean isColliding(PlayerCharacter playerCharacter) {
        return playerCharacter.getCol() == super.getCol() &&
                playerCharacter.getRow() == super.getRow();
    }

    public KeyColor getColor() {
        return color;
    }
}
