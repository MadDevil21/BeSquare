package org.academiadecodigo.haltistas.besquare;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    // constants and proprieties
    private final static int PORT_NUMBER = 20021;
    private final static int NUM_PLAYERS = 2;

    private ExecutorService executor;
    private List<ServerThread> players;
    private ServerSocket server;

    public static void main(String[] args) {

        new Server().start();
    }

    public void start() {

        executor = Executors.newFixedThreadPool(NUM_PLAYERS);
        players = new ArrayList<>(NUM_PLAYERS);

        try {

            server = new ServerSocket(PORT_NUMBER);
            System.out.println("Server started: " + server);

            while (players.size() < NUM_PLAYERS) {

                System.out.println("Waiting for players to connect..");
                addPlayer(server.accept());
            }

            executor.shutdown();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // method to broadcast information received from one player to both
    public synchronized void handle(int idPlayer, String mapUpdate) {

        for (ServerThread player : players) {

            player.send(idPlayer + ": " + mapUpdate);
        }
    }

    // method to accept the 2 players into the server
    private void addPlayer(Socket socket) {

        System.out.println("Player accepted: " + socket);
        ServerThread player = new ServerThread(this, socket);

        players.add(player);
        executor.submit(player);
    }
}
