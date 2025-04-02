package com.github.brinckley.kafka_consumer.exception;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * Special exception for Kafka consumer errors
 */
@StandardException
public class KafkaConsumerException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 5052008218952664631L;

    /**
     * Constructor
     *
     * @param message message
     */
    public KafkaConsumerException(String message) {
        super(message);
    }

    /**
     * Exception factory with specific message
     *
     * @param format  string format
     * @param objects arguments for string format
     * @return new KafkaConsumerException
     */
    public static KafkaConsumerException format(String format, Object... objects) {
        return new KafkaConsumerException(String.format(format, objects));
    }
}
