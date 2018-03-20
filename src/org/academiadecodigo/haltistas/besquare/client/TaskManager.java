package org.academiadecodigo.haltistas.besquare.client;

import org.academiadecodigo.haltistas.besquare.GameState;
import org.academiadecodigo.haltistas.besquare.server.logic.Levels;

public class TaskManager {

    private GameField field;

    TaskManager(GameField gameField) {
        this.field = gameField;
    }

    protected void interpret(String fromServer) {

        System.out.println("Received from server: " + fromServer);
        String[] instructions = fromServer.split(" ");

        String backgroundPath = "x";

        int player1col = toInt(instructions[2]);
        int player1row = toInt(instructions[3]);
        int player2col = toInt(instructions[4]);
        int player2row = toInt(instructions[5]);

        if (instructions[0].equals(GameState.NEW_LEVEL.name())) {
            backgroundPath = changeLevel(instructions[1]);
            
            field.loadBackground(backgroundPath);
            field.loadCharacters(player1col, player1row, player2col, player2row);
        }

        field.moveCharacters(player1col, player1row, player2col, player2row);
        System.out.println("moving characters");

    }

    private String changeLevel(String levelName) {

        String backgroundPath = "x";

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
