package org.academiadecodigo.haltistas.besquare.client;

public class TaskManager {

    private GameField field;


    TaskManager(GameField gameField) {
        this.field = gameField;
    }

    protected void interpret(String fromServer) {

        String[] instructions = fromServer.split(" ");

        String backgroundPath = instructions[1];
        int player1X = toInt(instructions[4]);
        System.out.println(player1X);
        int player1Y = toInt(instructions[5]);
        System.out.println(player1Y);
        int player2X = toInt(instructions[6]);
        System.out.println(player2X);
        int player2Y = toInt(instructions[7]);
        System.out.println(player2Y);

        if (!backgroundPath.equals("x")) {
            field.loadBackground(backgroundPath);
            field.loadCharacters(player1X, player1Y, player2X, player2Y);
        }

        field.moveCharacters(player1X, player1Y, player2X, player2Y);
        System.out.println("moving characters");

    }


    private int toInt(String instruction) {
        return Integer.parseInt(instruction);
    }
}
