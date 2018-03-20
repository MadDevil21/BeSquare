package org.academiadecodigo.haltistas.besquare.server.logic;

import org.academiadecodigo.haltistas.besquare.client.Action;
import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;
import org.academiadecodigo.haltistas.besquare.server.environment.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogicGrid {

    public static final int COLS = 32;
    public static final int ROWS = 20;

    private PlayerCharacter player1;
    private PlayerCharacter player2;

    private Block[][] grid;

    public LogicGrid() {

        grid = new Block[COLS][ROWS];
        player1 = new PlayerCharacter(COLS, ROWS, 1);
        player2 = new PlayerCharacter(COLS, ROWS, 2);

    }

    public void load(Levels currentLevel) throws IOException {

        BufferedReader fromFile = new BufferedReader(new FileReader(currentLevel.getMatrix()));

        grid = LogicGridLoader.loadLevel(currentLevel, this);
    }

    public int[] verifyAction(int playerId, Action selectedAction) {

        System.out.println("verifing " + playerId + selectedAction.toString());

        PlayerCharacter movingPlayer;

        if (playerId == player1.getId()) {

            movingPlayer = player1;

        } else {

            movingPlayer = player2;
        }

        int destinationCol = movingPlayer.getCol() + selectedAction.getColChange();
        int destinationRow = movingPlayer.getRow() + selectedAction.getRowChange();

        Collidable destinationBlock = grid[destinationCol][destinationRow];

        if (destinationBlock.isColliding()){

            destinationBlock.doCollide(movingPlayer);

            int[] positions = {player1.getCol(), player1.getRow(), player2.getCol(), player2.getRow()};

            return positions;
        }

        movingPlayer.setPosition(destinationCol, destinationRow);

        int[] positions = {player1.getCol(), player1.getRow(), player2.getCol(), player2.getRow()};

        return positions;
    }

    public PlayerCharacter getPlayer1() {
        return player1;
    }

    public PlayerCharacter getPlayer2() {
        return player2;
    }
}
