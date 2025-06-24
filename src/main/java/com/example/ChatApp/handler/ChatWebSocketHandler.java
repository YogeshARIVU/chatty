package com.example.ChatApp.handler;

import com.example.ChatApp.model.Message;
import com.example.ChatApp.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatWebSocketHandler implements WebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(ChatWebSocketHandler.class);
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ChatService chatService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = getUserIdFromSession(session);
        sessions.put(userId, session);
        logger.info("WebSocket connected: {}", userId);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws IOException {
        String payload = message.getPayload().toString();
        Message incomingMessage = objectMapper.readValue(payload, Message.class);
        incomingMessage.setSender(getUserIdFromSession(session));

        Message savedMessage = chatService.saveMessage(incomingMessage);
        String broadcast = objectMapper.writeValueAsString(savedMessage);
        broadcastMessage(broadcast);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.error("Transport error: {}", exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String userId = getUserIdFromSession(session);
        sessions.remove(userId);
        logger.info("WebSocket closed: {}", userId);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private void broadcastMessage(String message) {
        sessions.values().forEach(session -> {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(message));
                } catch (IOException e) {
                    logger.error("Send failed: {}", e.getMessage());
                }
            }
        });
    }

    private String getUserIdFromSession(WebSocketSession session) {
        String query = session.getUri().getQuery();
        if (query != null && query.startsWith("userId=")) {
            return query.substring(7);
        }
        return session.getId();
    }
}
