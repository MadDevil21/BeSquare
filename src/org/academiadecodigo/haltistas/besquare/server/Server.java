package org.academiadecodigo.haltistas.besquare.server;

import org.academiadecodigo.haltistas.besquare.server.logic.Game;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    // constants and proprieties
    private final static int PORT_NUMBER = 20021;
    private final static int NUM_PLAYERS = 2;

    private final Map<Integer, PlayerHandler> players;
    private ExecutorService executor;
    private Game game;

    public static void main(String[] args) {

        try {
            new Server().init();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());

        }

    }

    public Server() {
        this.players = new HashMap<>();
        this.game = new Game();
        this.executor = Executors.newFixedThreadPool(NUM_PLAYERS);

    }

    public void init() throws IOException {
        this.game.init();
        start();
    }

    private void start() throws IOException {
        int connectedPlayers = 0;
        ServerSocket server;

        server = new ServerSocket(PORT_NUMBER);
        System.out.println("Server started: " + server);

        while (connectedPlayers < NUM_PLAYERS) {

            System.out.println("Waiting for players to connect..");
            Socket clientSocket = server.accept();

            connectedPlayers++;

            addPlayer(connectedPlayers, clientSocket);

        }

        broadcast("NEW_LEVEL LEVEL_1 50 100 150 300");

    }

    // method to broadcast information received from one player to both

    protected void broadcast(String toClient) {

        synchronized (players) {

            for (Integer playerId : players.keySet()) {
                System.out.println("About to send to client " + playerId);
                players.get(playerId).send(toClient);
                System.out.println("SERVER to client: " + toClient);

            }
        }
    }

    protected void process(int playerId, String fromClient) {
        String toClient = game.process(playerId, fromClient);
        broadcast(toClient);

    }

    // method to accept the 2 players into the server
    private void addPlayer(int playerId, Socket socket) {

        synchronized (players) {

            System.out.println("Player accepted: " + socket);
            PlayerHandler player = new PlayerHandler(this, socket, playerId);

            players.put(playerId, player);
            executor.execute(player);

                // TODO replace with submit after troubleshooting
        }
    }
}
