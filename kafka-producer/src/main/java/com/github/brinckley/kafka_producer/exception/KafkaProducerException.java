package com.github.brinckley.kafka_producer.exception;

import lombok.experimental.StandardException;

import java.io.Serial;

/**
 * Special exception for Kafka producer errors
 */
@StandardException
public class KafkaProducerException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -3442826473319329883L;

    /**
     * Constructor
     *
     * @param message message
     */
    public KafkaProducerException(String message) {
        super(message);
    }

    /**
     * Exception factory with specific message
     *
     * @param format string format
     * @param objects arguments for string format
     * @return new KafkaProducerException
     */
    public static KafkaProducerException format(String format, Object ... objects) {
        return new KafkaProducerException(String.format(format, objects));
    }
}
