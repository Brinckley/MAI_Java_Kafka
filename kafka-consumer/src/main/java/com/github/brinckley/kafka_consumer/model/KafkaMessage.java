package com.github.brinckley.kafka_consumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * Object to store message consumed from kafka
 */
@Data
@Builder
@Jacksonized
public class KafkaMessage {
    @JsonProperty("message")
    private String message;
}
