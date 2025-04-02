package com.github.brinckley.kafka_producer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * Message to be sent to kafka
 */
@Data
@Builder
@Jacksonized
public class KafkaMessage {
    @JsonProperty("message")
    private String message;
}
