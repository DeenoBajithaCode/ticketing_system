package com.w1959883.models;

import com.w1959883.util.TicketCounter;
import com.w1959883.util.TicketingLogger;
import org.slf4j.Logger;

import java.util.concurrent.BlockingQueue;

public class Vendor implements Runnable {
    private static final Logger logger = TicketingLogger.getLogger();
    private final BlockingQueue<Ticket> ticketPool;
    private final Integer vendorId;
    private final TicketCounter ticketCounter;
    private final Long productionTime;

    public Vendor( BlockingQueue<Ticket> ticketPool, Integer vendorId, TicketCounter ticketCounter, Long productionTime ) {
        this.ticketPool = ticketPool;
        this.vendorId = vendorId;
        this.ticketCounter = ticketCounter;
        this.productionTime = productionTime;
    }

    @Override
    public void run() {
        try {
            while( true ){
                // Check if the ticket limit is reached
                if (ticketCounter.isLimitReached()) {
                    logger.info("Vendor " + vendorId + " has stopped producing. Ticket limit reached.");
                    break; // Exit the loop
                }

                // Produce a ticket
                int ticketNumber = ticketCounter.increment();
                if (ticketNumber == -1) {
                    // Limit reached, no need to produce
                    break;
                }

                Ticket ticket = new Ticket();
                ticket.setTicketId( ticketNumber );
                ticket.setVendorId( vendorId );
                ticketPool.put( ticket );
                logger.info( vendorId + " produced " + ticketNumber );
                // Simulate production time
                Thread.sleep(productionTime);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error( "Producer interrupted" );
        }
    }
}
