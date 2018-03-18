package org.academiadecodigo.haltistas.besquare.client;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class CharacterSprite {

    private int xPosition;
    private int yPosition;

    private Picture sprite;

    public CharacterSprite(String pictureSpritePath) {
        int initialX = 100;
        int initialY = 100;
        this.xPosition = initialX;
        this.yPosition = initialY;
        this.sprite = new Picture(xPosition, yPosition, pictureSpritePath);
        sprite.draw();
        System.out.println("created sprite");
    }

    public void setPosition(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        int xTranslation = ((xPosition - sprite.getX()) + GameField.PADDING);
        int yTranslation = ((yPosition - sprite.getY()) + GameField.PADDING);
        sprite.translate(xTranslation, yTranslation);
        sprite.draw();
        System.out.println("set position for sprite");
    }



    public void move(int finalX, int finalY){

        int xTranslation = ((finalX - sprite.getX() ) + GameField.PADDING);
        int yTranslation = ((finalY - sprite.getY() ) + GameField.PADDING);
        sprite.translate(finalX,finalY);

        System.out.println("MOOVED");

    }

    public Picture getSprite() {
        return sprite;
    }
}
