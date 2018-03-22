package org.academiadecodigo.haltistas.besquare.server.logic;

import org.academiadecodigo.haltistas.besquare.server.environment.*;
import org.academiadecodigo.haltistas.besquare.util.Message;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogicGridLoader {

    public static Block[][] loadLevel(Levels level, LogicGrid logicGrid) throws IOException {


        BufferedReader fromFile = new BufferedReader(new FileReader(
                LogicGridLoader.class.getResource(level.getMatrix()).getFile()));

        Block[][] grid = new Block[LogicGrid.COLS][LogicGrid.ROWS];

        String readLine;

        int workingRow = 0;

        while ((readLine = fromFile.readLine()) != null) {

            readRow(workingRow, readLine, grid, logicGrid);
            workingRow++;
        }

        return grid;
    }

    private static void readRow(int row, String line, Block[][] grid, LogicGrid logicGrid) {

        System.out.println(Message.INTERPRET_LINE + line);

        for (int col = 0; col < LogicGrid.COLS; col++) {

            grid[col][row] = interpretChar(line.charAt(col), col, row, logicGrid);
        }
    }

    private static Block interpretChar(char currentChar, int col, int row, LogicGrid logicGrid) {

        Block returnBlock = null;

        switch (currentChar) {

            case 'x':

                returnBlock = BlockFactory.createBlock(BlockType.PLATFORM, col, row);
                break;

            case '.':

                returnBlock = BlockFactory.createBlock(BlockType.BACKGROUND, col, row);
                break;

            case 't':

                logicGrid.addTokenToMap(new Token(col, row));
                returnBlock = BlockFactory.createBlock(BlockType.BACKGROUND, col, row);
                break;

            case 'e':

                Exit exit = BlockFactory.createExit(col, row);
                logicGrid.setExit(exit);
                returnBlock = exit;
                break;

            case '1':

                logicGrid.getPlayer1().setPosition(col, row);
                returnBlock = BlockFactory.createBlock(BlockType.BACKGROUND, col, row);
                break;

            case '2':

                logicGrid.getPlayer2().setPosition(col, row);
                returnBlock = BlockFactory.createBlock(BlockType.BACKGROUND, col, row);
                break;

            default:
                System.err.println(Message.ERR_INTERPRET);
        }

        return returnBlock;
    }
}