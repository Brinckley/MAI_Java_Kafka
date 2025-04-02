package com.github.brinckley.kafka_producer.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;

/**
 * Message format to receive from user
 */
@Data
@Builder
@Jacksonized
@Validated
public class MessageRequestDto {
    private static final String NOT_NULL_MESSAGE = "Message must be not null";

    @NotNull(message = NOT_NULL_MESSAGE)
    @JsonProperty("message")
    private String message;
}
