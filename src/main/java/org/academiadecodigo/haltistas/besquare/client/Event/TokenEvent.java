package org.academiadecodigo.haltistas.besquare.client.Event;

import org.academiadecodigo.haltistas.besquare.client.GameField;

public class TokenEvent extends AbstractEvent {
    public TokenEvent(String[] instructions) {
        super(instructions);
    }

    @Override
    public void process(String[] data, GameField field) {

        if (data[1].equals("1")) {

            int col = toInt(data[2]);
            int row = toInt(data[3]);

            field.createTokenSprite(col, row);

        } else if (data[1].equals("0")) {

            field.killTokenSprite(toInt(data[2]));

        }

    }
}
