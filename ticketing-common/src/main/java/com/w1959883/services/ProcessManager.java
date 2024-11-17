package com.w1959883.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.w1959883.models.Configuration;
import com.w1959883.models.Ticket;
import com.w1959883.models.Customer;
import com.w1959883.models.Vendor;
import com.w1959883.util.TicketingLogger;
import org.slf4j.Logger;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProcessManager
{
    private static final Logger logger = TicketingLogger.getLogger();
    private static final ProcessManager instance = new ProcessManager();
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

    public synchronized void start() {
        setupProcess();
        if (running) {
            logger.info( "Process is already running." );
            return;
        }

        running = true;
        //Vendor Threads
        vendorThreadOne = new Thread(new Vendor( ticketsPool, 1 ));
        vendorThreadTwo = new Thread(new Vendor( ticketsPool, 2 ));
        vendorThreadThree = new Thread(new Vendor( ticketsPool, 3 ));
        vendorThreadFour = new Thread(new Vendor( ticketsPool, 4 ));
        vendorThreadFive = new Thread(new Vendor( ticketsPool, 5 ));

        //Customer Threads
        customerThreadOne = new Thread(new Customer( ticketsPool, 1 ));
        customerThreadTwo = new Thread(new Customer( ticketsPool, 2 ));
        customerThreadThree = new Thread(new Customer( ticketsPool, 3 ));
        customerThreadFour = new Thread(new Customer( ticketsPool, 4 ));
        customerThreadFive = new Thread(new Customer( ticketsPool, 5 ));

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

    private void setupProcess()
    {
        Configuration configuration = readFile();
        assert configuration != null;
        ticketsPool = new ArrayBlockingQueue<>(configuration.getMaximumTicketCapacity());
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

    private static Configuration readFile() {
        try {
            // Create ObjectMapper to handle JSON deserialization
            ObjectMapper mapper = new ObjectMapper();

            // Read JSON file and convert it to Configuration object
            Configuration config = mapper.readValue(new File("config.json"), Configuration.class);

            logger.info("Configuration successfully read from config.json");
            return config;
        } catch (Exception e) {
            logger.error("Error reading configuration file: {}", e.getMessage());
            return null;
        }
    }
}

