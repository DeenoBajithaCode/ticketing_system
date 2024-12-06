package com.w1959883.services;

import com.w1959883.models.Configuration;
import com.w1959883.models.Customer;
import com.w1959883.models.Ticket;
import com.w1959883.models.Vendor;
import com.w1959883.util.TicketCounter;
import com.w1959883.util.TicketingLogger;
import org.slf4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProcessManager
{
    private static final Logger logger = TicketingLogger.getLogger();
    private static final ProcessManager instance = new ProcessManager();
    private TicketCounter ticketCounter;
    private Long productionTime;
    private Long sellingTime;
    //Ticket pool
    private BlockingQueue<Ticket> ticketsPool = null;
    private Thread vendorThreadOne;
    private Thread vendorThreadTwo;
    private Thread vendorThreadThree;
    private Thread vendorThreadFour;
    private Thread vendorThreadFive;

    private Thread customerThreadOne;
    private Thread customerThreadTwo;
    private Thread customerThreadThree;
    private Thread customerThreadFour;
    private Thread customerThreadFive;
    private volatile boolean running = false;

    private ProcessManager() {
        // Private constructor for singleton pattern
    }

    public static ProcessManager getInstance() {
        return instance;
    }

    public synchronized void start( Configuration configuration ) {
        if (running) {
            logger.info( "Process is already running." );
            return;
        }
        setupProcess( configuration );

        running = true;
        //Vendor Threads
        vendorThreadOne = new Thread(new Vendor( ticketsPool, 1, ticketCounter, productionTime ));
        vendorThreadTwo = new Thread(new Vendor( ticketsPool, 2, ticketCounter, productionTime ));
        vendorThreadThree = new Thread(new Vendor( ticketsPool, 3, ticketCounter, productionTime ));
        vendorThreadFour = new Thread(new Vendor( ticketsPool, 4, ticketCounter, productionTime ));
        vendorThreadFive = new Thread(new Vendor( ticketsPool, 5, ticketCounter, productionTime ));

        //Customer Threads
        customerThreadOne = new Thread(new Customer( ticketsPool, 1, sellingTime ));
        customerThreadTwo = new Thread(new Customer( ticketsPool, 2, sellingTime ));
        customerThreadThree = new Thread(new Customer( ticketsPool, 3, sellingTime ));
        customerThreadFour = new Thread(new Customer( ticketsPool, 4, sellingTime ));
        customerThreadFive = new Thread(new Customer( ticketsPool, 5, sellingTime ));

        //Selling Start
        vendorThreadOne.start();
        vendorThreadTwo.start();
        vendorThreadThree.start();
        vendorThreadFour.start();
        vendorThreadFive.start();

        //Buying Start
        customerThreadOne.start();
        customerThreadTwo.start();
        customerThreadThree.start();
        customerThreadFour.start();
        customerThreadFive.start();

        logger.info( "Process has Started." );
    }

    private void setupProcess( Configuration configuration )
    {
        assert configuration != null;
        ticketsPool = new ArrayBlockingQueue<>(configuration.getMaximumTicketCapacity());
        ticketCounter = new TicketCounter(configuration.getTotalNumberOfTickets());
        productionTime = calculateProductionTime(configuration.getTicketReleaseRate());
        sellingTime = calculateSellingTime(configuration.getCustomerRetrievalRate());
    }

    private Long calculateSellingTime( Double customerRetrievalRate )
    {
        return ( long ) ((1000*customerRetrievalRate)/5);
    }

    private Long calculateProductionTime( Double ticketReleaseRate )
    {
        return ( long ) ((1000*ticketReleaseRate)/5);
    }

    public synchronized void stop() {
        if (!running) {
            logger.info( "Process is not running." );
            return;
        }

        // Initialize TicketCounter with the maximum ticket limit
        ticketCounter = new TicketCounter(1000);

        running = false;
        vendorThreadOne.interrupt();
        customerThreadOne.interrupt();
        logger.info( "Process has stopped." );
    }
}

