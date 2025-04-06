package com.github.brinckley.kafka_consumer.repository;

import com.github.brinckley.kafka_consumer.model.KafkaConsumerMessage;
import com.github.brinckley.kafka_consumer.model.KafkaMessage;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * Repository for storing consumed kafka messages
 */
public interface MessageRepository {
    /**
     * Saving kafkaMessage to the repository
     *
     * @param kafkaMessage kafka message
     */
    void saveMessage(KafkaConsumerMessage kafkaMessage);

    /**
     * Fetching latest received message
     *
     * @return latest kafkaMessage if present
     */
    Optional<KafkaConsumerMessage> getLatestMessage();
}
