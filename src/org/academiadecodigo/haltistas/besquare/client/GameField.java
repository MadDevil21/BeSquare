package org.academiadecodigo.haltistas.besquare.client;

import org.academiadecodigo.haltistas.besquare.FilePath;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.Map;

public class GameField {

    static final int PADDING = 10;
    public static final int CELL_SIZE = 30;

    private Picture background;
    private CharacterSprite p1Sprite;
    private CharacterSprite p2Sprite;


    protected void moveCharacters(int player1col, int player1row, int player2col, int player2row) { // TODO: temporary solution

        int player1X = logicToCoord(player1col);
        int player1Y = logicToCoord(player1row);
        int player2X = logicToCoord(player2col);
        int player2Y = logicToCoord(player2row);

        p1Sprite.move(player1X, player1Y);
        p2Sprite.move(player2X, player2Y);
        System.out.println("moving characters");
    }

    protected void loadBackground(String path) {

        if (background != null) {
            background.delete();
        }

        background = new Picture(PADDING, PADDING, path);
        background.draw();
    }


    protected void loadCharacters(int player1col, int player1row, int player2col, int player2row) {

        p1Sprite = new CharacterSprite(FilePath.PLAYER1);
        p2Sprite = new CharacterSprite(FilePath.PLAYER2);

        int player1X = logicToCoord(player1col);
        int player1Y = logicToCoord(player1row);
        int player2X = logicToCoord(player2col);
        int player2Y = logicToCoord(player2row);

        p1Sprite.setPosition(player1X, player1Y);
        p2Sprite.setPosition(player2X, player2Y);

        p1Sprite.getSprite().draw();
        p2Sprite.getSprite().draw();

        System.out.println("loaded characters");
    }

    private int logicToCoord(int number){

        return number * CELL_SIZE;

    }
}



