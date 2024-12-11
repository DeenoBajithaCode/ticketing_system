package org.w1959883.ticketing_system.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.w1959883.models.Configuration;
import com.w1959883.services.ProcessManager;
import com.w1959883.util.TicketCounter;
import com.w1959883.util.TicketingLogger;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.w1959883.ticketing_system.models.Config;
import org.w1959883.ticketing_system.models.ConfigurationStatus;
import org.w1959883.ticketing_system.repositories.ConfigRepository;

import java.io.File;
import java.util.List;

@Service
public class ExecutionService
{
    private static final Logger logger = TicketingLogger.getLogger();
    private static final ProcessManager processManager = ProcessManager.getInstance();
    private final ConfigRepository configRepository;

    public ExecutionService( ConfigRepository configRepository )
    {
        this.configRepository = configRepository;
    }

    public void setExecution(Boolean executionRequest) {
        List<Config> configurationEntities = configRepository.findAllByStatus(ConfigurationStatus.QUEUED);

        if (executionRequest) {
            for (Config configuration : configurationEntities) {
                // Start the process for the current configuration
                processManager.start(configuration);
                TicketCounter ticketCounter = processManager.getTicketCounter();

                // Monitor the ticket counter for limit reached
                while (!ticketCounter.isLimitReached()) {
                    try {
                        Thread.sleep(500); // Wait to avoid busy-waiting
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Restore interrupted status
                        throw new RuntimeException("Thread interrupted while monitoring ticket counter.", e);
                    }
                }

                // When limit is reached, update the status and stop the process
                configuration.setStatus(ConfigurationStatus.DONE);
                configRepository.save(configuration);
                processManager.stop();
            }
        } else {
            processManager.stop();
        }
    }


    public void addConfiguration( Configuration configuration )
    {
        // Save configuration to JSON
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue( new File( "config.json" ), configuration );
            logger.info( "Configuration saved to config.json" );
        }
        catch( Exception e )
        {
            logger.error( "Error Occurred while executing{}", e.getMessage() );
        }
    }
}
