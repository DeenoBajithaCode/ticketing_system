package com.w1959883.services;

import com.w1959883.models.Ticket;
import com.w1959883.util.Consumer;
import com.w1959883.util.Producer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProcessManager
{
    private static final ProcessManager instance = new ProcessManager();

    private final BlockingQueue<Ticket> queue = new ArrayBlockingQueue<>(10);
    private Thread producerThread;
    private Thread consumerThread;
    private volatile boolean running = false;

    private ProcessManager() {
        // Private constructor for singleton pattern
    }

    public static ProcessManager getInstance() {
        return instance;
    }

    public synchronized void start() {
        if (running) {
            System.out.println("Producer-Consumer is already running.");
            return;
        }

        running = true;
        producerThread = new Thread(new Producer(queue));
        consumerThread = new Thread(new Consumer(queue));
        producerThread.start();
        consumerThread.start();
        System.out.println("Producer-Consumer started.");
    }

    public synchronized void stop() {
        if (!running) {
            System.out.println("Producer-Consumer is not running.");
            return;
        }

        running = false;
        producerThread.interrupt();
        consumerThread.interrupt();
        System.out.println("Producer-Consumer stopped.");
    }
}

