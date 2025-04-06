package com.github.brinckley.kafka_consumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class KafkaResponseMessageDto {
    @JsonProperty("message")
    private String message;

    /**
     * Convert {@link KafkaMessage} object to {@link KafkaResponseMessageDto}
     *
     * @param kafkaMessage kafkaMessage to convert
     * @return result of conversion
     */
    public static KafkaResponseMessageDto from(KafkaMessage kafkaMessage) {
        return KafkaResponseMessageDto.builder().message(kafkaMessage.getMessage()).build();
    }
}
