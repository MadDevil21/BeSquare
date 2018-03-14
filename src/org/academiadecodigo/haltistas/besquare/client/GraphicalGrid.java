package org.academiadecodigo.haltistas.besquare.client;


import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GraphicalGrid {

    static int PADDING = 10;
    static final int NUM_COLS = 50;
    static final int NUM_ROWS = 25;
    static final int CELL_SIZE = 20;
    private Rectangle[][] gridCells;


    private GraphicalGrid() {
        gridCells = new Rectangle[NUM_ROWS][NUM_COLS];
    }

    public static void main(String[] args) {

        GraphicalGrid grid = new GraphicalGrid();
        grid.createGrid();
        grid.drawGrid();
    }


    private void createGrid() {

        for (int rows = 0; rows < NUM_ROWS; rows++) {
            for (int cols = 0; cols < NUM_COLS; cols++) {

                int rowPosition = (PADDING + (CELL_SIZE * rows));
                int colPosition = (PADDING + (CELL_SIZE * cols));

                gridCells[rows][cols] = new Rectangle(colPosition, rowPosition, CELL_SIZE, CELL_SIZE);
                gridCells[rows][cols].draw();

            }
        }
    }

    public static String load() {

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


    private void drawGrid() {

        String loadedString = load();

        for (int col = 0; col < NUM_COLS; col++) {
            for (int row = 0; row < NUM_ROWS; row++) {
                char color = loadedString.charAt(col * NUM_ROWS + row);
                paintCell(col, row, color);


            }
        }

    }


    public void paintCell(int col, int row, char color) {

        Rectangle rectangle = gridCells[row][col];
        rectangle.fill();

        switch (color) {

            case 'i':
                rectangle.setColor(Color.BLACK);
                break;

            case 'x':
                rectangle.setColor(Color.RED);
                break;

            case 'o':
                rectangle.setColor(Color.WHITE);
                break;

            case 'd':
                rectangle.setColor(Color.GREEN);
                break;

            default:
                rectangle.setColor(Color.WHITE);

        }

    }
}
