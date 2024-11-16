package com.w1959883.util;

import com.w1959883.models.Ticket;
import org.slf4j.Logger;

import java.util.concurrent.BlockingQueue;

public class Customer implements Runnable {
    private static final Logger logger = TicketingLogger.getLogger();
    private final BlockingQueue<Ticket> ticketPool;

    public Customer( BlockingQueue<Ticket> ticketPool ) {
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ticketPool.take();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Consumer interrupted");
        }
    }
}
