package com.github.brinckley.kafka_consumer.kafka.impl;

import com.github.brinckley.kafka_consumer.config.KafkaConfig;
import com.github.brinckley.kafka_consumer.kafka.ConsumerExecutorService;
import com.github.brinckley.kafka_consumer.model.KafkaConsumerMessage;
import com.github.brinckley.kafka_consumer.model.KafkaMessage;
import com.github.brinckley.kafka_consumer.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Implementation of {@link ConsumerExecutorService}
 */
@Slf4j
@Service
public class ConsumerExecutorServiceImpl implements ConsumerExecutorService {
    private static final int TIMEOUT_MILLIS = 10000;

    private static final Duration POLL_TIMEOUT_DURATION = Duration.ofMillis(TIMEOUT_MILLIS);

    private final KafkaConfig kafkaConfig;

    private final KafkaConsumer<String, String> kafkaConsumer;

    private final MessageRepository messageRepository;

    @Autowired
    public ConsumerExecutorServiceImpl(KafkaConfig kafkaConfig, MessageRepository messageRepository) {
        this.kafkaConfig = kafkaConfig;
        this.messageRepository = messageRepository;

        Properties properties = this.getProperties();
        kafkaConsumer = new KafkaConsumer<>(properties);
    }

    @Override
    public void execute() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            kafkaConsumer.subscribe(Collections.singletonList(kafkaConfig.getMsgTopic()));
            try {
                while (true) {
                    ConsumerRecords<String, String> records = kafkaConsumer.poll(POLL_TIMEOUT_DURATION);
                    for (ConsumerRecord record : records) {
                        log.info("Record Key {} -- Value {}", record.key(), record.value());
                        KafkaConsumerMessage model = KafkaConsumerMessage.builder()
                                .key((String) record.key())
                                .value((String) record.value())
                                .build();
                        messageRepository.saveMessage(model);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                kafkaConsumer.close();
            }
        });
    }

    private Properties getProperties() {
        log.info("Initializing kafka producer");

        Properties kafkaProperties = new Properties();
        // Basic data
        kafkaProperties.put("bootstrap.servers", kafkaConfig.getBootstrapServers());
        kafkaProperties.put("key.deserializer", kafkaConfig.getKeyDeserializer());
        kafkaProperties.put("value.deserializer", kafkaConfig.getValueDeserializer());
        kafkaProperties.put("group.id", kafkaConfig.getConsumerGroupId());
        kafkaProperties.put("consumer.auto-offset-reset", kafkaConfig.getAutoOffsetReset());

        // SSL
        kafkaProperties.put("security.protocol", kafkaConfig.getSecurityProtocol());
        kafkaProperties.put("ssl.truststore.location", kafkaConfig.getSslTrustStoreLocation());
        kafkaProperties.put("ssl.truststore.password", kafkaConfig.getSslTruststorePassword());
        kafkaProperties.put("ssl.keystore.location", kafkaConfig.getSslKeystoreLocation());
        kafkaProperties.put("ssl.keystore.password", kafkaConfig.getSslKeystorePassword());
        kafkaProperties.put("ssl.key.password", kafkaConfig.getSslKeyPassword());

        // SASL
        kafkaProperties.put("sasl.mechanism", kafkaConfig.getSaslMechanism());
        kafkaProperties.put("sasl.jaas.config", kafkaConfig.getSaslJaasConfig());

        return kafkaProperties;
    }
}
