package org.academiadecodigo.haltistas.besquare.menu;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.awt.event.KeyEvent;

public class MenuInput implements KeyboardHandler {


    private MenuController menuController;
    private boolean setPort;
    private boolean isFinished;

    public MenuInput () {

        menuController = new MenuController();
        Keyboard keyboard = new Keyboard(this);
        addEventListeners(keyboard, new int[]{KeyboardEvent.KEY_0, KeyboardEvent.KEY_1, KeyboardEvent.KEY_2,
                KeyboardEvent.KEY_3, KeyboardEvent.KEY_4, KeyboardEvent.KEY_5, KeyboardEvent.KEY_6,
                KeyboardEvent.KEY_7, KeyboardEvent.KEY_8, KeyboardEvent.KEY_9, KeyboardEvent.KEY_DOWN, KeyEvent.VK_BACK_SPACE, KeyEvent.VK_ENTER});
    }


    private void addEventListeners(Keyboard keyboard, int[] keys) {
        for (int key : keys) {

            KeyboardEvent pressed = new KeyboardEvent();
            pressed.setKey(key);
            pressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(pressed);


        }
    }

    @Override
    public void keyPressed(KeyboardEvent kEvent) {
        if (kEvent.getKey() >= 48 && kEvent.getKey() <= 57) {
            if(setPort){

                menuController.addDigitToPort(kEvent.getKey() - 48);

            } else{

                menuController.addDigitToIP(kEvent.getKey() - 48);

            }

        } else {

            switch (kEvent.getKey()) {

                case KeyEvent.VK_BACK_SPACE: {
                    if(setPort){

                        menuController.removeDigitToPort();

                    } else{

                        menuController.removeDigitToIP();

                    }
                    break;
                }

                case KeyEvent.VK_ENTER: {
                    isFinished = menuController.isValidPort() && menuController.isValidIP();
                    break;
                }

            }

            if(kEvent.getKey() == KeyEvent.VK_ENTER || kEvent.getKey() ==KeyboardEvent.KEY_DOWN){
                setPort = !setPort;
            }

        }

        //menuController.showIp();
        menuController.getPort();

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public boolean isFinished() {
        return isFinished;
    }

    public boolean isSetPort() {
        return setPort;
    }

    public MenuController getMenuController() {
        return menuController;
    }

}
