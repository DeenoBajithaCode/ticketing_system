package org.w1959883.util;

import com.w1959883.models.Configuration;
import com.w1959883.util.TicketingLogger;
import org.slf4j.Logger;

import java.util.Scanner;

public class ConfigHandler
{
    private static final Logger logger = TicketingLogger.getLogger();

    public static Configuration setConfig() {
        Scanner scanner = new Scanner(System.in);
        Configuration config = new Configuration();

        config.setTotalNumberOfTickets(promptForInteger(scanner, "Enter Total Number Of Tickets (or type 'cancel' to exit): "));
        config.setTicketReleaseRate(promptForDouble(scanner, "Enter Ticket Release Rate (or type 'cancel' to exit): "));
        config.setCustomerRetrievalRate(promptForDouble(scanner, "Enter Customer Retrieval Rate (or type 'cancel' to exit): "));
        config.setMaximumTicketCapacity(promptForInteger(scanner, "Enter Maximum Ticket Capacity (or type 'cancel' to exit): "));

        return config;
    }

    private static int promptForInteger(Scanner scanner, String message) {
        while (true) {
            try {
                logger.info(message);
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("cancel")) {
                    logger.info("Configuration canceled by user.");
                    System.exit(0); // Exit the program
                }

                int value = Integer.parseInt(input);
                if (value < 0) throw new IllegalArgumentException("Value must be non-negative.");
                return value;
            } catch (NumberFormatException e) {
                logger.error("Invalid input. Please enter a valid integer.");
            } catch (IllegalArgumentException e) {
                logger.error(e.getMessage());
            }
        }
    }

    private static double promptForDouble(Scanner scanner, String message) {
        while (true) {
            try {
                logger.info(message);
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("cancel")) {
                    logger.info("Configuration canceled by user.");
                    System.exit(0); // Exit the program
                }

                double value = Double.parseDouble(input);
                if (value < 0) throw new IllegalArgumentException("Value must be non-negative.");
                return value;
            } catch (NumberFormatException e) {
                logger.error("Invalid input. Please enter a valid decimal number.");
            } catch (IllegalArgumentException e) {
                logger.error(e.getMessage());
            }
        }
    }

}
