package com.github.brinckley.kafka_consumer.repository.impl;

import com.github.brinckley.kafka_consumer.model.KafkaConsumerMessage;
import com.github.brinckley.kafka_consumer.model.KafkaMessage;
import com.github.brinckley.kafka_consumer.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * In memory realization of {@link MessageRepository}
 */
@Repository
@RequiredArgsConstructor
public class InMemoryMessageRepository implements MessageRepository {
    private final Queue<KafkaConsumerMessage> storageQueue = new ConcurrentLinkedQueue<>();

    @Override
    public void saveMessage(KafkaConsumerMessage kafkaMessage) {
        storageQueue.add(kafkaMessage);
    }

    @Override
    public Optional<KafkaConsumerMessage> getLatestMessage() {
        KafkaConsumerMessage peeked = storageQueue.peek();
        return peeked == null ? Optional.empty() : Optional.of(peeked);
    }
}
