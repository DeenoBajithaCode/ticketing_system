package com.w1959883.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.w1959883.models.Configuration;
import org.slf4j.Logger;

import java.io.File;

public class FileUtil
{
    private static final Logger logger = TicketingLogger.getLogger();

    public static Configuration readFile() {
        try {
            // Create ObjectMapper to handle JSON deserialization
            ObjectMapper mapper = new ObjectMapper();

            // Read JSON file and convert it to Configuration object
            Configuration config = mapper.readValue(new File("config.json"), Configuration.class);

            logger.info("Configuration successfully read from config.json");
            return config;
        } catch (Exception e) {
            logger.error("Error reading configuration file: {}", e.getMessage());
            return null;
        }
    }

    public static void writeFile( Configuration config )
    {
        // Save configuration to JSON
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue( new File( "config.json" ), config );
            logger.info( "Configuration saved to config.json" );
        }
        catch( Exception e )
        {
            logger.error( "Error Writing Configs to the file{}", e.getMessage() );
        }
    }
}
