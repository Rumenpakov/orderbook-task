package com.rumen.interview.orderbooktask;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.net.http.WebSocket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CountDownLatch;

public class WebSocketClient implements WebSocket.Listener {

    private static final String TOPIC = "test_topic";

//    @Autowired
//    private KafkaTemplate<String,String> kafkaTemplate;
//
//    public WebSocketClient() {
//
//    }
//
//    public void sendMessage(String message){
//
//        this.kafkaTemplate.send(TOPIC,message);
//    }
//
//    @Bean
//    public NewTopic createTopic(){
//
//        return new NewTopic(TOPIC,3,(short) 1);
//    }

    @Override
    public void onOpen(WebSocket webSocket) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now) + ": " + webSocket.getSubprotocol());
        WebSocket.Listener.super.onOpen(webSocket);
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now) + ": " + data);
//        sendMessage(data.toString());
        return WebSocket.Listener.super.onText(webSocket, data, false);
    }

    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        System.out.println("ERROR OCCURED: " + webSocket.toString());
        WebSocket.Listener.super.onError(webSocket, error);
    }
}
