/*
 * @(#)SpringKafkaSendConsumeJavaObjectApplication.java
 *
 * Copyright (c) 2018 Lufthansa Industry Solutions . All Rights Reserved.
 *
 * LastChangedDate: Dec 5, 2018
 * LastChangedRevision: 1.0
 * LastChangedBy: U595908
 *
 */
package com.javasampleapproach.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.javasampleapproach.kafka.model.Customer;
import com.javasampleapproach.kafka.services.KafkaProducer;

@SpringBootApplication
@EnableScheduling
public class SpringKafkaSendConsumeJavaObjectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaSendConsumeJavaObjectApplication.class, args);
	}

	@Autowired
	KafkaProducer producer;

	@Override
	public void run(String... arg0) throws Exception {
		// Send Mary customer
		Customer mary = new Customer("Mary", 31);
		producer.send(mary);

		// Send Peter customer
		Customer peter = new Customer("Peter", 24);
		producer.send(peter);
	}
}
