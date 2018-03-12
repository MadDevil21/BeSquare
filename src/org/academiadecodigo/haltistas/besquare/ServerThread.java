package org.academiadecodigo.haltistas.besquare;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {

    // proprieties
    private Server server;
    private Socket socket;
    private int idPlayer;

    private BufferedReader streamIn;
    private PrintWriter streamOut;

    // constructor with server and player socket, socketPort to differ the 2 players
    public ServerThread(Server server, Socket socket) {

        this.server = server;
        this.socket = socket;

        idPlayer = socket.getPort();
    }

    public void send(String mapUpdate) {

        streamOut.println(mapUpdate);
    }

    public void run() {

        System.out.println("Server Thread " + idPlayer + " running.");

        while (true) {

            try {
                server.handle(idPlayer, streamIn.readLine());

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void openStream() throws IOException {

        streamIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        streamOut = new PrintWriter(socket.getOutputStream(), true);
    }
}

