package com.github.brinckley.kafka_producer.business.impl;

import com.github.brinckley.kafka_producer.business.KafkaBusiness;
import com.github.brinckley.kafka_producer.exception.KafkaProducerException;
import com.github.brinckley.kafka_producer.kafka.KafkaProducerClient;
import com.github.brinckley.kafka_producer.model.KafkaMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link KafkaBusiness}
 */
@Service
@RequiredArgsConstructor
public class KafkaBusinessImpl implements KafkaBusiness {
    private final KafkaProducerClient kafkaProducerClient;

    @Override
    public void handleMessage(KafkaMessage kafkaMessage) throws KafkaProducerException {
        kafkaProducerClient.sendMessage(kafkaMessage);
    }
}
