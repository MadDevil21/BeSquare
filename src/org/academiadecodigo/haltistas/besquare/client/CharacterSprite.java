package org.academiadecodigo.haltistas.besquare.client;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class CharacterSprite {

    private int xPosition;
    private int yPosition;

    private Picture sprite;

    public CharacterSprite(String pictureSpritePath) {
        int initialX = -100;
        int initialY = 100;
        this.xPosition = initialX;
        this.yPosition = initialY;
        this.sprite = new Picture(xPosition, yPosition, pictureSpritePath);
    }

    public void setPosition(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        int xTranslation = ((sprite.getX() - xPosition) + GameField.PADDING);
        int yTranslation = ((sprite.getY() - yPosition) + GameField.PADDING);
        sprite.translate(xTranslation, yTranslation);
    }


    public Picture getSprite() {
        return sprite;
    }
}
