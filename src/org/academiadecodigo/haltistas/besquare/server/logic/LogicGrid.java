package org.academiadecodigo.haltistas.besquare.server.logic;

import org.academiadecodigo.haltistas.besquare.server.Character;
import org.academiadecodigo.haltistas.besquare.server.environment.Background;
import org.academiadecodigo.haltistas.besquare.server.environment.Block;
import org.academiadecodigo.haltistas.besquare.server.environment.Exit;
import org.academiadecodigo.haltistas.besquare.server.environment.Platform;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogicGrid {

    private static final int COLS = 50;
    private static final int ROWS = 25;

    private Block[][] grid;

    public LogicGrid() {

        grid = new Block[COLS][ROWS];
    }

    private void load() throws IOException {

        BufferedReader fromFile = new BufferedReader(new FileReader("resources/levels/level_1_tutorial.txt"));
        String level = fromFile.readLine();

        int counter = -1;

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                counter++;

                switch (level.charAt(counter)) {

                    case 'x':

                        grid[col][row] = new Platform();
                        break;

                    case '.':

                        grid[col][row] = new Background();
                        break;

                    case 'e':

                        grid[col][row] = new Exit();
                        break;

                    case '1':
                    case '2':

                        grid[col][row] = new Character();
                        break;

                    default:
                        System.out.println("Error with sunglasses");
                }
            }
        }
    }
}
