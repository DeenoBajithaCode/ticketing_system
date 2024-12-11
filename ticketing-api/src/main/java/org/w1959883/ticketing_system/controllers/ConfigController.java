package org.w1959883.ticketing_system.controllers;
import com.w1959883.models.Configuration;
import org.springframework.web.bind.annotation.*;
import org.w1959883.ticketing_system.exceptions.ResourceNotFoundException;
import org.w1959883.ticketing_system.models.Config;
import org.w1959883.ticketing_system.services.ConfigService;

import java.util.List;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @PostMapping("/add")
    public String addConfig(@RequestBody Configuration config) {
        if (config == null) {
            throw new IllegalArgumentException("Configuration object cannot be null");
        }
        configService.addConfig(config);
        return "Configurations Added Successfully";
    }

    @GetMapping
    public List<Config> getAllConfigs() {
        return configService.getAllConfigs();
    }

    @GetMapping("/{id}")
    public Config getConfigById(@PathVariable Integer id) {
        return configService.getConfigById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Configuration not found for ID: " + id));
    }

    @PutMapping("/{id}")
    public Config updateConfig(@PathVariable Integer id, @RequestBody Configuration configuration) {
        if (!configService.getConfigById(id).isPresent()) {
            throw new ResourceNotFoundException("Cannot update. Configuration not found for ID: " + id);
        }
        return configService.updateConfig(configuration);
    }

    @DeleteMapping("/{id}")
    public void deleteConfig(@PathVariable Integer id) {
        if (!configService.getConfigById(id).isPresent()) {
            throw new ResourceNotFoundException("Cannot delete. Configuration not found for ID: " + id);
        }
        configService.deleteConfig(id);
    }
}
