package org.academiadecodigo.haltistas.besquare.server;

import org.academiadecodigo.haltistas.besquare.server.logic.Game;

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

    private final List<PlayerHandler> players;
    private ExecutorService executor;
    private Game game;

    public static void main(String[] args) {

        new Server().init();

    }

    public Server() {
        players = new ArrayList<>(NUM_PLAYERS);
    }

    public void init() {
        this.game = new Game();
        executor = Executors.newFixedThreadPool(NUM_PLAYERS);
        start();
    }

    private void start() {

        ServerSocket server;

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

    protected void broadcast(int idPlayer, String mapUpdate) {

        synchronized (players) {

            for (PlayerHandler player : players) {

                player.send(idPlayer + ": " + mapUpdate);
            }
        }
    }

    protected void process(int idPlayer, String fromClient) {
        String toClient = game.process(fromClient);
        broadcast(idPlayer, toClient);

    }

    // method to accept the 2 players into the server
    private void addPlayer(Socket socket) {

        synchronized (players) {

            System.out.println("Player accepted: " + socket);
            PlayerHandler player = new PlayerHandler(this, socket);

            players.add(player);
            executor.submit(player);
        }
    }
}
