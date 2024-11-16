package com.w1959883.util;

import com.w1959883.models.Ticket;
import org.slf4j.Logger;

import java.util.concurrent.BlockingQueue;

public class Vendor implements Runnable {
    private static final Logger logger = TicketingLogger.getLogger();
    private final BlockingQueue<Ticket> ticketPool;

    public Vendor( BlockingQueue<Ticket> ticketPool ) {
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                Ticket item = new Ticket();
                ticketPool.put( item );
                logger.info( "\"Produced: \" + item" );
                Thread.sleep(500); // Simulate production time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error( "Producer interrupted" );
        }
    }
}
