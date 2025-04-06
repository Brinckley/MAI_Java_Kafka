package com.github.brinckley.kafka_consumer.business;

import com.github.brinckley.kafka_consumer.exception.KafkaConsumerException;
import com.github.brinckley.kafka_consumer.model.KafkaMessage;

/**
 * Business logic for communicating with Kafka
 */
public interface KafkaConsumerBusiness {
    /**
     * Method for gaining message consumed from Kafka
     *
     * @return KafkaMessage object
     * @throws KafkaConsumerException if unable to communicate with Kafka
     */
    KafkaMessage consumeMessage() throws KafkaConsumerException;
}
