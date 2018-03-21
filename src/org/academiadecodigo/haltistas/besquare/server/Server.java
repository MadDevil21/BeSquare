package org.academiadecodigo.haltistas.besquare.server;

import org.academiadecodigo.haltistas.besquare.server.logic.Game;
import org.academiadecodigo.haltistas.besquare.util.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
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
        this.game = new Game(this);
        this.executor = Executors.newFixedThreadPool(NUM_PLAYERS);
    }

    public void init() throws IOException {
        start();
    }

    private void start() throws IOException {

        int connectedPlayers = 0;
        ServerSocket server;

        server = new ServerSocket(PORT_NUMBER);
        System.out.println(Message.SERVER_START + server);

        while (connectedPlayers < NUM_PLAYERS) {

            System.out.println(Message.WAITING_PLAYERS);
            Socket clientSocket = server.accept();

            connectedPlayers++;
            addPlayer(connectedPlayers, clientSocket);
        }

        this.game.init();
    }

    // method to broadcast information received from one player to both
    public void broadcast(String toClient) {

        synchronized (players) {

            for (Integer playerId : players.keySet()) {

                players.get(playerId).send(toClient);
            }

            System.out.println(Message.HALP_BROADCAST + toClient);
        }
    }

    protected void process(int playerId, String fromClient) {

        String toClient = game.process(playerId, fromClient);

        if (toClient != null) {
            broadcast(toClient);
        }

    }

    // method to accept the 2 players into the server
    private void addPlayer(int playerId, Socket socket) {

        synchronized (players) {

            System.out.println(Message.ACCEPT_PLAYER + socket);
            PlayerHandler player = new PlayerHandler(this, socket, playerId);

            players.put(playerId, player);
            executor.execute(player);
            // TODO replace with submit after troubleshooting
        }
    }
}