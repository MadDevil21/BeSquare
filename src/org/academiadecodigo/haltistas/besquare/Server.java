package org.academiadecodigo.haltistas.besquare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private Socket client1Socket;
    private Socket client2Socket;
    private boolean flag;
    private final Integer lock = 0;

    public static void main(String[] args) {

        new Server().start();
    }

    private void start() {

        int portNumber = 20022;
        System.out.println("Port bounded: " + portNumber);

        try {

            ServerSocket serverSocket = new ServerSocket(portNumber);

            while (true) {

                System.out.println("Waiting for client connections!!");
                Socket clientSocket = serverSocket.accept();

                if (!flag) {

                    flag = true;
                    client1Socket = clientSocket;
                    System.out.println("Connected to client1: " + client1Socket);
                    continue;
                }

                client2Socket = clientSocket;
                System.out.println("Connected to client2: " + client2Socket);

                synchronized (lock) {

                    System.out.println(Thread.currentThread().getName() + " have the lock");

                    try {
                        lock.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    talk();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void talk() {


    }

}
