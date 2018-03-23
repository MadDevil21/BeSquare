package org.academiadecodigo.haltistas.besquare.menu;

import org.academiadecodigo.haltistas.besquare.GameState;
import org.academiadecodigo.haltistas.besquare.Initializer;

public class ConnectMenu extends AbstractMenu {

    public void init() {

        Initializer.gameState = GameState.CONNECT_MENU;
        createBackground();
        createConnectOptions();
    }

    private int requestIP() {

        createOption(200, "IP: ");
        return 0;
    }

    private int requestPort() {

        createOption(250, "PORT: ");
        return 20021;
    }

    public void createConnectOptions() {

        requestIP();
        requestPort();
    }

}
