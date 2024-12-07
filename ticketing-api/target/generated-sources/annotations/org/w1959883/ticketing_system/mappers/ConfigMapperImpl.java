package org.w1959883.ticketing_system.mappers;

import com.w1959883.models.Configuration;
import javax.annotation.processing.Generated;
import org.w1959883.ticketing_system.models.ConfigurationEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-07T19:53:54+0530",
    comments = "version: 1.6.0.Beta2, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
public class ConfigMapperImpl implements ConfigMapper {

    @Override
    public ConfigurationEntity toConfig(Configuration configuration) {
        if ( configuration == null ) {
            return null;
        }

        ConfigurationEntity configurationEntity = new ConfigurationEntity();

        configurationEntity.setTotalNumberOfTickets( configuration.getTotalNumberOfTickets() );
        configurationEntity.setTicketReleaseRate( configuration.getTicketReleaseRate() );
        configurationEntity.setCustomerRetrievalRate( configuration.getCustomerRetrievalRate() );
        configurationEntity.setMaximumTicketCapacity( configuration.getMaximumTicketCapacity() );

        return configurationEntity;
    }

    @Override
    public Configuration toConfiguration(ConfigurationEntity configurationEntity) {
        if ( configurationEntity == null ) {
            return null;
        }

        Configuration configuration = new Configuration();

        configuration.setTotalNumberOfTickets( configurationEntity.getTotalNumberOfTickets() );
        configuration.setTicketReleaseRate( configurationEntity.getTicketReleaseRate() );
        configuration.setCustomerRetrievalRate( configurationEntity.getCustomerRetrievalRate() );
        configuration.setMaximumTicketCapacity( configurationEntity.getMaximumTicketCapacity() );

        return configuration;
    }
}
