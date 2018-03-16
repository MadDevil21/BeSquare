package org.academiadecodigo.haltistas.besquare.client;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GraphicalGrid {

    static int PADDING = 10;
    static final int NUM_COLS = 32;
    static final int NUM_ROWS = 20;
    static final int CELL_SIZE = 30;
    private Rectangle[][] gridCells;


    private GraphicalGrid() {
        gridCells = new Rectangle[NUM_ROWS][NUM_COLS];
    }


    public static String loadFile() {

        FileReader reader = null;
        String stringToReturn = "";

        try {

            reader = new FileReader("assets/test_grid.txt");
            BufferedReader bReader = new BufferedReader(reader);

            stringToReturn += bReader.readLine();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());

        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return stringToReturn;
    }
}



