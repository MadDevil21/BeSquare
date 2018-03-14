package org.academiadecodigo.haltistas.besquare.server.environment;

public class Interactive extends Platform implements Activates {

    public Interactive(int col, int row) {
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
