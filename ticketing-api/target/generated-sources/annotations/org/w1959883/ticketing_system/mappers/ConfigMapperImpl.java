package org.w1959883.ticketing_system.mappers;

import com.w1959883.models.Configuration;
import javax.annotation.processing.Generated;
import org.w1959883.ticketing_system.models.Config;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-08T01:34:19+0530",
    comments = "version: 1.6.0.Beta2, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
public class ConfigMapperImpl implements ConfigMapper {

    @Override
    public Config toConfig(Configuration configuration) {
        if ( configuration == null ) {
            return null;
        }

        Config config = new Config();

        config.setTotalNumberOfTickets( configuration.getTotalNumberOfTickets() );
        config.setTicketReleaseRate( configuration.getTicketReleaseRate() );
        config.setCustomerRetrievalRate( configuration.getCustomerRetrievalRate() );
        config.setMaximumTicketCapacity( configuration.getMaximumTicketCapacity() );

        return config;
    }

    @Override
    public Configuration toConfiguration(Config config) {
        if ( config == null ) {
            return null;
        }

        Configuration configuration = new Configuration();

        configuration.setTotalNumberOfTickets( config.getTotalNumberOfTickets() );
        configuration.setTicketReleaseRate( config.getTicketReleaseRate() );
        configuration.setCustomerRetrievalRate( config.getCustomerRetrievalRate() );
        configuration.setMaximumTicketCapacity( config.getMaximumTicketCapacity() );

        return configuration;
    }
}
