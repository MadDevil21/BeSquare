package org.academiadecodigo.haltistas.besquare.server.logic;

import org.academiadecodigo.haltistas.besquare.client.Action;
import org.academiadecodigo.haltistas.besquare.server.PlayerCharacter;
import org.academiadecodigo.haltistas.besquare.server.environment.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogicGrid {

    private static final int COLS = 32;
    private static final int ROWS = 20;

    private Block player1;
    private Block player2;
    private Levels currentLevel;

    private Block[][] grid;

    public LogicGrid() {

        grid = new Block[COLS][ROWS];
    }

    public void load(Levels currentLevel) throws IOException {

        this.currentLevel = currentLevel;
        BufferedReader fromFile = new BufferedReader(new FileReader(currentLevel.getMatrix()));

        int counter = -1;

        for (int row = 0; row < ROWS; row++) {

            String levelRow = fromFile.readLine();

            for (int col = 0; col < COLS; col++) {

                counter++;

                switch (levelRow.charAt(counter)) {

                    case 'x':

                        grid[col][row] = BlockFactory.createBlock(BlockType.PLATFORM, col, row);
                        break;

                    case '.':

                        grid[col][row] = BlockFactory.createBlock(BlockType.BACKGROUND, col, row);
                        break;

                    case 'e':

                        grid[col][row] = BlockFactory.createBlock(BlockType.EXIT, col, row);
                        break;

                    case '1':

                        player1 = BlockFactory.createBlock(BlockType.CHARACTER_1, col, row);
                        grid[col][row] = player1;
                        break;

                    case '2':

                        player2 = BlockFactory.createBlock(BlockType.CHARACTER_2, col, row);
                        grid[col][row] = player2;
                        break;

                    default:
                        System.out.println("Error with sunglasses");
                }
            }
        }
    }

    public int[] verifyAction(int playerId, Action selectedAction, CollisionDetector collisionDetector) {

        Block movingPlayer;

        if (playerId == 1) {
            movingPlayer = player1;
        } else {
            movingPlayer = player2;
        }

        int originPlayerCol = movingPlayer.getCol();
        int originPlayerRow = movingPlayer.getRow();

        int destinationCol = selectedAction.getColChange();
        int destinationRow = selectedAction.getRowChange();

        int transitionCol = originPlayerCol;
        int transitionRow = originPlayerRow;

        grid[originPlayerCol][originPlayerRow] = grid[destinationCol][destinationRow];
        grid[transitionCol][transitionRow] = grid[originPlayerCol][originPlayerRow];



        return null;
    }
}
