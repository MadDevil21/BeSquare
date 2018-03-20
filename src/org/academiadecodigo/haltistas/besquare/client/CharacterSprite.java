package org.academiadecodigo.haltistas.besquare.client;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class CharacterSprite {

    private int xPosition = 0;
    private int yPosition = 0;

    private Picture sprite;

    public CharacterSprite(String pictureSpritePath) {

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
        System.out.println("set position for sprite");
    }


    public void move(int finalX, int finalY) {

        int xTranslate = finalX - this.xPosition;
        int yTranslate = finalY - this.yPosition;

        System.out.println(this + " moving from " + this.xPosition + " to " + finalX);
        System.out.println(xTranslate + yTranslate);

        sprite.translate(xTranslate, yTranslate);

        this.xPosition += xTranslate;
        this.yPosition += yTranslate;

        System.out.println("New position " + this.getSprite().getX() + " " + this.getSprite().getY());

    }

    public Picture getSprite() {
        return sprite;
    }
}
