package org.academiadecodigo.haltistas.besquare.client;


import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {


    private Controller controller;
    private Socket socket;
    private PrintWriter toServer;
    private GameField gameField;


    public Client(Socket socket) {

        controller = new Controller(this);
        gameField = new GameField(); // 
        this.socket = socket;
        startConnections();       // TODO: temporary solution
    }


    private void startConnections() {

        initOutStream();
        Receiver receiver = new Receiver();
        Thread fromServer = new Thread(receiver);
        fromServer.start();

    }


    private void sendAction(int action) {

        if (!socket.isClosed()) {
            toServer.println(action);
        }
    }


    public void moveRight() {
        final int actionValue = 1;
        sendAction(actionValue);           // TODO: temporary solution

    }


    public void moveLeft() {
        final int actionValue = -1;
        sendAction(actionValue);         // TODO: temporary solution
    }


    private void initOutStream() {

        try {
            toServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);


        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("unable to create output stream");
        }
    }


    private void close(Closeable clientSocket) {

        if (clientSocket != null) {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    private class Receiver implements Runnable {

        private BufferedReader inStream;

        @Override
        public void run() {

            initInStream();

            while (!socket.isClosed()) {

                String fromServer = receive();

                if (fromServer != null) {

                     InputTranslator.translate(fromServer, gameField);
                }
            }
        }


        private String receive() {

            try {
                return inStream.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


        private void initInStream() {
            inStream = null;

            try {
                inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
