package org.academiadecodigo.haltistas.besquare.server;

import org.academiadecodigo.haltistas.besquare.util.Message;

import java.io.*;
import java.net.Socket;

public class PlayerHandler implements Runnable {

    // proprieties
    private Server server;
    private Socket socket;
    private int playerId;
    private BufferedReader fromPlayer;
    private PrintWriter toPlayer;

    // constructor with server and player socket, socketPort to differ the 2 players
    public PlayerHandler(Server server, Socket socket, int playerId) {

        this.server = server;
        this.socket = socket;
        this.playerId = playerId;
    }

    public void send(String mapUpdate) {
        toPlayer.println(mapUpdate);
    }

    public void run() {

        try {

            openStream();

            while (true) {

                String message = fromPlayer.readLine();

                // if one player lost connection spam null, block to prevent that
                if (message == null) {

                    System.out.println(playerId + Message.ERR_CONNECTION);
                    socket.close();
                    break;
                }

                server.process(this.playerId, message);
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