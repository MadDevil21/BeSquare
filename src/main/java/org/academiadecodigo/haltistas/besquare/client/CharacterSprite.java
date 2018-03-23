package org.academiadecodigo.haltistas.besquare.client;

import org.academiadecodigo.haltistas.besquare.server.logic.Game;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class CharacterSprite {

    private int xPosition = 0;
    private int yPosition = 0;

    private Picture sprite;

    public CharacterSprite(String pictureSpritePath) {

        this.sprite = new Picture(xPosition, yPosition, pictureSpritePath);
        sprite.draw();

    }

    /**
     * Forces the sprite to the position, such as at the start of the level
     *
     * @param xPosition The X position in coordinates where the sprite should appear
     * @param yPosition The Y position in coordinates where the sprite should appear
     */

    public void setPosition(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;

        int xTranslation = ((xPosition - sprite.getX()) + GameField.PADDING);
        int yTranslation = ((yPosition - sprite.getY()) + GameField.PADDING);
        sprite.translate(xTranslation, yTranslation);

    }

    /**
     * Move a sprite to the final X and final Y parameters
     *
     * @param finalX The X position in coordinates where the sprite should translate to
     * @param finalY The Y position in coordinates where the sprite should translate to
     */

    public void move(int finalX, int finalY) {

        int xTranslate = finalX - this.xPosition;
        int yTranslate = finalY - this.yPosition;

        sprite.translate(xTranslate, yTranslate);

        this.xPosition += xTranslate;
        this.yPosition += yTranslate;

    }

    public Picture getSprite() {
        return sprite;
    }

    public boolean isInPosition(int col, int row) {
        return getCol() == col && getRow() == row;
    }

    public int getCol() {
        return sprite.getX() / GameField.CELL_SIZE;
    }

    public int getRow() {
        return sprite.getY() / GameField.CELL_SIZE;
    }
}
