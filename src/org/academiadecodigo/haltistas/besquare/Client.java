package org.academiadecodigo.haltistas.besquare;

public class Client implements Runnable {

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
