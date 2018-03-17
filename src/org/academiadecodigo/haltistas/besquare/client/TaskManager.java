package org.academiadecodigo.haltistas.besquare.client;

public class TaskManager {


    private Client client;
    private GameField field;
    private boolean loadedGameField;

    public TaskManager(Client client, GameField gameField) {
        this.client = client;
        this.field = gameField;
        this.loadedGameField = false;
    }

    protected void interpret(String fromServer) { // esta merda mete nojo

        String[] instructions = fromServer.split(" ");

        String backgroundPath = instructions[1];
        int player1X = toInt(instructions[4]);
        int player1Y = toInt(instructions[5]);
        int player2X = toInt(instructions[6]);
        int player2Y = toInt(instructions[7]);

       if(!backgroundPath.equals("x")){
           field.loadBackground(backgroundPath);
           field.loadCharacters(player1X, player1Y, player2X, player2Y);
       }

      field.moveCharacters(player1X, player1Y, player2X, player2Y);

    }


    private int toInt(String instruction) {
        return Integer.parseInt(instruction);
    }
}
