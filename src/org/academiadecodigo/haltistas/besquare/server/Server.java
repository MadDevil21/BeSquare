package org.academiadecodigo.haltistas.besquare.server;

import org.academiadecodigo.haltistas.besquare.server.logic.LogicCenter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final static int PORT_NUMBER = 8080;

    private LogicCenter logicCenter;
    private Socket playerOneSocket;
    private Socket playerTwoSocket;
    private boolean isConnected;

    public static void main(String[] args) {

        new Server().start();
    }

    private void start() {

        int numConnections = 0;

        System.out.println("Port bounded: " + PORT_NUMBER);

        try {

            ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);

            while (numConnections < 2) {

                System.out.println("Waiting for player connection!!");
                Socket clientSocket = serverSocket.accept();

                numConnections++;

                if (!isConnected) {

                    isConnected = true;

                    playerOneSocket = clientSocket;
                    System.out.println("Connected to client1: " + playerOneSocket);

                    continue;
                }

                playerTwoSocket = clientSocket;
                System.out.println("Connected to client2: " + playerTwoSocket);
            }

            talk(playerOneSocket, playerTwoSocket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void talk(Socket playerOne, Socket playerTwo) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(playerOne.getInputStream()));
        PrintWriter out = new PrintWriter(playerOne.getOutputStream(), true);

        String received = in.readLine();

        System.out.println(received);
        received = received.toUpperCase();
        System.out.println(received);

        out.println(received);
    }
}
