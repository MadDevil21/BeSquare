package org.academiadecodigo.haltistas.besquare.server.environment;

import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;

public class MovingPlatform extends Platform{

    private KeyColor color;
    private boolean isActive = false;

    public MovingPlatform(int col, int row, KeyColor color) {
        super(col, row);
        this.color = color;

    }

    @Override
    public boolean isColliding(PlayerCharacter playerCharacter) {

        return !isActive;

    }

    public void open(){
        isActive = true;

    }

    public void close(){
        isActive = false;

    }

    public KeyColor getColor() {
        return color;
    }
}
