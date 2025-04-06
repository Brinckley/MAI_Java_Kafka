package com.github.brinckley.kafka_consumer.business.impl;

import com.github.brinckley.kafka_consumer.business.KafkaConsumerBusiness;
import com.github.brinckley.kafka_consumer.exception.KafkaConsumerException;
import com.github.brinckley.kafka_consumer.model.KafkaConsumerMessage;
import com.github.brinckley.kafka_consumer.model.KafkaMessage;
import com.github.brinckley.kafka_consumer.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of {@link KafkaConsumerBusiness}
 */
@Service
@RequiredArgsConstructor
public class KafkaConsumerBusinessImpl implements KafkaConsumerBusiness {
    private static final String NO_DATA_MESSAGE = "No message in consumer yet";

    private final MessageRepository messageRepository;

    @Override
    public KafkaMessage consumeMessage() throws KafkaConsumerException {
        Optional<KafkaConsumerMessage> latestMessage = messageRepository.getLatestMessage();
        String message = latestMessage.isPresent() ? latestMessage.get().getValue() : NO_DATA_MESSAGE;

        return KafkaMessage.builder().message(message).build();
    }
}
