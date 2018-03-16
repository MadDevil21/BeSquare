package org.academiadecodigo.haltistas.besquare.server;

import org.academiadecodigo.haltistas.besquare.server.environment.Block;
import org.academiadecodigo.haltistas.besquare.server.environment.Collides;

public class Character extends Block implements Collides {

    private int id;

    public Character(int col, int row, int id) {
        super(col, row);
        this.id = id;

    }
}
