package com.w1959883.models;

import com.w1959883.util.TicketingLogger;
import org.slf4j.Logger;

import java.util.concurrent.BlockingQueue;

public class Customer implements Runnable {
    private static final Logger logger = TicketingLogger.getLogger();
    private final BlockingQueue<Ticket> ticketPool;
    private final Integer customerId;

    public Customer( BlockingQueue<Ticket> ticketPool, Integer customerId ) {
        this.ticketPool = ticketPool;
        this.customerId = customerId;
    }

    @Override
    public void run() {
        try {
            while (true) {
                logger.info( "Pool Size: " + ticketPool.size() );
                Ticket ticket =  ticketPool.take();
                ticket.setCustomerId( customerId );
                logger.info(  customerId + " took " + ticket.getVendorId() );
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Consumer interrupted");
        }
    }
}
