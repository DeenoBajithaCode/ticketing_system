package org.w1959883.ticketing_system.models;

import com.w1959883.models.Configuration;
import jakarta.persistence.*;

@Entity
public class Config extends Configuration
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void setStatus( ConfigurationStatus status )
    {
        this.status =  status;
    }
}
