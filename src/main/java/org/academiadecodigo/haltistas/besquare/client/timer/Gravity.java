package org.academiadecodigo.haltistas.besquare.client.timer;

import org.academiadecodigo.haltistas.besquare.client.CharacterSprite;
import org.academiadecodigo.haltistas.besquare.client.GameField;
import org.academiadecodigo.haltistas.besquare.client.event.Event;

import java.util.Arrays;
import java.util.TimerTask;

public class Gravity extends TimerTask implements Event {

    private int[] finalPositions;
    private GameField field;
    private CharacterSprite p1Sprite;
    private CharacterSprite p2Sprite;
    private int deltaRowP1;
    private int deltaRowP2;

    @Override
    public void run() {

        if (deltaRowP1 <= 0 && deltaRowP2 <= 0) {
            this.cancel();
            return;
        }

        movePlayer1();
        movePlayer2();
    }

    private void movePlayer2() {
        if (deltaRowP2 > 0) {
            field.moveCharacters(p1Sprite.getCol(), p1Sprite.getRow() , p2Sprite.getCol(),
                    p2Sprite.getRow() + 1);
            deltaRowP2--;
        }
    }

    private void movePlayer1() {
        if (deltaRowP1 > 0) {
            field.moveCharacters(p1Sprite.getCol(), p1Sprite.getRow() + 1
                    , p2Sprite.getCol(), p2Sprite.getRow());
            deltaRowP1--;
        }
    }

    @Override
    public void process(String[] data, GameField field) {

        processToInts(data);

        this.field = field;
    }

    private void processToInts(String[] data) {
        finalPositions = new int[data.length - 1];
        finalPositions[0] = parseInt(data[2]);
        finalPositions[1] = parseInt(data[3]);
        finalPositions[2] = parseInt(data[4]);
        finalPositions[3] = parseInt(data[5]);

        deltaRowP1 = finalPositions[1] - p1Sprite.getRow();
        deltaRowP2 = finalPositions[3] - p2Sprite.getRow();

    }

    private int parseInt(String datum) {
        return Integer.parseInt(datum);
    }

    public void setCharacterSprites(CharacterSprite p1Sprite, CharacterSprite p2Sprite) {
        this.p1Sprite = p1Sprite;
        this.p2Sprite = p2Sprite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gravity gravity = (Gravity) o;
        return Arrays.equals(finalPositions, gravity.finalPositions);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(finalPositions);
    }
}
