package com.github.brinckley.kafka_consumer.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class KafkaConfig {
    @Value("${msg.topic}")
    private String msgTopic;

    @Value("${json.topic}")
    private String jsonTopic;

    @Value("${bootstrap.servers}")
    private String bootstrapServers;

    @Value("${key.deserializer}")
    private String keyDeserializer;

    @Value("${value.deserializer}")
    private String valueDeserializer;

    @Value("${consumer.group.id}")
    private String consumerGroupId;

    @Value("${consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Value("${security.protocol}")
    private String securityProtocol;

    @Value("${ssl.truststore.location}")
    private String sslTrustStoreLocation;

    @Value("${ssl.truststore.password}")
    private String sslTruststorePassword;

    @Value("${ssl.keystore.location}")
    private String sslKeystoreLocation;

    @Value("${ssl.keystore.password}")
    private String sslKeystorePassword;

    @Value("${ssl.key.password}")
    private String sslKeyPassword;

    @Value("${sasl.mechanism}")
    private String saslMechanism;

    @Value("${sasl.jaas.config}")
    private String saslJaasConfig;
}