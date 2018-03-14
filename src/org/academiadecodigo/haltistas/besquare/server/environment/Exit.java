package org.academiadecodigo.haltistas.besquare.server.environment;

public class Exit extends Background implements Activates {

    public Exit(int col, int row) {
        super(col, row);
    }

    private boolean active;

    @Override
    public void activate() {

    }

    @Override
    public boolean isActive() {
        return active;

    }

}
