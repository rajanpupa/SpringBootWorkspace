package com.rajan.websocket.controller;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@EnableScheduling
public class SocketHandler extends TextWebSocketHandler {
    private static volatile Map<WebSocketSession, String> sessionStringMap = new HashMap<>();

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Connection closed for session " + session + ", status: " + status);
        sessionStringMap.remove(session);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        if( ! sessionStringMap.containsKey(session)){
            // First time session
            System.out.println("First time connection.." + session);
            sessionStringMap.put(session, "");
            session.sendMessage(new TextMessage("Welcome Home!"));
        }else{
            System.out.println("Connection already present");
        }

    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        sessionStringMap.put(session, message.getPayload().toString());
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 5000)
    public void message() {
        for(Map.Entry<WebSocketSession, String> entry : sessionStringMap.entrySet()){
            try {
                if(!entry.getValue().equals("")){
                    entry.getKey().sendMessage(new TextMessage("Processing your message: " + entry.getValue()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
