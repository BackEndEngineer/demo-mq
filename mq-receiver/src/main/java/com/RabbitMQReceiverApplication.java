package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMQReceiverApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(RabbitMQReceiverApplication.class, args);
    }
    
}
