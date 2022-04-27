package com.rumen.interview.orderbooktask;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CountDownLatch;

@Service
public class Producer {

    public void listen() {
        try {
            String publicWebSocketURL = "wss://ws.kraken.com/";
            String publicWebSocketSubscriptionMsg = "{ \"event\":\"subscribe\", \"subscription\":{\"name\":\"trade\"},\"pair\":[\"XBT/USD\"] }";

            /*
             * MORE PUBLIC WEBSOCKET EXAMPLES
             *
             * String publicWebSocketSubscriptionMsg =
             * "{ \"event\": \"subscribe\", \"subscription\": { \"interval\": 1440, \"name\": \"ohlc\"}, \"pair\": [ \"XBT/EUR\" ]}"
             * ;
             * String publicWebSocketSubscriptionMsg =
             * "{ \"event\": \"subscribe\", \"subscription\": { \"name\": \"spread\"}, \"pair\": [ \"XBT/EUR\",\"ETH/USD\" ]}"
             * ;
             */

            OpenAndStreamWebSocketSubscription(publicWebSocketURL, publicWebSocketSubscriptionMsg);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*
     * WebSocket API
     */

    public static void OpenAndStreamWebSocketSubscription(String connectionURL, String webSocketSubscription) {
        try {

            CountDownLatch latch = new CountDownLatch(1);
            WebSocket ws = HttpClient
                    .newHttpClient()
                    .newWebSocketBuilder()
                    .buildAsync(URI.create(connectionURL), new WebSocketClient())
                    .join();
            ws.sendText(webSocketSubscription, true);
            latch.await();

        } catch (

                Exception e) {
            System.out.println();
            System.out.println("AN EXCEPTION OCCURED :(");
            System.out.println(e);
        }
    }
}
