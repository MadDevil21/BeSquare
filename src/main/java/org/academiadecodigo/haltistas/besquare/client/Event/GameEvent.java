package org.academiadecodigo.haltistas.besquare.client.Event;

import org.academiadecodigo.haltistas.besquare.client.GameField;

public class GameEvent extends AbstractEvent {

    public GameEvent(String[] instructions) {
        super(instructions);
    }

    @Override
    public void process(String[] data, GameField field) {

        field.moveCharacters(position1col, position1row, position2col, position2row);

    }

}
