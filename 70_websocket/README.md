# Websocket with SpringBoot

Websocket is a type of TCP connection where the connection is open and the producer and consumer can send the diff message.
Do not have http overheads like the http protocol.

## Implementation

* `@EnableWebSocket`
* Write a SocketHandler class extending `TextWebSocketHandler`
* Override the following methods

```
public void afterConnectionEstablished(WebSocketSession session);
public void handleMessage(WebSocketSession session, WebSocketMessage<?> message);
public void afterConnectionClosed(WebSocketSession session, CloseStatus status);
```

* Create a Socket config class extending `WebSocketConfigurer` and extend the method.

```java
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SocketHandler(), "/socket");
    }
```

This will bind the websocket to the `/socket` path.

## TO test

* Install `wscat` via npm
```
npm install -g wscat
```

* Connect locally with wscat
```
wscat -c ws://localhost:8080/socket
```


### Problems faced
1. SpringBoot version 2 did not support Scheduled
2. @Scheduled method in springBoot version 1.5.9.RELEASE was not able to access instance variable.
    Instance variable was modified, but the scheduled method has older state of the instance.