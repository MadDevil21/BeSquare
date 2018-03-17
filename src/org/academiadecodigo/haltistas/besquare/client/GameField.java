package org.academiadecodigo.haltistas.besquare.client;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import java.util.Map;

public class GameField {

    static int PADDING = 10;
    static final int CELL_SIZE = 30;

    private Picture background;
    private Map<Integer, Picture> charMap;


    public void moveCharacter(int playerID, Action action) {

        switch (action) {
            case LEFT:
                moveLeft(playerID);
                break;
            case RIGHT:
                moveRight(playerID);
                break;
            case JUMP_LEFT:
                // not implemented yet
                break;
            case JUMP_RIGHT:
                // not implemented yet
                break;
        }

    }

    private void moveLeft(int playerNumber) {

        charMap.get(playerNumber).translate( -CELL_SIZE, 0);
    }

    private void moveRight(int playerNumber) {

        charMap.get(playerNumber).translate( CELL_SIZE, 0);

    }

    protected void moveCharacters(int player1X, int player1Y, int player2X, int player2Y){

    }

    protected void loadBackground(String path){

        background = new Picture(PADDING, PADDING, path);

    }


    protected void loadCharacters(int p1initialX, int p1initialY, int p2initialX, int p2initialY){

        charMap.put(1, new Picture(p1initialX, p1initialY, "path to picture 1"));
        charMap.put(2, new Picture(p2initialX, p2initialY, "path to picture 2"));
    }
}



