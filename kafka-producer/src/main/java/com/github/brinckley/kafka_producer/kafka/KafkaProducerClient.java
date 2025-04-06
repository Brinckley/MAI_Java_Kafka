package com.github.brinckley.kafka_producer.kafka;

import com.github.brinckley.kafka_producer.exception.KafkaProducerException;
import com.github.brinckley.kafka_producer.model.KafkaMessage;

/**
 * Service to produce messages and send them to Kafka
 */
public interface KafkaProducerClient {
    /**
     * Sending message to kafka
     *
     * @param message message
     */
    void sendMessage(KafkaMessage message);
}
