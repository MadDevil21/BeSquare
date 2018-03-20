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

        System.out.println("verifing " + playerId + selectedAction.toString());

        PlayerCharacter movingPlayer;

        if (playerId == player1.getId()) {

            movingPlayer = player1;

        } else {

            movingPlayer = player2;
        }

        synchronized (this) {

            // TODO: Method should recognize when a token was grabbed and remove 1 from the number of tokens left

            System.out.println(movingPlayer);

            int destinationCol = movingPlayer.getCol() + selectedAction.getColChange();
            int destinationRow = movingPlayer.getRow() + selectedAction.getRowChange();

            System.out.println(grid[destinationCol] + " " + grid[destinationCol][destinationRow]);
            Collidable destinationBlock = grid[destinationCol][destinationRow];


            // check if destination block is collidable and if it is sets PlayerCharacter's positions accordingly
            if (!destinationBlock.isColliding(movingPlayer)) {

                movingPlayer.setPosition(destinationCol, destinationRow);
            }

            // checks if PlayerCharacter is going to fall and if so sets it's positions
            checkUnderlyingBlock(movingPlayer, destinationCol, destinationRow);

            destinationBlock.doCollide(movingPlayer);

            if (exit.isColliding(player1, player2)) {
                System.out.println("EXITOITOITOI");
            }

            return new int[]{player1.getCol(), player1.getRow(), player2.getCol(), player2.getRow()};
        }

    }

    private void checkUnderlyingBlock(PlayerCharacter movingPlayer, int destinationCol, int destinationRow) {

        int underlyingBlockRow = destinationRow--;
        Collidable underlyingblock = grid[destinationCol][underlyingBlockRow];

        if (!underlyingblock.isColliding(movingPlayer)) {
            movingPlayer.setPosition(destinationCol, underlyingBlockRow);
            checkUnderlyingBlock(movingPlayer, destinationCol, underlyingBlockRow);
        }
    }

    public PlayerCharacter getPlayer1() {
        return player1;
    }

    public PlayerCharacter getPlayer2() {
        return player2;
    }

    public int getTokensLeft() {
        return tokensLeft;

    }

    public boolean activeExit() {
        return exit.isActive();

    }

    public void setExit(Exit exit) {
        this.exit = exit;
    }
}
