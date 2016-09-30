package com;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	@RabbitListener(queues = "queue0")
	public void receiveMessage0(String message) {
		System.out.println("Queue0 Received <" + message + ">");
	}

	@RabbitListener(queues = "queue1")
	public void receiveMessage1(String message) {
		System.out.println("Queue1 Received <" + message + ">");
	}
}
