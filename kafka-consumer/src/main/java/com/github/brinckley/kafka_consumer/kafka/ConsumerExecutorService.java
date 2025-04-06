package com.github.brinckley.kafka_consumer.kafka;

/**
 * Client for communicating with Kafka
 */
public interface ConsumerExecutorService {
    /**
     * Start consuming messages from kafka and writing them to {@link com.github.brinckley.kafka_consumer.repository.MessageRepository}
     */
    void execute();
}
