package org.academiadecodigo.haltistas.besquare;

import java.io.*;
import java.net.Socket;

public class PlayerHandler implements Runnable {

    // proprieties
    private Server server;
    private Socket socket;
    private int idPlayer;

    private BufferedReader fromPlayer;
    private PrintWriter toPlayer;

    // constructor with server and player socket, socketPort to differ the 2 players
    public PlayerHandler(Server server, Socket socket) {

        this.server = server;
        this.socket = socket;

        idPlayer = socket.getPort();
    }

    public void send(String mapUpdate) {

        toPlayer.println(mapUpdate);
    }

    public void run() {

        try {

            openStream();
            System.out.println("Server Thread " + idPlayer + " running.");

            while (true) {

                server.handle(idPlayer, fromPlayer.readLine());
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void openStream() throws IOException {

        fromPlayer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toPlayer = new PrintWriter(socket.getOutputStream(), true);
    }
}