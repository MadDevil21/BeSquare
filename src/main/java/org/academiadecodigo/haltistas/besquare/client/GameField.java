package org.academiadecodigo.haltistas.besquare.client;

import org.academiadecodigo.haltistas.besquare.FilePath;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.HashMap;
import java.util.Map;

public class GameField {

    public static final int PADDING = 10;
    public static final int CELL_SIZE = 30;

    private Picture background;
    private CharacterSprite p1Sprite;
    private CharacterSprite p2Sprite;
    private Map<Integer, Picture> tokenSprites;

    public GameField(){
        tokenSprites = new HashMap<>();

    }

    /**
     * Method to calculate the final X and Y positions for players, which come in Col and Row format and need
     * to be in coordinate format when the move method in the sprites
     * @see CharacterSprite#move(int, int)
     *
     * @param player1col Player 1's destination column
     * @param player1row Player 1's destination row
     * @param player2col Player 2's destination column
     * @param player2row Player 2's destination row
     */

    protected void moveCharacters(int player1col, int player1row, int player2col, int player2row) { // TODO: temporary solution

        int player1X = logicToCoord(player1col);
        int player1Y = logicToCoord(player1row);
        int player2X = logicToCoord(player2col);
        int player2Y = logicToCoord(player2row);

        p1Sprite.move(player1X, player1Y);
        p2Sprite.move(player2X, player2Y);
    }

    /**
     *  Loads the background picture
     * @param path Path to the background image in the resources folder
     */

    protected void loadBackground(String path) {

        if (background != null) {
            background.delete();
        }

        background = new Picture(PADDING, PADDING, path);
        background.draw();
    }

    /**
     * Loads the character sprites to the initial positions when a new level starts
     * Values come from server in columns and rows and need to be converted to coordinates format
     * @see #logicToCoord(int)
     *
     * @param player1col Player 1's column value
     * @param player1row Player 1's row value
     * @param player2col Player 2's column value
     * @param player2row Player 2's row value
     */
    protected void loadCharacters(int player1col, int player1row, int player2col, int player2row) {

        p1Sprite = new CharacterSprite(FilePath.PLAYER1);
        p2Sprite = new CharacterSprite(FilePath.PLAYER2);

        p1Sprite.getSprite().delete();
        p2Sprite.getSprite().delete();

        int player1X = logicToCoord(player1col);
        int player1Y = logicToCoord(player1row);
        int player2X = logicToCoord(player2col);
        int player2Y = logicToCoord(player2row);

        p1Sprite.setPosition(player1X, player1Y);
        p2Sprite.setPosition(player2X, player2Y);

        p1Sprite.getSprite().draw();
        p2Sprite.getSprite().draw();
    }

    protected void createTokenSprite(int col, int row){

        System.out.println("trying to create at col " + col);
        int tokenX = logicToCoord(col);
        System.out.println("creating token at x: " + tokenX);
        System.out.println("trying to create at col " + row);
        int tokenY = logicToCoord(row);
        System.out.println("creating token at y: " + tokenY);

        Picture tokenSprite = new Picture(tokenX, tokenY, FilePath.TOKEN);
        int index = tokenSprites.size();

        tokenSprite.draw();
        tokenSprites.put(index, tokenSprite);
    }

    protected void killTokenSprite(int index){

        tokenSprites.get(index).delete();
        tokenSprites.remove(index);

    }


    private int logicToCoord(int number){

        return number * CELL_SIZE;
    }
}



