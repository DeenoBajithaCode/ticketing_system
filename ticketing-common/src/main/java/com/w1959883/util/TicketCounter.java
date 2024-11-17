package com.w1959883.util;

import java.util.concurrent.atomic.AtomicInteger;

public class TicketCounter {
    private final AtomicInteger counter = new AtomicInteger(0);
    private final int maxTickets;

    public TicketCounter(int maxTickets) {
        this.maxTickets = maxTickets;
    }

    /**
     * Increments the counter if the maximum limit is not reached.
     * @return the incremented value, or -1 if the limit is reached.
     */
    public synchronized int increment() {
        if (counter.get() < maxTickets) {
            return counter.incrementAndGet();
        }
        return -1; // Indicates the limit has been reached
    }

    /**
     * Checks if the ticket limit has been reached.
     * @return true if the limit is reached, false otherwise.
     */
    public boolean isLimitReached() {
        return counter.get() >= maxTickets;
    }

    public int getCurrentCount() {
        return counter.get();
    }
}