package com.github.brinckley.kafka_consumer.model;

import lombok.Builder;
import lombok.Data;

/**
 * Key value object for reading from ConsumerRecord 
 */
@Data
@Builder
public class KafkaConsumerMessage {
    private String key;

    private String value;
}
