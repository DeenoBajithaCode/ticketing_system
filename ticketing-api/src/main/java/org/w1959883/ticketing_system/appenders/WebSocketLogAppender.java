package org.w1959883.ticketing_system.appenders;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

public class WebSocketLogAppender extends AbstractAppender {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketLogAppender( String name, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions, SimpMessagingTemplate messagingTemplate) {
        super(name, filter, layout, ignoreExceptions);
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void append(LogEvent event) {
        if (messagingTemplate != null) {
            try {
                String logMessage = new String(getLayout().toByteArray(event), StandardCharsets.UTF_8);
                messagingTemplate.convertAndSend("/topic/logs", logMessage);
                System.out.println("WebSocket message sent: " + logMessage); // Debugging
            } catch (Exception e) {
                System.err.println("Error sending WebSocket message: " + e.getMessage());
            }
        } else {
            System.err.println("Messaging template is null, unable to send log messages.");
        }
    }


}

