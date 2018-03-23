package org.academiadecodigo.haltistas.besquare.client.timer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;

public class GravityAnimation implements Runnable {

    private static final long FRAME_RATE = 150;
    private Timer timer;
    private Queue<Gravity> gravities;

    public GravityAnimation() {
        this.gravities = new LinkedList<>();
        this.timer = new Timer();
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {

                while (gravities.isEmpty()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        // no handling
                    }
                }
                timer.scheduleAtFixedRate(gravities.poll(), 0, FRAME_RATE);
            }

        }
    }

    public void addGravity(Gravity gravity) {

        if (gravities.contains(gravity)) {
            System.out.println("returning");
            return;
        }

        synchronized (this) {
            if (!gravities.contains(gravity)) {
                gravities.offer(gravity);
                notifyAll();
            }
        }
    }
}

