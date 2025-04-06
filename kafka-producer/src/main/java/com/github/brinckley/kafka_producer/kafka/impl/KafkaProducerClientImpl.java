package com.github.brinckley.kafka_producer.kafka.impl;

import com.github.brinckley.kafka_producer.config.KafkaConfig;
import com.github.brinckley.kafka_producer.exception.KafkaProducerException;
import com.github.brinckley.kafka_producer.kafka.KafkaProducerClient;
import com.github.brinckley.kafka_producer.model.KafkaMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * Implementation of {@link KafkaProducerClient}
 */
@Slf4j
@Service
public class KafkaProducerClientImpl implements KafkaProducerClient {
    private final KafkaConfig kafkaConfig;

    private final KafkaProducer<String, KafkaMessage> msgKafkaProducer;

    @Autowired
    public KafkaProducerClientImpl(KafkaConfig kafkaConfig) {
        this.kafkaConfig = kafkaConfig;

        Properties kafkaProperties = this.getKafkaProperties();
        msgKafkaProducer = new KafkaProducer<>(kafkaProperties);
    }

    @Override
    public void sendMessage(KafkaMessage message) {
        String topic = kafkaConfig.getMsgTopic();

        log.info("Sending message {} to topic {}", message, topic);

        ProducerRecord<String, KafkaMessage> msgProducer = new ProducerRecord<>(topic, topic, message);
        msgKafkaProducer.send(msgProducer);
    }

    private Properties getKafkaProperties() {
        log.info("Initializing kafka producer");

        Properties kafkaProperties = new Properties();
        // Basic data
        kafkaProperties.put("bootstrap.servers", kafkaConfig.getBootstrapServers());
        kafkaProperties.put("key.serializer", kafkaConfig.getKeySerializer());
        kafkaProperties.put("value.serializer", kafkaConfig.getValueSerializer());

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
