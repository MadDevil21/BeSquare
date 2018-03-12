package org.academiadecodigo.haltistas.besquare;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    // constants and proprieties
    private final static int PORT_NUMBER = 20021;
    private final static int MAX_PLAYERS = 2;

    private ServerThread[] players = new ServerThread[MAX_PLAYERS];
    private int num_players;
    private Thread thread;
    private ServerSocket server;

    public static void main(String[] args) {

        new Server().start();
    }

    public void start() {

        try {
            server = new ServerSocket(PORT_NUMBER);
            System.out.println("Server started: " + server);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        thread = new Thread(this);
        thread.start();
    }

    // method to broadcast information received from one player to both
    public synchronized void handle(int idPlayer, String mapUpdate) {

        for (int i = 0; i < num_players; i++) {
            players[i].send(idPlayer + ": " + mapUpdate);
        }
    }

    // method to accept the 2 players into the server
    private void addPlayer(Socket socket) {

        if (num_players < players.length) {

            System.out.println("Player accepted: " + socket);
            players[num_players] = new ServerThread(this, socket);

            try {

                players[num_players].openStream();
                players[num_players].start();
                num_players++;

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void run() {

        while (thread != null) {

            try {

                System.out.println("Waiting for players to connect..");
                addPlayer(server.accept());

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
