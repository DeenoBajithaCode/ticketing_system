package org.w1959883.ticketing_system.services;

import com.w1959883.models.Configuration;
import org.springframework.stereotype.Service;
import org.w1959883.ticketing_system.mappers.ConfigMapper;
import org.w1959883.ticketing_system.models.ConfigurationEntity;
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
        ConfigurationEntity config = configMapper.toConfig( configDto );
        configRepository.save( config );
    }

    public List<ConfigurationEntity> getAllConfigs()
    {
        return configRepository.findAll();
    }

    public Optional<ConfigurationEntity> getConfigById( Integer configurationId )
    {
        return configRepository.findById( configurationId );
    }


    public ConfigurationEntity updateConfig( Configuration configuration )
    {
        ConfigurationEntity config = configMapper.toConfig( configuration );
        return configRepository.save( config );
    }

    public void deleteConfig( Integer configId )
    {
        configRepository.deleteById( configId );
    }

}
