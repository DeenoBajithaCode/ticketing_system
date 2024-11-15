package org.w1959883.ticketing_system.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.w1959883.ticketing_system.configs.AppConfig;

import java.io.File;

@Component
public class ConfigLoader {
    private final AppConfig appConfig;

    public ConfigLoader(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @PostConstruct
    public void loadConfig() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            AppConfig loadedConfig = mapper.readValue(new File("config.json"), AppConfig.class);

            // Set the loaded configuration
            appConfig.setThreadPoolSize(loadedConfig.getThreadPoolSize());
            appConfig.setNumberOfThreads(loadedConfig.getNumberOfThreads());
            System.out.println("Configuration loaded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
