package com.javasampleapproach.kafka.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.javasampleapproach.kafka.model.Customer;

@Service
public class KafkaConsumer {
	
	@KafkaListener(topics="${jsa.kafka.topic}")
    public void processMessage(Customer customer) {
		System.out.println("received content = " + customer);
    }
}
