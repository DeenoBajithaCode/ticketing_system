package org.w1959883;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.w1959883.models.Configuration;
import com.w1959883.services.ProcessManager;
import com.w1959883.util.TicketingLogger;
import org.slf4j.Logger;
import org.w1959883.util.ConfigHandler;

import java.io.File;
import java.util.Scanner;

public class CliApplication
{
    private static final Logger logger = TicketingLogger.getLogger();
    private static final ProcessManager processManager = ProcessManager.getInstance();

    public static void main( String[] args )
    {
        Scanner scanner = new Scanner( System.in );
        Configuration config = null;

        logger.info( "\n" +
                             "=================================================\n" +
                             "| Welcome to the Realtime Ticket Booking System |\n" +
                             "=================================================" );

        while( true )
        {
            logger.info( "1. Start Process" );
            logger.info( "2. Stop Process" );
            logger.info( "3. Setup Configurations" );
            logger.info( "4. Exit" );
            logger.info( "Please choose an option (1-4): " );

            try
            {
                String input = scanner.nextLine().trim();

                if( !input.matches( "[1-4]" ) )
                {
                    logger.error( "Invalid choice. Please select a valid option." );
                    continue;
                }

                int choice = Integer.parseInt( input );

                switch( choice )
                {
                    case 1:
                        if( config == null )
                        {
                            logger.warn( "Configurations are not set up yet. Please set up configurations first." );
                        }
                        else
                        {
                            processManager.start();
                        }
                        break;

                    case 2:
                        logger.info( "Stopping the process..." );
                        processManager.stop();
                        break;

                    case 3:
                        logger.info( "Setting up configurations..." );
                        config = ConfigHandler.setConfig();
                        writeFile( config );
                        break;

                    case 4:
                        logger.info( "Exiting the system. Goodbye!" );
                        System.exit( 0 );
                        break;

                    default:
                        logger.error( "Unexpected error occurred." );
                        break;
                }
            }
            catch( Exception e )
            {
                logger.error( "An error occurred: {}", e.getMessage() );
            }
        }

    }

    private static void writeFile( Configuration config )
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
