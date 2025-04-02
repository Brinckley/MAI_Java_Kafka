package com.github.brinckley.kafka_consumer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("kafka")
@RequiredArgsConstructor
public class MessageConsumerController {
    private final KafkaConsumeBusiness kafkaBusiness;

    @GetMapping("/consume")
    public void publishMessage() {
        log.info("Message received to the endpoint /publish with content {}", messageRequestDto);

        KafkaMessage kafkaMessage = KafkaMessage.builder().message(messageRequestDto.getMessage()).build();
        kafkaBusiness.handleMessage(kafkaMessage);
    }
}
