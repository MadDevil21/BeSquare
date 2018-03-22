package org.academiadecodigo.haltistas.besquare.server.logic;

import org.academiadecodigo.haltistas.besquare.client.Action;
import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;
import org.academiadecodigo.haltistas.besquare.server.environment.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LogicGrid {

    public static final int COLS = 32;
    public static final int ROWS = 20;

    private PlayerCharacter player1;
    private PlayerCharacter player2;

    private Block[][] grid;
    private Exit exit;
    private Map<Integer, Token> tokenMap;
    private boolean win;


    //TODO: Grid loader should inform the grid how many tokens there are and where they are
    //TODO: This could be stored in a list or a map

    public LogicGrid() {

        grid = new Block[COLS][ROWS];
        player1 = new PlayerCharacter(COLS, ROWS, 1);
        player2 = new PlayerCharacter(COLS, ROWS, 2);
        tokenMap = new HashMap<>();

    }

    public void load(Levels currentLevel) throws IOException {

        grid = LogicGridLoader.loadLevel(currentLevel, this);
        exit.setActive();
    }

    public int[] verifyAction(int playerId, Action selectedAction) {

        PlayerCharacter movingPlayer = currentCharacter(playerId);

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


            if (exit.isColliding(player1, player2) && tokenMap.isEmpty()) {
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

    public void addTokenToMap(Token token) {
        int size = tokenMap.size();
        tokenMap.put(size, token);

    }

    private PlayerCharacter currentCharacter(int id) {
        PlayerCharacter currentCharacter = null;

        if (id == player1.getId()) {

            currentCharacter = player1;

        } else {

            currentCharacter = player2;
        }

        return currentCharacter;

    }

    public Map<Integer, Token> getTokenMap() {
        return tokenMap;
    }

    public int checkTokenCollisions(int id) {

        int eatenTokenIndex = -1;

        PlayerCharacter checkedCharacter = currentCharacter(id);

        for (Integer index : tokenMap.keySet()) {

            if (tokenMap.get(index) == null) {
                continue;
            }

            Token checkedToken = tokenMap.get(index);

            if (checkedToken.isColliding(checkedCharacter)) {
                eatenTokenIndex = index;

                System.out.println("Om nom nom token # " + eatenTokenIndex);
                tokenMap.remove(eatenTokenIndex);

                return eatenTokenIndex;

            }

        }

        return eatenTokenIndex;

    }


    public void removeToken(Integer index) {
        tokenMap.remove(index);

    }

    public void setExit(Exit exit) {
        this.exit = exit;
    }
}
