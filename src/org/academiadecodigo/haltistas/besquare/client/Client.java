package org.academiadecodigo.haltistas.besquare.client;

public class Client implements Runnable {

    private Controller controller;
    private GraphicalGrid graphicalGrid;

    private final Integer lock;

    public Client(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {

        synchronized (lock) {

            System.out.println("Entrei");

            while (true) {
            }
        }
    }
}
