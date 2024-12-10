package org.w1959883.ticketing_system.services;

import com.w1959883.models.Configuration;
import org.springframework.stereotype.Service;
import org.w1959883.ticketing_system.mappers.ConfigMapper;
import org.w1959883.ticketing_system.models.Config;
import org.w1959883.ticketing_system.models.ConfigurationStatus;
import org.w1959883.ticketing_system.repositories.ConfigRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ConfigService
{
    private final ConfigRepository configRepository;
    private final ConfigMapper configMapper = ConfigMapper.INSTANCE;

    public ConfigService( ConfigRepository configRepository )
    {
        this.configRepository = configRepository;
    }

    public void addConfig( Configuration configDto )
    {
        Config config = configMapper.toConfig( configDto );
        config.setStatus( ConfigurationStatus.QUEUED );
        configRepository.save( config );
    }

    public List<Config> getAllConfigs()
    {
        return configRepository.findAll();
    }

    public Optional<Config> getConfigById( Integer configurationId )
    {
        return configRepository.findById( configurationId );
    }


    public Config updateConfig( Configuration configuration )
    {
        Config config = configMapper.toConfig( configuration );
        return configRepository.save( config );
    }

    public void deleteConfig( Integer configId )
    {
        configRepository.deleteById( configId );
    }

}
