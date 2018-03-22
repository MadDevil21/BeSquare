package org.academiadecodigo.haltistas.besquare.client.event;

import org.academiadecodigo.haltistas.besquare.client.GameField;

public class TokenEvent extends AbstractEvent {
    public TokenEvent(String[] instructions) {
        super(instructions);
    }

    @Override
    public void process(String[] data, GameField field) {

        if (isCreateToken(data[1])) {

            int col = toInt(data[2]);
            int row = toInt(data[3]);

            field.createTokenSprite(col, row);
            return;
        }

        int indexToKill = toInt(data[2]);
        field.killTokenSprite(indexToKill);

    }

    private boolean isCreateToken(String data) {
        return data.equals("1");
    }
}
