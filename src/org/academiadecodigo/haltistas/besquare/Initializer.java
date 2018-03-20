package org.academiadecodigo.haltistas.besquare;

import org.academiadecodigo.haltistas.besquare.client.Client;
import org.academiadecodigo.haltistas.besquare.client.Controller;

import java.io.IOException;
import java.net.Socket;

public class Initializer {

    private Controller controller;
    private GameState gameState;
    private Menu menu;

    public static void main(String[] args) {


        try {
            new Client(new Socket("192.168.2.2", 20021));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
