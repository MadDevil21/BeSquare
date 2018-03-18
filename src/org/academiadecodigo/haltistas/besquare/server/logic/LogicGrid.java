package org.academiadecodigo.haltistas.besquare.server.logic;

import org.academiadecodigo.haltistas.besquare.server.environment.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogicGrid {

    private static final int COLS = 50;
    private static final int ROWS = 25;
    private Levels currentLevel;

    private Block[][] grid;

    public LogicGrid() {

        grid = new Block[COLS][ROWS];
    }

    private void load(Levels currentLevel) throws IOException {

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

                        grid[col][row] = BlockFactory.createBlock(BlockType.CHARACTER_1, col, row);
                        break;

                    case '2':

                        grid[col][row] = BlockFactory.createBlock(BlockType.CHARACTER_2, col, row);
                        break;

                    default:
                        System.out.println("Error with sunglasses");
                }
            }
        }
    }
}
