package org.w1959883;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.w1959883.models.Configuration;

import java.io.File;
import java.util.Scanner;

public class CliApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Configuration config = new Configuration();

        System.out.print("Enter thread pool size: ");
        config.setThreadPoolSize(scanner.nextInt());

        System.out.print("Enter number of threads: ");
        config.setNumberOfThreads(scanner.nextInt());

        // Save configuration to JSON
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("config.json"), config);
            System.out.println("Configuration saved to config.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
