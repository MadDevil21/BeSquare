package org.academiadecodigo.haltistas.besquare.server.logic;

import org.academiadecodigo.haltistas.besquare.server.environment.Block;
import org.academiadecodigo.haltistas.besquare.server.environment.BlockFactory;
import org.academiadecodigo.haltistas.besquare.server.environment.BlockType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogicGridLoader {

    public static Block[][] loadLevel(Levels level) throws IOException {

        String filepath = level.getFilePath();
        System.out.println(filepath);

        BufferedReader fromFile = new BufferedReader(new FileReader(level.getFilePath()));

        // TODO: Investigate:
        // Reading from file, turning it into a long line with \n's at the end, then splitting at the \n's
        // There has to be a much easier way to do this

        String fullString = "";
        String readLine;

        while ((readLine = fromFile.readLine()) != null) {
            fullString += readLine;
            fullString += "\n";

        }

        String[] lines = fullString.split("\n");

        Block[][] grid = new Block[LogicGrid.COLS][LogicGrid.ROWS];


        for (int row = 0; row < lines.length; row++) {
            readRow(row, lines[row], grid);

        }

        return grid;

    }

    private static void readRow(int row, String line, Block[][] grid) {
        System.out.println("Interpreting line " + line);
        for (int col = 0; col < LogicGrid.COLS; col++) {

            grid[col][row] = interpretChar(line.charAt(col), col, row);

            System.out.println(grid[col][row] + " at " + col + " " + row);

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
