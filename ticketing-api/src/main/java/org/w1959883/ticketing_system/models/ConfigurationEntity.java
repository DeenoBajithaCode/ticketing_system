package org.w1959883.ticketing_system.models;

import com.w1959883.models.Configuration;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class ConfigurationEntity extends Configuration
{
    @Id
    private Long configurationId;
    @Enumerated( EnumType.STRING )
    private ConfigurationStatus status;

    public Long getConfigurationId()
    {
        return configurationId;
    }

    public void setConfigurationId( Long configurationId )
    {
        this.configurationId = configurationId;
    }

    public ConfigurationStatus getStatus()
    {
        return status;
    }

    public void setStatus( String status )
    {
        this.status = ConfigurationStatus.valueOf( status );
    }
}
