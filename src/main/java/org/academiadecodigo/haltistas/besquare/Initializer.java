package org.academiadecodigo.haltistas.besquare;

import org.academiadecodigo.haltistas.besquare.client.Client;
import org.academiadecodigo.haltistas.besquare.client.Controller;
import org.academiadecodigo.haltistas.besquare.menu.ConnectMenu;
import org.academiadecodigo.haltistas.besquare.menu.MainMenu;
import org.academiadecodigo.haltistas.besquare.server.Server;


import java.io.IOException;
import java.net.Socket;

public class Initializer {

    public static GameState gameState;

    private Client client;
    private Controller controller;

    public static void main(String[] args) {

        new Initializer().init();
    }

    public void init() {

        gameState = GameState.MAIN_MENU;

        ConnectMenu connectMenu = new ConnectMenu();
        MainMenu mainMenu = new MainMenu();
        controller = new Controller(mainMenu);

        controller.setConnectMenu(connectMenu);
        mainMenu.setConnectMenu(connectMenu);
        connectMenu.setInitializer(this);
        controller.init();
        mainMenu.init();
    }

    public void hostServer(int port) {

        try {
            new Server().init(port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void joinGame(String ip, int port) {

        try {
            client = new Client(new Socket(ip, port));

            controller.setClient(client);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
