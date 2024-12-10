package org.w1959883.ticketing_system.controllers;

import com.w1959883.models.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w1959883.ticketing_system.models.Config;
import org.w1959883.ticketing_system.services.ConfigService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/api/config" )
public class ConfigController
{
    private final ConfigService configService;

    public ConfigController( ConfigService configService )
    {
        this.configService = configService;
    }

    @PostMapping( "/add" )
    private void addConfig( Configuration config )
    {
        configService.addConfig( config );
    }

    @GetMapping
    public List<Config> getAllConfigs()
    {
        return configService.getAllConfigs();
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<Config> getConfigById( @PathVariable Integer id )
    {
        Optional<Config> config = configService.getConfigById( id );
        return config.map( ResponseEntity::ok )
                       .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<Configuration> updateConfig( @PathVariable Integer id, @RequestBody Configuration configuration )
    {
        Optional<Config> config = configService.getConfigById( id );
        if( config.isPresent() )
        {
            return ResponseEntity.ok( configService.updateConfig( configuration ) );
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Void> deleteConfig( @PathVariable Integer id )
    {
        Optional<Config> config = configService.getConfigById( id );
        if( config.isPresent() )
        {
            configService.deleteConfig( id );
            return ResponseEntity.noContent().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

}
