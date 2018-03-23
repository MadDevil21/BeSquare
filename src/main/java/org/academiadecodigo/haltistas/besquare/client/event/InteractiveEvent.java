package org.academiadecodigo.haltistas.besquare.client.event;

import org.academiadecodigo.haltistas.besquare.FilePath;
import org.academiadecodigo.haltistas.besquare.client.GameField;
import org.academiadecodigo.haltistas.besquare.server.environment.KeyColor;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.Map;

public class InteractiveEvent extends AbstractEvent {

    public InteractiveEvent(String[] instructions) {
        super(instructions);
    }

    @Override
    public void process(String[] data, GameField field) {

        KeyColor color = getColor(toInt(data[1]));

        Map<KeyColor, Picture> workingMap = null;

        int option = toInt(data[2]);

        switch (option) {
            case 0:
                workingMap = field.getButtonSprites();
                break;
            case 1:
                workingMap = field.getDoorSprites();
                break;
        }

        int action = toInt(data[3]);

        int x = logicToCoord(toInt(data[4])) + GameField.PADDING;
        int y = logicToCoord(toInt(data[5])) + GameField.PADDING;

        switch (action){
            case 0:
                Picture picture = new Picture(x, y, picturePicker(color, option));
                picture.draw();
                workingMap.put(color, picture);
                break;
            case 1:
                field.getDoorSprites().get(color).delete();
                break;
        }


    }

    private KeyColor getColor(int option){
        if (option == 0){
            return KeyColor.RED;
        }

        return KeyColor.GREEN;
    }

    private int logicToCoord(int number) {

        return number * GameField.CELL_SIZE;
    }

    private String picturePicker(KeyColor color, int type){
        if(color == KeyColor.RED){
            return redObjects(type);
        }
        return greenObjects(type);
    }

    private String redObjects(int type){
        if (type == 0){
            return FilePath.RED_BUTTON;

        }
        return FilePath.RED_DOOR;
    }

    private String greenObjects(int type){
        if (type == 0){
            return FilePath.GREEN_BUTTON;

        }
        return FilePath.GREEN_DOOR;

    }

}
