package org.academiadecodigo.haltistas.besquare.client;



import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {


    private Controller controller;
    private Socket socket;
    private PrintWriter outStream;

    public Client(Socket socket) {
        controller = new Controller(this);
        this.socket = socket;
        initOutStream();
    }

    private void sendAction() {

        new Thread(new Receiver()).start();

        initOutStream();

        System.out.println("Connected to chat, start typing.");

        while (!socket.isClosed()) {

            String fromKeyboard = readString("");

            outStream.println(fromKeyboard);
        }


    }

    public void moveRight() {
        sendAction(); // send 1
    }

    public void moveLeft() {
        sendAction(); // send -1
    }

    private void initOutStream() {

        try {
            outStream = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
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

    private String readString(String question) {
        Scanner scanner = new Scanner(System.in);

        String read = "";

        try {
            System.out.print(question);
            read = scanner.nextLine();

        } catch (NoSuchElementException e) {
            System.out.println("no input given");
            return readString(question);
        }

        if (read.equals("")) {
            return "localhost";
        }

        return read;
    }


    private class Receiver implements Runnable {

        private BufferedReader inStream;

        @Override
        public void run() {

            initInStream();

            while (!socket.isClosed()) {

                String fromServer = receive();

                if (fromServer != null) {

                    System.out.println(fromServer);
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
