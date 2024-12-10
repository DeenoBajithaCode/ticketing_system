package com.w1959883.util;

import com.w1959883.models.Ticket;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TicketPool
{
    //Ticket pool
    private BlockingQueue<Ticket> ticketsPool;

    public TicketPool(int size) {
        ticketsPool = new LinkedBlockingQueue<>(size);
    }

    public void addTickets(Ticket ticket) throws InterruptedException {
        ticketsPool.put(ticket);
    }

    public Ticket purchaseTicket() throws InterruptedException {
        return ticketsPool.take();
    }

}
