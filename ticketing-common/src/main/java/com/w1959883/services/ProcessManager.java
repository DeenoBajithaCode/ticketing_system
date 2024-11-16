package com.w1959883.services;

import com.w1959883.models.Ticket;
import com.w1959883.util.Customer;
import com.w1959883.util.Vendor;
import com.w1959883.util.TicketingLogger;
import org.slf4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProcessManager
{
    private static final Logger logger = TicketingLogger.getLogger();
    private static final ProcessManager instance = new ProcessManager();

    private final BlockingQueue<Ticket> ticketsPool = new ArrayBlockingQueue<>(10);
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

    public synchronized void start() {
        if (running) {
            logger.info( "Process is already running." );
            return;
        }

        running = true;
        //Vendor Threads
        vendorThreadOne = new Thread(new Vendor( ticketsPool ));
        vendorThreadTwo = new Thread(new Vendor( ticketsPool ));
        vendorThreadThree = new Thread(new Vendor( ticketsPool ));
        vendorThreadFour = new Thread(new Vendor( ticketsPool ));
        vendorThreadFive = new Thread(new Vendor( ticketsPool ));

        //Customer Threads
        customerThreadOne = new Thread(new Customer( ticketsPool ));
        customerThreadTwo = new Thread(new Customer( ticketsPool ));
        customerThreadThree = new Thread(new Customer( ticketsPool ));

        //Selling Start
        vendorThreadOne.start();

        //Buying Start
        customerThreadOne.start();
        logger.info( "Process has Started." );
    }

    public synchronized void stop() {
        if (!running) {
            logger.info( "Process is not running." );
            return;
        }

        running = false;
        vendorThreadOne.interrupt();
        customerThreadOne.interrupt();
        logger.info( "Process has stopped." );
    }
}

