package org.academiadecodigo.haltistas.besquare.client.timer;

import org.academiadecodigo.haltistas.besquare.client.CharacterSprite;
import org.academiadecodigo.haltistas.besquare.client.GameField;
import org.academiadecodigo.haltistas.besquare.client.event.Event;

import java.util.Timer;
import java.util.TimerTask;

public class Gravity extends TimerTask implements Event {

    private static final long FRAME_RATE = 60;
    private int[] finalPositions;
    private Timer timer;
    private GameField field;
    private CharacterSprite p1Sprite;
    private CharacterSprite p2Sprite;
    private int deltaRowP1;
    private int deltaRowP2;

    public Gravity(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void run() {

        System.out.println(deltaRowP1 + " " + deltaRowP2);
        if (deltaRowP1 <= 0 && deltaRowP2 <= 0) {
            System.out.println("canceling");
            this.cancel();
            return;
        }

        movePlayer1(finalPositions[0], finalPositions[1]);
        movePlayer2(finalPositions[2], finalPositions[3]);
    }

    private void movePlayer2(int col, int row) {
        if (!p2Sprite.isInPosition(col, row)) {
            field.moveCharacters(p1Sprite.getCol(), p1Sprite.getRow() , p2Sprite.getCol(),
                    p2Sprite.getRow() + 1);
            deltaRowP2--;
        }
    }

    private void movePlayer1(int x, int y) {
        if (!p1Sprite.isInPosition(x, y)) {
            field.moveCharacters(p1Sprite.getCol(), p1Sprite.getRow() + 1
                    , p2Sprite.getCol(), p2Sprite.getRow());
            deltaRowP1--;
        }
    }

    @Override
    public void process(String[] data, GameField field) {

        processToInts(data);

        this.field = field;
        timer.scheduleAtFixedRate(this, 0, FRAME_RATE);
    }

    private void processToInts(String[] data) {

        finalPositions = new int[data.length - 1];
        for (int i = 1; i < data.length; i++) {
            finalPositions[i - 1] = Integer.parseInt(data[i]);
        }

        deltaRowP1 = finalPositions[1] - p1Sprite.getRow();
        deltaRowP2 = finalPositions[3] - p2Sprite.getRow();

    }

    public void setCharacterSprites(CharacterSprite p1Sprite, CharacterSprite p2Sprite) {
        this.p1Sprite = p1Sprite;
        this.p2Sprite = p2Sprite;
    }
}
