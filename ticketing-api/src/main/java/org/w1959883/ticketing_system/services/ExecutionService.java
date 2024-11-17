package org.w1959883.ticketing_system.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.w1959883.models.Configuration;
import com.w1959883.services.ProcessManager;
import com.w1959883.util.TicketingLogger;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ExecutionService
{
    private static final Logger logger = TicketingLogger.getLogger();
    private static final ProcessManager processManager = ProcessManager.getInstance();

    public void setExecution( Boolean executionRequest )
    {
        if( executionRequest ){
            processManager.start();
        }
        else {
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
