package org.academiadecodigo.haltistas.besquare.server;

import org.academiadecodigo.haltistas.besquare.server.environment.Block;
import org.academiadecodigo.haltistas.besquare.server.environment.Collides;

public class PlayerCharacter extends Block implements Collides {

    private final int id;

    public PlayerCharacter(int col, int row, int id) {
        super(col, row);
        this.id = id;

    }

    public int getId() {
        return id;
    }

}
