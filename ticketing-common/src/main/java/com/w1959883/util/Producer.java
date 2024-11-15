package com.w1959883.util;

import com.w1959883.models.Ticket;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private final BlockingQueue<Ticket> queue;

    public Producer(BlockingQueue<Ticket> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                Ticket item = new Ticket();
                queue.put( item );
                System.out.println("Produced: " + item);
                Thread.sleep(500); // Simulate production time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Producer interrupted");
        }
    }
}
