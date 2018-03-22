package org.academiadecodigo.haltistas.besquare.client;

import org.academiadecodigo.haltistas.besquare.util.Message;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Controller implements KeyboardHandler {

    private Client client;

    public Controller(Client client) {
        this.client = client;
    }

    public void init() {

        Keyboard keyboard = new Keyboard(this);

        int[] keys = new int[]{

                KeyboardEvent.KEY_K,
                KeyboardEvent.KEY_J,
                KeyboardEvent.KEY_H,
                KeyboardEvent.KEY_L,
                KeyboardEvent.KEY_R
        };

        for (int key : keys) {

            KeyboardEvent event = new KeyboardEvent();
            event.setKey(key);
            event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(event);
        }
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_K:
                client.moveRight();
                break;

            case KeyboardEvent.KEY_J:
                client.moveLeft();
                break;

            case KeyboardEvent.KEY_H:
                client.jumpLeft();
                break;

            case KeyboardEvent.KEY_L:
                client.jumpRight();
                break;

            case KeyboardEvent.KEY_R:
                client.resetLevel();
                break;

            default:
                System.err.println(Message.ERR_CONTROLLER);
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }
}
