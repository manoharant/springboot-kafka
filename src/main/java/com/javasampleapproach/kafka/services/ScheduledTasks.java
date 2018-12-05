/*
 * @(#)ScheduledTasks.java
 *
 * Copyright (c) 2018 Lufthansa Industry Solutions . All Rights Reserved.
 *
 * LastChangedDate: Dec 5, 2018
 * LastChangedRevision: 1.0
 * LastChangedBy: U595908
 *
 */
package com.javasampleapproach.kafka.services;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.javasampleapproach.kafka.model.ChannelMapping;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduledTasks {
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Value("${jsa.kafka.filepath}")
	private String filepath;

	@Autowired(required = true)
	private KafkaTemplate<String, ChannelMapping> kafkaTemplate;

	@Scheduled(cron = "${jsa.kafka.cronexpression}")
	public void loadObjectList() {
		long startTime = System.nanoTime();
		try {
			log.info("Scheduler started ::{}", dateTimeFormatter.format(LocalDateTime.now()));
			CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator(';');
			CsvMapper mapper = new CsvMapper();

			File csvFile = new File(filepath + File.separator + "Book1.csv");
			log.info("CSV File location ::" + csvFile);
			MappingIterator<ChannelMapping> readValues = mapper.readerFor(ChannelMapping.class).with(bootstrapSchema)
					.readValues(csvFile);
			while (readValues.hasNext()) {
				ChannelMapping request = readValues.next();
				log.info("CSV Parser value ::{}", request);
				kafkaTemplate.send(ChannelMapping.class.getSimpleName(), request);
			}
		} catch (Exception e) {
			log.error("Error occurred while loading object list from file ", e);
		}
		long elapsedTime = System.nanoTime() - startTime;
		log.debug("Scheduler duration: {}ms", elapsedTime / 1000000.0);
	}

}