package com.github.brinckley.kafka_producer.business.impl;

import com.github.brinckley.kafka_producer.business.KafkaProducerBusiness;
import com.github.brinckley.kafka_producer.exception.KafkaProducerException;
import com.github.brinckley.kafka_producer.kafka.KafkaProducerClient;
import com.github.brinckley.kafka_producer.model.KafkaMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link KafkaProducerBusiness}
 */
@Service
@RequiredArgsConstructor
public class KafkaProducerBusinessImpl implements KafkaProducerBusiness {
    private final KafkaProducerClient kafkaProducerClient;

    @Override
    public void handleMessage(KafkaMessage kafkaMessage) throws KafkaProducerException {
        try {
            kafkaProducerClient.sendMessage(kafkaMessage);
        } catch (Exception e) {
            throw KafkaProducerException.format("Unable to send data to kafka error : %s", e.getMessage());
        }
    }
}
