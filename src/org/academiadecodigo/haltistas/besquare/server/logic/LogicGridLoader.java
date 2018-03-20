package org.academiadecodigo.haltistas.besquare.server.logic;

import org.academiadecodigo.haltistas.besquare.server.environment.Block;
import org.academiadecodigo.haltistas.besquare.server.environment.BlockFactory;
import org.academiadecodigo.haltistas.besquare.server.environment.BlockType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogicGridLoader {

    public static Block[][] loadLevel(Levels level) throws IOException {

        BufferedReader fromFile = new BufferedReader(new FileReader(level.getFilePath()));

        Block[][] grid = new Block[LogicGrid.COLS][LogicGrid.ROWS];

        String readLine;

        int workingRow = 0;

        while ((readLine = fromFile.readLine()) != null) {
            readRow(workingRow, readLine, grid);
            workingRow++;

        }

        return grid;

    }

    private static void readRow(int row, String line, Block[][] grid) {
        System.out.println("Interpreting line " + line);
        for (int col = 0; col < LogicGrid.COLS; col++) {

            grid[col][row] = interpretChar(line.charAt(col), col, row);

        }

    }

    private static Block interpretChar(char currentChar, int col, int row) {

        Block returnBlock = null;

        switch (currentChar) {

            case 'x':

                returnBlock = BlockFactory.createBlock(BlockType.PLATFORM, col, row);
                break;
            case '.':

                returnBlock = BlockFactory.createBlock(BlockType.BACKGROUND, col, row);
                break;

            case 'e':

                returnBlock = BlockFactory.createBlock(BlockType.EXIT, col, row);
                break;

            case '1':

                returnBlock = BlockFactory.createBlock(BlockType.CHARACTER_1, col, row);
                break;

            case '2':

                returnBlock = BlockFactory.createBlock(BlockType.CHARACTER_2, col, row);
                break;

            default:
                System.err.println("Problem in interpretChar method");
        }

        return returnBlock;

    }

}
