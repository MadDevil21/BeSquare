package org.academiadecodigo.haltistas.besquare.server.logic;

import org.academiadecodigo.haltistas.besquare.server.environment.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogicGrid {

    public static final int COLS = 50;
    public static final int ROWS = 25;

    private Block[][] grid;

    public LogicGrid() {

        grid = new Block[COLS][ROWS];
    }

    private void load(Levels level) throws IOException {

        BufferedReader fromFile = new BufferedReader(new FileReader(Levels.LEVEL_1.getFilePath()));

        grid = LogicGridLoader.loadLevel(level);

    }
}
