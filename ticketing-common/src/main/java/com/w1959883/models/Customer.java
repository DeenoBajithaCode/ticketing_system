package com.w1959883.models;

import com.w1959883.util.TicketPool;
import com.w1959883.util.TicketingLogger;
import org.apache.logging.log4j.Logger;

public class Customer implements Runnable {
    private static final Logger logger = TicketingLogger.getLogger();
    private final TicketPool ticketPool;
    private final Integer customerId;
    private final Long sellingTime;

    public Customer( TicketPool ticketPool, Integer customerId, Long sellingTime ) {
        this.ticketPool = ticketPool;
        this.customerId = customerId;
        this.sellingTime = sellingTime;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Ticket ticket =  ticketPool.purchaseTicket();
                ticket.setCustomerId( customerId );
                logger.info(  customerId + " took " + ticket.getTicketId() );
                //simulates selling time
                Thread.sleep(sellingTime);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error( "Customer : " + customerId + "got interrupted" );
        }
    }
}
