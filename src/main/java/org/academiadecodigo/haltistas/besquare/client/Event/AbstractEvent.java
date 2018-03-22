package org.academiadecodigo.haltistas.besquare.client.Event;

import org.academiadecodigo.haltistas.besquare.client.GameField;

public abstract class AbstractEvent implements Event {

    int position1col;
    int position1row;
    int position2col;
    int position2row;

    AbstractEvent(String[] instructions) {

        position1col = toInt(instructions[2]);
        position1row = toInt(instructions[3]);
        position2col = toInt(instructions[4]);
        position2row = toInt(instructions[5]);

    }

    int toInt(String instruction) {
        return Integer.parseInt(instruction);
    }

}
