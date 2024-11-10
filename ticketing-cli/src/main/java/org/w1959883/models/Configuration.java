package org.w1959883.models;

public class Configuration {
    private int threadPoolSize;
    private int numberOfThreads;

    // Getters and setters
    public int getThreadPoolSize() {
        return threadPoolSize;
    }
    public void setThreadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }
    public int getNumberOfThreads() {
        return numberOfThreads;
    }
    public void setNumberOfThreads(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }
}
