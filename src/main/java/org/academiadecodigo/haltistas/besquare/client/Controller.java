package org.academiadecodigo.haltistas.besquare.client;

import org.academiadecodigo.haltistas.besquare.GameState;
import org.academiadecodigo.haltistas.besquare.Initializer;
import org.academiadecodigo.haltistas.besquare.menu.ConnectMenu;
import org.academiadecodigo.haltistas.besquare.menu.MainMenu;
import org.academiadecodigo.haltistas.besquare.util.Message;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Controller implements KeyboardHandler {

    private Client client;
    private MainMenu mainMenu;
    private ConnectMenu connectMenu;
    private boolean checkIP;

    public Controller(MainMenu mainMenu) {

        this.mainMenu = mainMenu;
        checkIP = false;
    }

    public void init() {

        Keyboard keyboard = new Keyboard(this);

        int[] keys = new int[]{

                KeyboardEvent.KEY_K, KeyboardEvent.KEY_J, KeyboardEvent.KEY_H,
                KeyboardEvent.KEY_L, KeyboardEvent.KEY_R, KeyboardEvent.KEY_DOWN, KeyboardEvent.KEY_UP,
                KeyboardEvent.KEY_ENTER, KeyboardEvent.KEY_BACKSPACE, KeyboardEvent.KEY_0, KeyboardEvent.KEY_1,
                KeyboardEvent.KEY_2, KeyboardEvent.KEY_3, KeyboardEvent.KEY_4, KeyboardEvent.KEY_5,
                KeyboardEvent.KEY_6, KeyboardEvent.KEY_7, KeyboardEvent.KEY_8, KeyboardEvent.KEY_9
        };

        for (int key : keys) {

            KeyboardEvent event = new KeyboardEvent();
            event.setKey(key);
            event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(event);
        }
    }

    @Override
    public void keyPressed(char c) {

        if(!checkIP) {

            connectMenu.insertIP(c);
            return;
        }

        connectMenu.insertPort(c);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (Initializer.gameState) {

            case MAIN_MENU:

                mainMenuController(keyboardEvent);
                break;

            case CONNECT_MENU:

                connectMenuController();
                break;

            case GAME:

                playerController(keyboardEvent);
                break;
        }
    }

    private void mainMenuController(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_UP:
                mainMenu.moveUp();
                break;

            case KeyboardEvent.KEY_DOWN:
                mainMenu.moveDown();
                break;

            default:
                System.err.println(Message.ERR_CONTROLLER);
        }
    }

    private void connectMenuController() {

        switch (connectMenu.getMovePointer()) {

            case 0:

                connectMenu.hostServer();
                break;

            case 1:

                connectMenu.hostGame();
                Initializer.gameState = GameState.GAME;
                break;

            case 2:

                connectMenu.joinGame();
                Initializer.gameState = GameState.GAME;
                break;
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

        if (Initializer.gameState == GameState.MAIN_MENU) {

            mainMenu.actionSelection();
            return;
        }

        if (Initializer.gameState == GameState.CONNECT_MENU) {

            if (!checkIP) {
                checkIP = true;
            }

            connectMenuController();
        }
    }

    public void setConnectMenu(ConnectMenu connectMenu) {
        this.connectMenu = connectMenu;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
