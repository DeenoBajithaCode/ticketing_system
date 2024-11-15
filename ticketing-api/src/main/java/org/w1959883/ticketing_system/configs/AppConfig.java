package org.w1959883.ticketing_system.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {
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
