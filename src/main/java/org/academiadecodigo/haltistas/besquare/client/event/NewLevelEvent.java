package org.academiadecodigo.haltistas.besquare.client.event;

import org.academiadecodigo.haltistas.besquare.client.GameField;
import org.academiadecodigo.haltistas.besquare.server.logic.Levels;

public class NewLevelEvent extends AbstractEvent {

    private String backgroundPath;

    public NewLevelEvent(String[] instructions) {
        super(instructions);
    }

    @Override
    public void process(String[] data, GameField field) {

        String levelName = data[1];
        String backgroundPath = getBackgroundPath(levelName);

        field.loadBackground(backgroundPath);
        field.loadCharacters(position1col, position1row, position2col, position2row);

    }

    private String getBackgroundPath(String levelName) {


        for (Levels levels : Levels.values()) {

            if (levelName.equals(levels.name())) {
                backgroundPath = levels.getBackground();
            }
        }

        return backgroundPath;
    }


}
