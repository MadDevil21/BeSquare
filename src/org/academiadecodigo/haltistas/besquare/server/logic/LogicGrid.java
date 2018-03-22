package org.academiadecodigo.haltistas.besquare.server.logic;

import org.academiadecodigo.haltistas.besquare.client.Action;
import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;
import org.academiadecodigo.haltistas.besquare.server.environment.*;

import java.io.IOException;

public class LogicGrid {

    public static final int COLS = 32;
    public static final int ROWS = 20;

    private PlayerCharacter player1;
    private PlayerCharacter player2;

    private Block[][] grid;
    private Exit exit;
    private int tokensLeft = 0;
    private boolean win;

    //TODO: Grid loader should inform the grid how many tokens there are and where they are
    //TODO: This could be stored in a list or a map

    public LogicGrid() {

        grid = new Block[COLS][ROWS];
        player1 = new PlayerCharacter(COLS, ROWS, 1);
        player2 = new PlayerCharacter(COLS, ROWS, 2);
    }

    public void load(Levels currentLevel) throws IOException {

        grid = LogicGridLoader.loadLevel(currentLevel, this);
        exit.setActive();
    }

    public int[] verifyAction(int playerId, Action selectedAction) {

        PlayerCharacter movingPlayer;

        if (playerId == player1.getId()) {

            movingPlayer = player1;

        } else {

            movingPlayer = player2;
        }

        synchronized (this) {

            int destinationCol;
            int destinationRow;

            if (movingPlayer.isFalling()) {

                destinationCol = movingPlayer.getCol();
                destinationRow = movingPlayer.getRow() + 1;

                return keepFalling(movingPlayer, grid, destinationCol, destinationRow);

            }

            // TODO: Method should recognize when a token was grabbed and remove 1 from the number of tokens left

            destinationCol = movingPlayer.getCol() + selectedAction.getColChange();
            destinationRow = movingPlayer.getRow() + selectedAction.getRowChange();

            Block destinationBlock = grid[destinationCol][destinationRow];

            if (!destinationBlock.isColliding(movingPlayer)) {

                movingPlayer.setPosition(destinationCol, destinationRow);

            }

            if (!grid[movingPlayer.getCol()][movingPlayer.getRow() + 1].isColliding(movingPlayer)) {

                movingPlayer.setFalling(true);
                System.out.println("I'm about to fall");
                verifyAction(movingPlayer.getId(), Action.FALLING);

            }

            destinationBlock.doCollide(movingPlayer);


            if (exit.isColliding(player1, player2) && tokensLeft == 0) {
                win = true;
            }

            return new int[]{player1.getCol(), player1.getRow(), player2.getCol(), player2.getRow()};
        }
    }

    private int[] keepFalling(PlayerCharacter movingPlayer, Block[][] grid, int destinationCol, int destinationRow) {

        if (!grid[destinationCol][destinationRow].isColliding(movingPlayer)) {
            movingPlayer.setPosition(destinationCol, destinationRow);
            System.out.println("Falling!");

            // TODO: This commented out solution makes players fall one block at a time but only
            // when the other one moves; this is not the intended behavior but it could be
            // if we implement a timer.

            // return new int[]{player1.getCol(), player1.getRow(), player2.getCol(), player2.getRow()};

            // Recursive solution: This makes players disappear from a platform and immediately appear below

            verifyAction(movingPlayer.getId(), Action.FALLING);

        }

        movingPlayer.setFalling(false);
        return new int[]{player1.getCol(), player1.getRow(), player2.getCol(), player2.getRow()};

    }

    public PlayerCharacter getPlayer1() {
        return player1;
    }

    public PlayerCharacter getPlayer2() {
        return player2;
    }

    public boolean levelWon() {
        return win;
    }

    public void setExit(Exit exit) {
        this.exit = exit;
    }
}
