package org.academiadecodigo.haltistas.besquare.client;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Controller implements KeyboardHandler {

    private Client client;

    public Controller(Client client) {
        this.client = client;
    }

    public void init(){

        Keyboard keyboard = new Keyboard(this);

        int[] keys = new int[] {
                KeyboardEvent.KEY_RIGHT,
                KeyboardEvent.KEY_LEFT
        };




        for (int key: keys){
            KeyboardEvent event = new KeyboardEvent();
            event.setKey(key);
            event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(event);

        }
    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT){
            client.moveRight();
            System.out.println("key pressed right");
            return;
        }

        if(keyboardEvent.getKey() == KeyboardEvent.KEY_LEFT){
            client.moveLeft();
            System.out.println("key pressed left");
            return;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
