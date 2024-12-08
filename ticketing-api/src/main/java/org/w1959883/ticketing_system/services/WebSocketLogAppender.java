package org.w1959883.ticketing_system.services;

import ch.qos.logback.core.AppenderBase;
import org.apache.logging.log4j.core.LogEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketLogAppender extends AppenderBase<LogEvent>
{

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketLogAppender(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void append(LogEvent event) {
        if (messagingTemplate != null) {
            String logMessage = event.getMessage().getFormattedMessage();
            messagingTemplate.convertAndSend("/topic/logs", logMessage);
        }
    }
}
