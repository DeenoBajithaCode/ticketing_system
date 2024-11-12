package com.w1959883.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TicketingLogger {
    private static final Logger logger = LoggerFactory.getLogger("TicketingSystemLogger");

    private TicketingLogger() {
        // Private constructor to prevent instantiation
    }

    public static Logger getLogger() {
        return logger;
    }
}
