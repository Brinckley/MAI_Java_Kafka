package com.github.brinckley.kafka_producer.controller;

import com.github.brinckley.kafka_producer.exception.KafkaProducerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Handling kafka exceptions
 */
@Slf4j
@RestControllerAdvice
public class MessageProducerControllerExceptionHandler {
    @ExceptionHandler(KafkaProducerException.class)
    public ResponseEntity<String> handleKafkaException(Exception e) {
        log.error("Kafka error occurred", e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An internal server error occurred");
    }
}
