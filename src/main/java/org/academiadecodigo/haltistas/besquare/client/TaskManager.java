package org.academiadecodigo.haltistas.besquare.client;

import org.academiadecodigo.haltistas.besquare.GameState;
import org.academiadecodigo.haltistas.besquare.server.logic.Levels;
import org.academiadecodigo.haltistas.besquare.util.Message;

public class TaskManager {

    private GameField field;
    private String backgroundPath;

    TaskManager(GameField gameField) {

        this.field = gameField;
        backgroundPath = "x";
    }

    protected void interpret(String fromServer) {

        //System.out.println(Message.HALP_BROADCAST + fromServer);
        String[] instructions = fromServer.split(" ");

        int position1col = toInt(instructions[2]);
        int position1row = toInt(instructions[3]);
        int position2col = toInt(instructions[4]);
        int position2row = toInt(instructions[5]);


        if (instructions[0].equals(GameState.NEW_LEVEL.name())) {

            backgroundPath = changeLevel(instructions[1]);

            field.loadBackground(backgroundPath);
            field.loadCharacters(position1col, position1row, position2col, position2row);
            return;
        }

        // TODO refactor this into methods

        if (instructions[0].equals(GameState.TOKEN.name())) {

            if (instructions[1].equals("1")) {

                int col = toInt(instructions[2]);
                int row = toInt(instructions[3]);

                field.createTokenSprite(col, row);

            } else if (instructions[1].equals("0")) {

                field.killTokenSprite(toInt(instructions[2]));

            }
            return;

        }

        field.moveCharacters(position1col, position1row, position2col, position2row);
    }

    private String changeLevel(String levelName) {

        for (Levels levels : Levels.values()) {

            if (levelName.equals(levels.name())) {
                backgroundPath = levels.getBackground();
            }
        }

        return backgroundPath;
    }

    private int toInt(String instruction) {
        return Integer.parseInt(instruction);
    }
}
