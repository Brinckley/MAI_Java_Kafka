package com.github.brinckley.kafka_consumer.controller;

import com.github.brinckley.kafka_consumer.exception.KafkaConsumerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handling kafka consumer exceptions
 */
@Slf4j
@RestController
public class MessageConsumerControllerExceptionHandler {
    @ExceptionHandler(KafkaConsumerException.class)
    public ResponseEntity<String> handleKafkaException(Exception e) {
        log.error("Kafka error occurred", e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An internal server error occurred");
    }
}
