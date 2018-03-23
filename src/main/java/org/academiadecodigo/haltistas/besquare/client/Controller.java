package org.academiadecodigo.haltistas.besquare.client;

import org.academiadecodigo.haltistas.besquare.GameState;
import org.academiadecodigo.haltistas.besquare.Initializer;
import org.academiadecodigo.haltistas.besquare.menu.Menu;
import org.academiadecodigo.haltistas.besquare.util.Message;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Controller implements KeyboardHandler {

    private Client client;
    private Menu menu;

    public Controller(Menu menu) {
        this.client = client;
        this.menu = menu;
    }

    public void init() {

        Keyboard keyboard = new Keyboard(this);

        int[] keys = new int[]{

                KeyboardEvent.KEY_K,
                KeyboardEvent.KEY_J,
                KeyboardEvent.KEY_H,
                KeyboardEvent.KEY_L,
                KeyboardEvent.KEY_R,
                KeyboardEvent.KEY_DOWN,
                KeyboardEvent.KEY_UP,
                KeyboardEvent.KEY_ENTER,
                KeyboardEvent.KEY_BACKSPACE
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

        switch (Initializer.gameState) {

            case MENU:
                menuController(keyboardEvent);
                break;

            case GAME:
                playerController(keyboardEvent);
                break;
        }
    }

    private void menuController(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_UP:
                menu.moveUp();
                break;

            case KeyboardEvent.KEY_DOWN:
                menu.moveDown();
                break;

            default:
                System.err.println(Message.ERR_CONTROLLER);
        }
    }

    private void playerController(KeyboardEvent keyboardEvent) {

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

            default:
                System.err.println(Message.ERR_CONTROLLER);
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }

    @Override
    public void keyBackspace() {

    }

    @Override
    public void keyEnter() {

        if (Initializer.gameState != GameState.MENU) {
            return;
        }

        menu.actionSelection();
    }
}
