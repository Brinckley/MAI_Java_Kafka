package com.github.brinckley.kafka_producer.business;

import com.github.brinckley.kafka_producer.exception.KafkaProducerException;
import com.github.brinckley.kafka_producer.model.KafkaMessage;

/**
 * Business logic of the kafka producer
 */
public interface KafkaBusiness {
    /**
     * Handle message as kafka producer
     *
     * @param kafkaMessage message
     * @throws KafkaProducerException if unable to produce message
     */
    void handleMessage(KafkaMessage kafkaMessage) throws KafkaProducerException;
}
