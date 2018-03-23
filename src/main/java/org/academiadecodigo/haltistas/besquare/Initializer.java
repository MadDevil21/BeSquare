package org.academiadecodigo.haltistas.besquare;

import org.academiadecodigo.haltistas.besquare.client.Client;
import org.academiadecodigo.haltistas.besquare.client.Controller;
import org.academiadecodigo.haltistas.besquare.menu.ConnectMenu;
import org.academiadecodigo.haltistas.besquare.menu.MainMenu;


import java.io.IOException;
import java.net.Socket;

public class Initializer {

    private Controller controller;
    public static GameState gameState;
    private MainMenu mainMenu;
    private ConnectMenu connectMenu;

    public static void main(String[] args) {


        try {

            new Initializer().init();
            new Client(new Socket("localhost", 20021));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() {

        gameState = GameState.MAIN_MENU;
        connectMenu = new ConnectMenu();
        mainMenu = new MainMenu();
        controller = new Controller(mainMenu);

        mainMenu.setConnectMenu(connectMenu);
        mainMenu.init();
        controller.init();
    }
}
