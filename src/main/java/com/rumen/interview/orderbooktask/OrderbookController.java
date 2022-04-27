package com.rumen.interview.orderbooktask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderbookController {

    private final Producer producer;

    @Autowired
    public OrderbookController(Producer producer) {
        this.producer = producer;
    }

    @GetMapping("/listen")
    public void messageToTopic(){
        this.producer.listen();
    }
}
