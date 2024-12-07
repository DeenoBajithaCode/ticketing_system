package org.w1959883.ticketing_system.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.w1959883.models.Configuration;
import com.w1959883.services.ProcessManager;
import com.w1959883.util.TicketingLogger;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.w1959883.ticketing_system.models.ConfigurationEntity;
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

    public void setExecution( Boolean executionRequest )
    {
        List<ConfigurationEntity> configurationEntities = configRepository.findAllByStatus( ConfigurationStatus.NOT_DONE );
        if( executionRequest )
        {
            for( ConfigurationEntity configuration : configurationEntities )
            {
                processManager.start( configuration );
            }
        }
        else
        {
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
