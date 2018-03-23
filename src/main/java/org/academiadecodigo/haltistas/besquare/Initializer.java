package org.academiadecodigo.haltistas.besquare;

import org.academiadecodigo.haltistas.besquare.client.Client;
import org.academiadecodigo.haltistas.besquare.client.Controller;
import org.academiadecodigo.haltistas.besquare.menu.Menu;


import java.io.IOException;
import java.net.Socket;

public class Initializer {

    private Controller controller;
    public static GameState gameState;
    private Menu menu;

    public static void main(String[] args) {


        try {

            new Initializer().init();
            new Client(new Socket("localhost", 20021));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() {

        gameState = GameState.MENU;
        menu = new Menu();
        controller = new Controller(menu);
        menu.init();
        controller.init();
    }
}
