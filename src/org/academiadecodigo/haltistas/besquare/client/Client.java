package org.academiadecodigo.haltistas.besquare.client;


import org.academiadecodigo.haltistas.besquare.util.Message;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socket;
    private PrintWriter toServer;
    private TaskManager taskManager;

    public Client(Socket socket) {

        this.socket = socket;

        GameField gameField = new GameField();
        Controller controller = new Controller(this);

        taskManager = new TaskManager(gameField);
        controller.init();
        startConnections();       // TODO: temporary solution
    }

    private void startConnections() {

        initOutStream();
        Receiver receiver = new Receiver();
        Thread fromServer = new Thread(receiver);
        fromServer.start();
    }

    private void sendAction(String action) {

        if (!socket.isClosed()) {
            toServer.println(action);
        }
    }

    public void moveRight() {

        String actionValue = Action.MOVE_RIGHT.getActionText();
        sendAction(actionValue);
    }

    public void moveLeft() {

        String actionValue = Action.MOVE_LEFT.getActionText();
        sendAction(actionValue);
    }

    private void initOutStream() {

        try {
            toServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(Message.ERR_OUTPUT_STREAM);
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

                    taskManager.interpret(fromServer);
                    continue;
                }

                close(socket);
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
