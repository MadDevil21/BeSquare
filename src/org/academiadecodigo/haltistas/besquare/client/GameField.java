package org.academiadecodigo.haltistas.besquare.client;

import org.academiadecodigo.haltistas.besquare.FilePath;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.Map;

public class GameField {

    static final int PADDING = 10;
    static final int CELL_SIZE = 30;

    private Picture background;
    private CharacterSprite p1Sprite;
    private CharacterSprite p2Sprite;


    GameField() {
        p1Sprite = new CharacterSprite(FilePath.PLAYER1);
        p2Sprite = new CharacterSprite(FilePath.PLAYER2);
    }


    protected void moveCharacters(int player1X, int player1Y, int player2X, int player2Y) { // TODO: resolv temporary solution

        int xTranslation = ((p1Sprite.getSprite().getX() - player1X) + GameField.PADDING);
        int yTranslation = ((p1Sprite.getSprite().getY() - player1Y) + GameField.PADDING);

        p1Sprite.getSprite().translate(xTranslation, yTranslation);

        xTranslation = ((p2Sprite.getSprite().getX() - player2X) + GameField.PADDING);
        yTranslation = ((p2Sprite.getSprite().getY() - player2Y) + GameField.PADDING);

        p2Sprite.getSprite().translate(xTranslation, yTranslation);
    }

    protected void loadBackground(String path) {

        if (background != null) {
            background.delete();
        }

        background = new Picture(PADDING, PADDING, path);
        background.draw();
    }


    protected void loadCharacters(int player1X, int player1Y, int player2X, int player2Y) {

        p1Sprite.getSprite().delete();
        p2Sprite.getSprite().delete();

        p1Sprite.setPosition(player1X, player1Y);
        p2Sprite.setPosition(player2X, player2Y);

        p1Sprite.getSprite().draw();
        p2Sprite.getSprite().draw();

        System.out.println("loaded characters");
    }
}



