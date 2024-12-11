package com.w1959883.services;

import com.w1959883.models.Configuration;
import com.w1959883.models.Customer;
import com.w1959883.models.Vendor;
import com.w1959883.util.TicketCounter;
import com.w1959883.util.TicketPool;
import com.w1959883.util.TicketingLogger;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ProcessManager {
    private static final Logger logger = TicketingLogger.getLogger();
    private static final ProcessManager instance = new ProcessManager();
    private static TicketPool ticketsPool;
    private TicketCounter ticketCounter;
    private Long productionTime;
    private Long sellingTime;
    private List<Thread> vendorThreads = new ArrayList<>();
    private List<Thread> customerThreads = new ArrayList<>();

    private volatile boolean running = false;

    private ProcessManager() {
        // Private constructor for singleton pattern
    }

    public static ProcessManager getInstance() {
        return instance;
    }

    public synchronized void start(Configuration configuration) {
        if (running) {
            logger.info("Process is already running.");
            return;
        }
        setupProcess(configuration);
        running = true;

        // Vendor Threads
        int numberOfVendors = 5;
        for (int i = 1; i <= numberOfVendors; i++) {
            Thread vendorThread = new Thread(new Vendor(ticketsPool, i, ticketCounter, productionTime));
            vendorThreads.add(vendorThread);
            vendorThread.start();
        }

        // Customer Threads
        int numberOfCustomers = 5;
        for (int i = 1; i <= numberOfCustomers; i++) {
            Thread customerThread = new Thread(new Customer(ticketsPool, i, sellingTime));
            customerThreads.add(customerThread);
            customerThread.start();
        }

        logger.info("Process has Started.");
    }

    private void setupProcess(Configuration configuration) {
        assert configuration != null;
        ticketsPool = new TicketPool(configuration.getMaximumTicketCapacity());
        ticketCounter = new TicketCounter(configuration.getTotalNumberOfTickets());
        productionTime = calculateProductionTime(configuration.getTicketReleaseRate());
        sellingTime = calculateSellingTime(configuration.getCustomerRetrievalRate());
    }

    private Long calculateSellingTime(Double customerRetrievalRate) {
        return (long) ((1000 * customerRetrievalRate) / 5);
    }

    private Long calculateProductionTime(Double ticketReleaseRate) {
        return (long) ((1000 * ticketReleaseRate) / 5);
    }

    public synchronized void stop() {
        if (!running) {
            logger.info("Process is not running.");
            return;
        }

        running = false;

        // Interrupt vendor threads
        for (Thread thread : vendorThreads) {
            if (thread != null && thread.isAlive()) {
                thread.interrupt();
            }
        }
        // Interrupt customer threads
        for (Thread thread : customerThreads) {
            if (thread != null && thread.isAlive()) {
                thread.interrupt();
            }
        }

        vendorThreads.clear();
        customerThreads.clear();

        logger.info("Process has stopped.");
    }
}
