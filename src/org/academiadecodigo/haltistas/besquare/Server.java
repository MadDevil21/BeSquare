package org.academiadecodigo.haltistas.besquare;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final static int PORT_NUMBER = 8080;

    private Socket playerOneSocket;
    private Socket playerTwoSocket;
    private boolean isConnected;

    public static void main(String[] args) {

        new Server().start();
    }

    private void start() {

        int numConnections = 0;

        System.out.println("Port bound: " + PORT_NUMBER);

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

            while (true) {

                talk(playerOneSocket, playerTwoSocket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void talk(Socket playerOne, Socket playerTwo) throws IOException {

        BufferedReader in1 = new BufferedReader(new InputStreamReader(playerOne.getInputStream()));
        PrintWriter out1 = new PrintWriter(playerOne.getOutputStream(), true);

        BufferedReader in2 = new BufferedReader(new InputStreamReader(playerTwo.getInputStream()));
        PrintWriter out2 = new PrintWriter(playerTwo.getOutputStream(), true);

        String received1 = in1.readLine();

        System.out.println("1: " + received1);
        received1 = received1.toUpperCase();

        out1.println(received1);

        String received2 = in2.readLine();

        System.out.println("2: " + received2);
        received2 = received2.toUpperCase();

        out2.println(received2);
    }
}
