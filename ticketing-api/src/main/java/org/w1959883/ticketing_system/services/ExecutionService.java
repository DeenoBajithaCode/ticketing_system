package org.w1959883.ticketing_system.services;

import org.springframework.stereotype.Service;
import org.w1959883.ticketing_system.configs.AppConfig;

@Service
public class ExecutionService
{
    private final AppConfig appConfig;

    public ExecutionService( AppConfig appConfig )
    {
        this.appConfig = appConfig;
    }

    public void setProjectStatus( Boolean executionRequest )
    {
        if( executionRequest ){
            run();
        }
    }

    public void run() {
        System.out.println("Running with thread pool size: " + appConfig.getThreadPoolSize());
        System.out.println("Running with number of threads: " + appConfig.getNumberOfThreads());
        // Start your logic here
    }
}
