package com.w1959883.models;

import com.w1959883.util.TicketingLogger;
import org.slf4j.Logger;

import java.util.concurrent.BlockingQueue;

public class Vendor implements Runnable {
    private static final Logger logger = TicketingLogger.getLogger();
    private final BlockingQueue<Ticket> ticketPool;
    private final Integer vendorId;

    public Vendor( BlockingQueue<Ticket> ticketPool, Integer vendorId ) {
        this.ticketPool = ticketPool;
        this.vendorId = vendorId;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                Ticket ticket = new Ticket();
                ticket.setVendorId( vendorId );
                ticketPool.put( ticket );
                logger.info( vendorId + " produced " + i );
                Thread.sleep(500); // Simulate production time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error( "Producer interrupted" );
        }
    }
}
