package com.w1959883.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TicketingLogger {
    private static final Logger logger = LogManager.getLogger("TicketingSystemLogger");

    private TicketingLogger() {
        // Private constructor to prevent instantiation
    }

    public static Logger getLogger() {
        return logger;
    }
}
