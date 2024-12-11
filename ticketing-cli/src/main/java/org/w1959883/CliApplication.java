package org.w1959883;

import com.w1959883.models.Configuration;
import com.w1959883.services.ProcessManager;
import com.w1959883.util.FileUtil;
import com.w1959883.util.TicketingLogger;
import org.apache.logging.log4j.Logger;
import org.w1959883.util.ConfigHandler;

import java.util.Scanner;

public class CliApplication {
    private static final Logger logger = TicketingLogger.getLogger();
    private static final ProcessManager processManager = ProcessManager.getInstance();
    private static Configuration config;

    public static void main(String[] args) {
        logger.info("\n" +
                            "=================================================\n" +
                            "| Welcome to the Realtime Ticket Booking System |\n" +
                            "=================================================");

        // Start a thread for handling user input
        Thread inputThread = new Thread(CliApplication::handleUserInput);
        inputThread.start();

        // Keep main thread alive for logs and background tasks
        while (true) {
            try {
                Thread.sleep(1000); // Keeps the main thread alive
            } catch (InterruptedException e) {
                logger.error("Main thread interrupted: {}", e.getMessage());
            }
        }
    }

    private static void handleUserInput() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            logger.info("1. Start Process");
            logger.info("2. Stop Process");
            logger.info("3. Setup Configurations");
            logger.info("4. Exit");
            logger.info("Please choose an option (1-4): ");

            String input = scanner.nextLine().trim();

            try {
                if (!input.matches("[1-4]")) {
                    logger.error("Invalid choice. Please select a valid option.");
                    continue;
                }

                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        if (config == null) {
                            logger.warn("Configurations are not set up yet. Please set up configurations first.");
                        } else {
                            processManager.start(FileUtil.readFile());
                        }
                        break;

                    case 2:
                        logger.info("Stopping the process...");
                        processManager.stop();
                        break;

                    case 3:
                        logger.info("Setting up configurations...");
                        config = ConfigHandler.setConfig();
                        FileUtil.writeFile(config);
                        break;

                    case 4:
                        logger.info("Exiting the system. Goodbye!");
                        processManager.stop();
                        break;

                    default:
                        logger.error("Unexpected error occurred.");
                        break;
                }
            } catch (Exception e) {
                logger.error("An error occurred: {}", e.getMessage());
            }
        }
    }
}
