package com.github.brinckley.kafka_consumer;

import com.github.brinckley.kafka_consumer.kafka.ConsumerExecutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main spring boot application class
 */
@SpringBootApplication
@RequiredArgsConstructor
public class KafkaConsumerApplication implements CommandLineRunner {
	private final ConsumerExecutorService consumerExecutorService;

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		consumerExecutorService.execute();
	}
}
