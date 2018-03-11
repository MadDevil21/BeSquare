package org.academiadecodigo.haltistas.besquare.client;

import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable {

    private Controller controller;
    private GraphicalGrid graphicalGrid;

    private final Integer lock;

    private Socket socket;
    private String hostname = "localhost";
    private int port = 8080;

    public Client(int lock, Socket socket) throws IOException {

        this.lock = lock;
        socket = new Socket(hostname, port);
    }

    @Override
    public void run() {

        synchronized (lock) {

            System.out.println("Entrei");

            while (true) {
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
